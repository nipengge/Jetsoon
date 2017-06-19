package com.jetsoon.clientconn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jetsoon.service.*;
import com.jetsoon.service.impl.*;
import com.jetsoon.util.DateJsonValueProcessor;
import com.jetsoon.util.SectionPackUtil;

import net.sf.json.*;



import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Parser;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_gps_raw_int;
import com.MAVLink.common.msg_heartbeat;
import com.MAVLink.common.msg_mission_ack;
import com.MAVLink.common.msg_mission_count;
import com.MAVLink.common.msg_mission_item;
import com.MAVLink.common.msg_mission_request;
import com.MAVLink.common.msg_request_data_stream;
import com.MAVLink.common.msg_sys_status;
import com.MAVLink.common.msg_vfr_hud;
import com.MAVLink.enums.MAV_DATA_STREAM;
import com.MAVLink.enums.MAV_TYPE;
import com.jetsoon.config.CommandConfig;
import com.jetsoon.frame.UavMainFrame;
import com.jetsoon.reference.ListDataReference;
import com.jetsoon.reference.ListSeqReference;
import com.jetsoon.serverconn.UAVSocketServer;
import com.jetsoon.util.HashBiMap;
import net.sf.json.util.CycleDetectionStrategy;



/**
 * 客户端管理类
 * 负责命令逻辑的分发
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-26 下午08:11:25
 */
public class UAVSocketCilent extends Thread{

	private static final int MAX_SEQ = 255; //消息最大长度
 
	//private static int seqNumber = 0;

	private final Socket socket;//上下文socket

	private UAVSocketServer uavSocketServer;//上下文server

	private DataInputStream oReader;//上下文 输入流

	private DataOutputStream oWritter;//上下文 输出流

	private String msg;//上下文　消息

	//private String uavId;//无人机编号

	private List<msg_mission_item> items = new ArrayList<msg_mission_item>();

	private static  HashMap<String, Byte> flagMap = new HashMap<String, Byte>();

	private static HashBiMap<String,Socket> socketMap = new HashBiMap<String,Socket>();//当前在线的用户集合

	private static Map<Socket,Map<String,Object>> flight_history = new HashMap<Socket,Map<String,Object>>();
	
	private SectionPackUtil sectionPackUtil;


	public  UAVSocketCilent(UAVSocketServer uavSocketServer,Socket socket){

		this.socket = socket;

		this.uavSocketServer = uavSocketServer;

	}

	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			//读入流
			oReader = new DataInputStream(socket.getInputStream());

			//写出流
			oWritter = new DataOutputStream(socket.getOutputStream());

			
			byte[] l_aruBuf = new byte[2048];

			int len = 0;

			//读取客户端发送的数据
			while ((len = oReader.read(l_aruBuf))!=-1) {

				if(l_aruBuf[0] == -2){
					
					if(sectionPackUtil == null ){
						sectionPackUtil = new SectionPackUtil();
					}
					
					//截取数据包
					List<byte[]> packs = sectionPackUtil.packetInterception(l_aruBuf, len);
					
					for (byte[] pack : packs) {
						
						Parser parser = new Parser();
						
						for (int i = 0; i < pack.length; i++) {
							
							System.out.print(pack[i]+" ");
							
							MAVLinkPacket packet = parser.mavlink_parse_char(pack[i] & 0x00ff);
					
							if(packet != null){
								
								System.out.println("飞控返回数据");
								
								MAVLinkMessage message = packet.unpack();

								if(message.msgid == msg_mission_request.MAVLINK_MSG_ID_MISSION_REQUEST){
									//接收到请求航点命令

									msg_mission_request requestMsg = (msg_mission_request) message;

									sendUAVMession(ListDataReference.getMission(socketMap.getKey(socket), requestMsg.seq));

								}else if(message.msgid == msg_mission_ack.MAVLINK_MSG_ID_MISSION_ACK){
									//接收到航点请求完毕命令
									msg_mission_ack ack = (msg_mission_ack) message;

									if(ack.type == 0){
										//删除航点信息
										ListDataReference.remove(socketMap.getKey(socket));

										UavMainFrame.setMessage(socket+":飞机反馈航点请求完毕");
										System.out.println(socket+":飞机反馈航点请求完毕");

									}else{

										System.out.println("错误");
									}

								}else if(message.msgid == msg_heartbeat.MAVLINK_MSG_ID_HEARTBEAT){//心跳

									initflight();

									msg_heartbeat heartbeat = (msg_heartbeat) message;

									Map<String ,Object> history = flight_history.get(socket);

									if(heartbeat.type == MAV_TYPE.MAV_TYPE_2G){ //检测是否是2G模发送过来的消息

									}else{//正常飞控
										history.put("droneType", heartbeat.type);
										history.put("droneStatus", heartbeat.system_status);
										history.put("systemMode", heartbeat.base_mode);
										history.put("customMode", heartbeat.custom_mode);

										flight_history.put(socket, history);
										//inputHistory();
									}

									byte[] butff ={-2,0,0,0,0,0,0,0};

									sendMessage2G(butff);

								  String uavid = socketMap.getKey(socket);
								 
								  
								  Socket socket =  socketMap.getValue(uavid);
								  
								  socket.setSoTimeout(10000);

									if(flagMap.get(uavid) == null){

										socket.setSoTimeout(10000);
										socketMap.put(uavid, socket);

										// setupStreamRates(null,(byte)1, (byte)1, 1, 1, 1, 1, 1, 1, 1, 1);//(2017.4.26修改)

										flagMap.put(uavid, (byte) 1);
									}


								}else if(message.msgid == msg_sys_status.MAVLINK_MSG_ID_SYS_STATUS){

									msg_sys_status status = (msg_sys_status) message;

									initflight();

									Map<String ,Object> history = flight_history.get(socket);
									float voltage = status.voltage_battery;

									history.put("voltage",voltage/1000);

									flight_history.put(socket,history);
									inputHistory();

									UavMainFrame.setMessage("电压："+status.voltage_battery);
								}else if(message.msgid == msg_gps_raw_int.MAVLINK_MSG_ID_GPS_RAW_INT){

									msg_gps_raw_int gps_raw = (msg_gps_raw_int) message;
									
									initflight();

									Map<String ,Object> history = flight_history.get(socket);
									
									if(gps_raw.fix_type == 2 || gps_raw.fix_type == 3){ //正确定位保存数据
																									
										double lat = gps_raw.lat;

										double lon = gps_raw.lon;

										float  alt = gps_raw.alt;
										System.out.println(history);
																										
										if(gps_raw.time_usec == 1){					//检查是否是2G定位模块		
											
											history.put("voltage",0.0f);
											history.put("speed",0.0f);

											history.put("lat",lat/100000);
											history.put("lon",lon/100000);
											history.put("alt",alt/1000);
											
										}else{
											history.put("lat",lat/10000000);
											history.put("lon",lon/10000000);
											history.put("alt",alt/1000);
										}
									}else{
										history.put("lat",0);
										history.put("lon",0);
										history.put("alt",0);
									}
									
									flight_history.put(socket,history);
									inputHistory();
																														
									UavMainFrame.setMessage("经度:"+gps_raw.lat+",纬度:"+gps_raw.lon+",高度:"+gps_raw.alt+",地速"+gps_raw.vel);

								}else if(message.msgid == msg_vfr_hud.MAVLINK_MSG_ID_VFR_HUD){

									msg_vfr_hud vfr = (msg_vfr_hud) message;

									initflight();

									Map<String ,Object> history = flight_history.get(socket);

									float speed = vfr.airspeed;

									history.put("speed",speed/1000);

									flight_history.put(socket,history);
									inputHistory();
									UavMainFrame.setMessage("航向:"+vfr.heading+",空速:"+vfr.airspeed+",地速"+vfr.groundspeed);
								}

								break;
							}
							
						}
						
					}
					
				}else if(l_aruBuf[0] == 123 && l_aruBuf[len-1] == 125){//如果是符合JSON 的 {}

					//客户端发来命令

					if(msg != null){

						msg = null;
					}
					System.out.println(l_aruBuf[0]+":"+l_aruBuf[len-1]);
					msg = new String(l_aruBuf,0,len,"utf-8");

					UavMainFrame.setMessage("接收到："+socket+"发来的消息："+msg);

					JSONObject jsonObject = JSONObject.fromObject(msg.toString());

					System.out.println(msg.toString());

					String from = jsonObject.getString("from");

					if("2G".equals(from)){
						String command = (String) jsonObject.get("command");
						if(command.equals("Handshake")){

							try{
								//2G发来握手消息
								String IMEI = (String) jsonObject.get("IMEI");

								//向数据库验证此IEMI是否已录入
							/*	DroneInfoService droneInfoService = new DroneInfoServiceImpl();

								Map<String, Object> droneInfo = droneInfoService.findByIEMI(IMEI);*/

								//	if(droneInfo != null){ //用户在网页录入了无人机信息   验证通过

								IMEILibrayService imeiLibrayService = new IMEILibrayServiceImpl();
								
								if(imeiLibrayService.isIMEI(IMEI)){

									DroneInfoService droneInfoService = new DroneInfoServiceImpl();

									if(droneInfoService.isDroneLock(IMEI)){
										oWritter.write("OK:1".getBytes());
									}else{
										//没有锁死记录允许解锁
										oWritter.write("OK:2".getBytes());
									}
									
									socketMap.put(IMEI, socket);

								}else{
									oWritter.write("Fail:1".getBytes());
								}

								/*}else {//用户没有在网页录入无人机信息 此2G未经过认证
									oWritter.write("Fail :2".getBytes());
								}*/

							}catch (SQLException e){
								e.printStackTrace();
							}

						}else if(command.equals("inputIMEI")){ //入库2G
							try {
								String IMEI = jsonObject.getString("IMEI");
								IMEILibrayService imeiLibrayService = new IMEILibrayServiceImpl();

								if(!imeiLibrayService.isIMEI(IMEI)){
									SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									imeiLibrayService.add2GIMEI(IMEI,simpleDateFormat.format(new Date()));
									if(imeiLibrayService.isIMEI(IMEI)){
										oWritter.write("Type in OK".getBytes());
									}
								}

							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						/*//(已不需要 龙文科2017.4.26注释)
						else if(command.equals("FCB connect")){
							setupStreamRates(null,(byte)1, (byte)1, 1, 1, 1, 1, 1, 1, 1, 1);
						}
						*/
					}else{
						if(jsonObject.has("code")) {//客户端命令

							String code = jsonObject.getString("code");

							if(code.equals(CommandConfig.HEART_BEAT)){//心跳
								
								DataOutputStream oWritter = new DataOutputStream(socket.getOutputStream());
								
								int state = 0;
								
								if(socketMap.containsValue(socket)){
									state = 1;
								}else{
									state = 0;
								}
								oWritter.write(("{'msgId':10,'code':"+CommandConfig.SERVER_HEARTHEART+",'state':"+state+"}$#_").getBytes("utf-8"));
								System.out.println("向android发送心跳");
								UavMainFrame.setMessage("接受到android心跳");
								UavMainFrame.setMessage("向android发送心跳");
								
							}else if(code.equals(CommandConfig.LOGIN)){//客户请求登录 并保持连接
								//账号
								String accountName = jsonObject.getString("accountName");
								//密码
								String euPassword =jsonObject.getString("password");
								//校验码
								String checkCode = jsonObject.getString("checkCode");

								oWritter  =  new DataOutputStream(socket.getOutputStream());

							//	if(!socketMap.containsValue(socket)){//如果此账号没有登录过

									if(checkCode.equals("8787jjlsdflsdf57584")){//检验校验码是否正确

										try {
											EnterpriseUserService userService = new EnterpriseUserServiceImpl();

											Map<String,Object> user = userService.LoginCheck(accountName,euPassword);

											//System.out.println(format.format(date));

											JsonConfig jsonConfig = new JsonConfig();
											//设置屏蔽的数据
											jsonConfig.setExcludes(new String[]{"registerDateTime","euPassword"});

											JSONObject result = JSONObject.fromObject(user,jsonConfig);


											//根据账号密码查询企业信息
											//JSONObject result = responseData.CheckLogin(accountName, euPassword);
											if(!result.isNullObject()){//账号密码正确返回企业信息
												result.put("msgId", 1);
												result.put("code", 0);

												oWritter.write((result.toString()+"$#_").getBytes("utf-8"));

												//层层效验通过 保存与服务器的链接

												socketMap.put(accountName,socket);

											}else{
												oWritter.write("{'msgId':1,'code':2}$#_".getBytes("utf-8"));//返回此账号不错在或密码错误
											}

										} catch (SQLException e) {
											e.printStackTrace();
										}

									}else{

										oWritter.write("{'msgId':1,'code':1}$#_".getBytes("utf-8"));//返回效验码不正确

									}

								/*}else{//登陆过

									oWritter.write("{'msgId':1,'code':3}$#_".getBytes("utf-8"));//反回此账号已登录过
								}*/

							}else if(code.equals("qureyUavList")) {//查询无人机列表
								try {
									String companyId = jsonObject.getString("companyId");
									int role = jsonObject.getInt("role");//权限
									int currentPage = jsonObject.getInt("currentPage");//当前第几页

									DroneInfoService droneInfoService = new DroneInfoServiceImpl();

									List<Map<String,Object>> uavList = droneInfoService.findByCompanyId(companyId,role,currentPage);

									if(uavList != null){

										/**
										 * 此断代码解决java.lang.IllegalArgumentException
										 */
										JsonConfig jsonConfig = new JsonConfig();
										jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
										// resolve  java.lang.IllegalArgumentException java.sql.Date.getHours(Date.java:177)
										jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new DateJsonValueProcessor());
										//resolve  java.sql.SQLException: Positioned Update not supported.
										//jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
										JSONArray jsonArray = JSONArray.fromObject(uavList,jsonConfig);
										JSONObject json = new JSONObject();
										json.put("msgId", 2);
										json.put("uavList", jsonArray);

										byte[] butff = (json.toString()+"$#_").getBytes("utf-8");
										System.out.println(butff.length);
										System.out.println(json.toString());
										oWritter.write(butff);

									}else{
										JSONObject result = new JSONObject();
										result.put("code","NotUavList");
										oWritter.write((result.toString()+"$#_").getBytes("utf-8"));
									}

								} catch (SQLException e) {
									e.printStackTrace();
								}
									/*if(socketMap.containsValue(socket)){

									}else{

									}*/
							}else if(code.equals("findFlightHistoryByIMEI")){ //查询飞机历史轨迹
								try{
									String IMEI = (String) jsonObject.get("IMEI");
									String startDate = (String) jsonObject.get("startDate");
									String endDate = (String) jsonObject.get("endDate");

									FlightHistoryService flightHistoryService = new FlightHistoryServiceImpl();
									List<Map<String,Object>> flightHistory = flightHistoryService.findFlightHistoryInfoByIMEI(IMEI,startDate,endDate);

									if(flightHistory != null && !flightHistory.isEmpty()){

										JSONArray jsonArray = JSONArray.fromObject(flightHistory);
										JSONObject result = new JSONObject();
										result.put("msgId", 3);
										result.put("flightHistory", jsonArray);

										oWritter.write((result.toString()+"$#_").getBytes("utf-8"));
									}

								}catch (SQLException e){
									e.printStackTrace();
								}

							}else if(code.equals("inputPlantProtectionHistory")){//录入植保历史记录
								try {
									String uavName = jsonObject.getString("uavName");
									String IMEI = jsonObject.getString("IMEI");
									String startDate = jsonObject.getString("startDate");
									String endDate = jsonObject.getString("endDate");
									String cropperName = jsonObject.getString("cropperName");
									float sprayingWidth = (float) jsonObject.getDouble("sprayingWidth");
									
									PlantProtectionHistoryService plantProtectionHistoryService = new PlantProtectionHistoryServiceImpl();
									
									plantProtectionHistoryService.inputPlantProtectionHistory(uavName,IMEI, startDate, endDate, cropperName, sprayingWidth);
									
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
							}else if(code.equals("findPlantProtectionHistoryByIMEI")){//根据IMEI查询植保历史记录
								
								try {
									String IMEI = jsonObject.getString("IMEI");
									
									PlantProtectionHistoryService plantProtectionHistoryService = new PlantProtectionHistoryServiceImpl();
									List<Map<String, Object>> plantProtectionHistorys  = plantProtectionHistoryService.findPlantProtectionHistoryByIMEI(IMEI);
									
									JSONObject resultJson = new JSONObject();
									
									resultJson.put("msgId", 5);
									resultJson.put("plantProtectionHistorys", plantProtectionHistorys);
									
									DataOutputStream out = new DataOutputStream(socket.getOutputStream());
									out.write((resultJson.toString()+"$#_").getBytes("utf-8"));
									
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else if(code.equals("updateDroneLockStatus")){ // 更改无人机锁定状态
								
								try {
									
									String IMEI = jsonObject.getString("IMEI");
									int  onLine = jsonObject.getInt("onLine");//0代表正常,1代表锁死
									
									DroneInfoService droneInfoService = new DroneInfoServiceImpl();
									
									droneInfoService.updateUAVOnLineStatus(IMEI, onLine);
									
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else if(code.equals("Unlock")){//解除锁死

								String uavid = jsonObject.getString("uavid");

								sendMessage2G(uavid,"CMD:1");

							}else if(code.equals("takeoff1")){// 一键起飞

								String uavid = jsonObject.getString("uavid");

								/**
								 * 切换定点模式
								 *//*
								msg_set_mode mode = new msg_set_mode();
								mode.custom_mode = 5;//定点模式
								mode.base_mode = 1;//

								sendMessage2G(uavid, mode);

								*//**
								 * takeoff起飞
								 *//*
								msg_command_long msg = new msg_command_long();
								msg.command = 22;
								msg.param7 = 10;

								sendMessage2G(uavid, msg);*/

								sendMessage2G(uavid,"CMD:4");

							}else if(code.equals("Return")){// 返航模式

								String uavid = jsonObject.getString("uavid");

							/*	msg_set_mode msg = new msg_set_mode();

								msg.custom_mode = 6;
								msg.base_mode = 1;*/

								sendMessage2G(uavid, "CMD:9");

							}else if(code.equals("lock")){//上锁
								String uavid = jsonObject.getString("uavid");

								sendMessage2G(uavid, "CMD:0");
							}else if(code.equals("arm")){ //一键解锁

								String uavid = jsonObject.getString("uavid");

//								/**
//								 * 切换自稳模式
//								 */
//								msg_set_mode mode = new msg_set_mode();
//								mode.custom_mode = 0;
//								mode.base_mode = 1;
//
//								sendMessage2G(uavid, mode);
//
//								/**
//								 * 解锁
//								 * param2  智航的值
//								 */
//								msg_command_long msg = new msg_command_long();
//								msg.command = 400;
//								msg.param1 = 1;
//								msg.param2 = 21196;
//
//								sendMessage2G(uavid, msg);
								sendMessage2G(uavid,"CMD:1");

							}else if(code.equals("disArm")){

								String uavid = jsonObject.getString("uavid");

								/*msg_command_long msg = new msg_command_long();

								msg.command = 400;
								msg.param1 = 0;
								msg.param2 = 21196;*/

								sendMessage2G(uavid, "CMD:3");

							}else if(code.equals("forcedReturn")){//强制返航
								String uavid = jsonObject.getString("uavid");

								sendMessage2G(uavid,"CMD:6");
							}else if(code.equals("cancelCompulsoryReturn")){
								String uavid = jsonObject.getString("uavid");

								sendMessage2G(uavid,"CMD:7");
							}else if(code.equals("bindingModule")){
								String uavid = jsonObject.getString("uavid");

								sendMessage2G(uavid,"CMD:99");
							}else if(code.equals("takeoff")){
								String uavid = jsonObject.getString("uavid");

								sendMessage2G(uavid,"CMD:8");
							}else if(code.equals("automatic")){//切换自动模式并解锁起飞

								String uavid = jsonObject.getString("uavid");

								/**
								 * 解锁
								 *//*
								msg_command_long armlong = new msg_command_long();

								armlong.command = 400;
								armlong.param1 = 1;
								armlong.param2 = 21196;

								sendMessage2G(uavid, armlong);

								*//**
								 * 切换自动模式
								 *//*
								msg_set_mode mode = new msg_set_mode();

								mode.custom_mode = 3;
								mode.base_mode = 1;*/

								sendMessage2G(uavid, "CMD:5");

							}else if(code.equals("Routeplanning")){//接收到向指定的无人机发送航线命令

								JSONArray jsonArray = jsonObject.getJSONArray("position");

								String uavid = jsonObject.getString("uavid");


								if(items == null){

									items = new ArrayList<msg_mission_item>();

								}

								int missionSeq = 0;

								for (int i = 0; i < jsonArray.size(); i++) {

									JSONObject position = jsonArray.getJSONObject(i);

									msg_mission_item missionItem = new msg_mission_item();

									if(i == 0){

										//Home
										msg_mission_item home = new msg_mission_item();

										home.x = (float) position.getDouble("lat");
										home.y = (float) position.getDouble("lng");
										home.z = (float) position.getDouble("alt");
										home.command = 16;//默认
										home.seq = missionSeq++;
										home.target_system = 1;
										home.target_component = 1;
										home.autocontinue = 1;

										items.add(home);//Home

										//起飞点
										msg_mission_item takeoffMission = new msg_mission_item();

										takeoffMission.seq = missionSeq++;
										takeoffMission.command = 22;
										takeoffMission.frame = 3;
										takeoffMission.autocontinue = 1;

										items.add(takeoffMission);

										//第一个航点
										missionItem.x = (float) position.getDouble("lat");
										missionItem.y = (float) position.getDouble("lng");
										missionItem.z = (float) position.getDouble("alt");
										missionItem.command = 16;//默认
										missionItem.seq = missionSeq;
										missionItem.frame = 3;
										missionItem.autocontinue = 1;

									}else{

										missionItem.x = (float) position.getDouble("lat");
										missionItem.y = (float) position.getDouble("lng");
										missionItem.z = (float) position.getDouble("alt");
										missionItem.command = 16; //默认
										missionItem.seq = missionSeq;
										missionItem.frame = 3;
										missionItem.autocontinue = 1;

									}

									items.add(missionItem);

									missionSeq = (missionSeq+1)%(MAX_SEQ+1);

									if(i == jsonArray.size()-1){//最后一个航点添加完毕添加一个反航点

										msg_mission_item retu = new msg_mission_item();

										retu.seq = missionSeq;
										retu.command = 20;
										retu.frame = 3;
										retu.autocontinue = 1;

										items.add(retu);
									}

								}

								System.out.println(items.size());

								//储存航点信息到缓存中,待航点 上传给飞机完毕清空该航点信息
								ListDataReference.put(uavid, items);

								//航点数量
								msg_mission_count missionCount = new msg_mission_count();
								missionCount.count = items.size();

								sendMessage2G(uavid, missionCount);

							} else if(code.equals("Electronicfence")){//电子围栏

							}else if(code.equals("stopData")){//停止数据流请求
								//JSONArray jsonArray = jsonObject.getJSONArray("position");

								String uavId = jsonObject.getString("uavid");

								setupStreamRates(uavId, (byte)1,(byte) 1, 1, 1, 1, 1, 1, 1, 1, 1);
							}
						}
					}
					}else{
						System.out.println(l_aruBuf[0]+":"+l_aruBuf[len]);
						String msg = new String(l_aruBuf,0,len,"UTF-8");
						
						System.out.println("未知消息："+msg);
						
						UavMainFrame.setMessage("未知消息："+msg);
					
					}
					/**
					 * else if("android".equals(from)){
					 *
					 * }else if("pc".equals(from)){
					 *
					 * }
					 */
				

			}
			//断开连接释放内存

			oReader.close();

			oWritter.close();

			socketMap.removeValue(socket);

			uavSocketServer.removeClient(this);

		} catch (IOException e) {
			// TODO Auto-generated catch block

			//抛出此异常表示客户端断开连接
			try {

				oReader.close();
				oWritter.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//flagMap.remove(socketMap.getKey(socket));
			socketMap.removeValue(socket);
			flight_history.remove(socket);
			uavSocketServer.removeClient(this);
			UavMainFrame.setMessage("客户端："+socket+",断开连接");

		} 

	}

	/**
	 * 向所有客户端发送命令
	 * @param msg 命令
	 */
	public static void sendMessageMapAll(String msg){

		try {

			Set<Socket> sockets = socketMap.valueSet();//获取所有键

			for (Socket socket:sockets) {//遍历所有key

				if(socketMap.getKey(socket).length()==15){//根据Key找出 如果 IMEI小于10则是客户端账号
					//获取所有的输出流 //发送消息
					DataOutputStream oWritter = new DataOutputStream(socket.getOutputStream());
					oWritter.write(msg.getBytes("UTF-8"));
					oWritter.flush();
					UavMainFrame.setMessage("向客户端"+socket+"发送消息:"+msg);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 向所有客户端发送命令
	 * @param msg
	 */
/*	public static void sendMessageListAll(String msg){
		try {
		
			for (Socket socket : socketList) {
				
				DataOutputStream oWritter = new DataOutputStream(socket.getOutputStream());
				oWritter.write(msg.getBytes("UTF-8"));
				oWritter.flush();
				UavMainFrame.setMessage("向客户端"+socket+"发送消息:"+msg);
			}
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/

	/**
	 * 向指定的 客户端发送消息
	 * @param userName
	 * @param msg
	 */
	public static void sendMessageCilent(String userName,String msg){

		try {

			String username = "_"+userName;//_是区分此连接是电脑客户端 还是 2G模块

			Socket socket = socketMap.getValue(username);

			DataOutputStream oWritter = new DataOutputStream(socket.getOutputStream());

			oWritter.write(msg.getBytes("utf-8"));

			oWritter.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 向指定的 2G模块发送消息
	 * @param uavId
	 * @param msg
	 */
	public static void sendMessage2G(String uavId,String msg){

		try {

			Socket socket = socketMap.getValue(uavId);
			
			if(socket != null){
				
				DataOutputStream oWritter = new DataOutputStream(socket.getOutputStream());

				oWritter.write(msg.getBytes("utf-8"));

				oWritter.flush();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 向指定的当前的socket发送消息
	 * @param butff
	 */
	public void sendMessage2G(byte[] butff){

		try {


			DataOutputStream oWritter = new DataOutputStream(socket.getOutputStream());

			oWritter.write(butff);

			oWritter.flush();
			System.out.println("向2G模块发送心跳");
			UavMainFrame.setMessage("向2G模块发送心跳");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 向指当前2G模块发送消息
	 * @param message
	 */
	public  void sendMessage2G(MAVLinkMessage message){

		try {

			String uavId = socketMap.getKey(socket);

			if(!ListSeqReference.containsKey(uavId)){

				ListSeqReference.put(uavId, 0);

			}

			int seqNumber = ListSeqReference.get(uavId);

			MAVLinkPacket packet = message.pack();

			packet.seq = seqNumber;

			byte [] butff = packet.encodePacket();

			for (int j = 0; j < butff.length; j++) {
				System.out.print(butff[j]+" ");
			}

			System.out.println();

			System.out.println(toHex(butff));

			DataOutputStream oWritter = new DataOutputStream(socket.getOutputStream());

			oWritter.write(butff);

			oWritter.flush();

			seqNumber = (seqNumber + 1) % (MAX_SEQ + 1);

			ListSeqReference.put(uavId, seqNumber);

			UavMainFrame.setMessage("向2G模块发送命令:"+new String(butff,0,butff.length,"utf-8"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 向指定的 2G模块发送消息
	 * @param uavId
	 * @param message
	 */
	public  void sendMessage2G(String uavId,MAVLinkMessage message){

		try {

			Socket socket = socketMap.getValue(uavId);

			if(!ListSeqReference.containsKey(uavId)){

				ListSeqReference.put(uavId, 0);

			}

			int seqNumber = ListSeqReference.get(uavId);

			MAVLinkPacket packet = message.pack();

			packet.seq = seqNumber;

			byte [] butff = packet.encodePacket();

			for (int j = 0; j < butff.length; j++) {
				System.out.print(butff[j]+" ");
			}
			System.out.println();

			System.out.println(toHex(butff));

			DataOutputStream oWritter = new DataOutputStream(socket.getOutputStream());

			oWritter.write(butff);

			oWritter.flush();

			seqNumber = (seqNumber + 1) % (MAX_SEQ + 1);

			ListSeqReference.put(uavId, seqNumber);

			System.out.println("seqNumber:"+seqNumber);

			UavMainFrame.setMessage("向2G模块发送命令:"+new String(butff,0,butff.length,"utf-8"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 向当前的2G对象发送 消息
	 * @param message
	 */
	public  void sendUAVMession(MAVLinkMessage message){

		try {

			oWritter = new DataOutputStream(socket.getOutputStream());

			MAVLinkPacket packet = message.pack();

			String uavId =  socketMap.getKey(socket);

			if(!ListSeqReference.containsKey(uavId)){
				ListSeqReference.put(uavId, 0);
			}

			int seqNumber = ListSeqReference.get(uavId);

			packet.seq = seqNumber;

			byte [] butff = packet.encodePacket();

			for (int j = 0; j < butff.length; j++) {
				System.out.print(butff[j]+" ");
			}

			System.out.println();

			System.out.println(toHex(butff));

			oWritter.write(butff);

			oWritter.flush();

			seqNumber = (seqNumber + 1) % (MAX_SEQ + 1);

			ListSeqReference.put(uavId, seqNumber);

			System.out.println("seqNumber:"+seqNumber);

			UavMainFrame.setMessage("向2G模块发送命令:"+new String(butff,0,butff.length,"utf-8"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 转换10进制到16进制
	 * @param buffer
	 * @return
	 */
	public static  String toHex(byte[] buffer) {

		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {

			// sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));

			System.out.print(Character.forDigit((buffer[i] & 240) >> 4, 16));

			System.out.print(Character.forDigit(buffer[i] & 15, 16)+" ");

			//sb.append(Character.forDigit(buffer[i] & 15, 16));
		}
		return sb.toString();
	}

	/**
	 * 初始化 无人机信息储存队列
	 */
	public void initflight(){
		if(!flight_history.containsKey(socket)){

			Map<String,Object> history = new HashMap<String, Object>();
			history.put("lat",null);
			history.put("lon",null);
			history.put("alt", null);
			history.put("voltage",null);
			history.put("speed",null);
			history.put("IMEI",socketMap.getKey(socket));

			flight_history.put(socket, history);
		}
	}

	/**
	 * 录入历史信息
	 * @throws IOException 
	 */
	public void inputHistory() throws IOException{
		try {
			Map<String,Object>  historyMap = flight_history.get(socket);
			if(historyMap.get("lat")!= null && historyMap.get("voltage") != null &&
					historyMap.get("speed") !=null){
				
				if(Float.parseFloat(historyMap.get("lat").toString()) != 0 && Float.parseFloat(historyMap.get("lon").toString()) != 0){
					
					FlightHistoryService flightHistoryService = new FlightHistoryServiceImpl();

					flightHistoryService.inputFlightHistoryInfo(historyMap);
				}
				
				flight_history.remove(socket);
				
				String IMEI = socketMap.getKey(socket);
				
				EnterpriseUserService enterpriseUserService = new EnterpriseUserServiceImpl();
				Map<String,Object> map = enterpriseUserService.findUserNameByIMEI(IMEI);
				
				System.out.println(IMEI);
				
				if(map != null && !map.isEmpty() ){
					
					String accountName = map.get("accountName").toString();
				
					
					if(socketMap.containsKey(accountName)){
						
						Socket socket = socketMap.getValue(accountName);
						DataOutputStream  oWritter = new DataOutputStream(socket.getOutputStream());
						JSONObject jsonObject = new JSONObject();
						jsonObject.putAll(historyMap);
						jsonObject.put("msgId", 4);
						System.out.println("向"+accountName+"推送实时信息");
						oWritter.write((jsonObject.toString()+"$#_").getBytes("utf-8"));
						
					}else{
						System.out.println(accountName+":不在线");
					}
					
				}
				
			}

		}catch (SQLException e){
			e.printStackTrace();;
		}

	}
	

	public void setupStreamRates(String uavId,byte sysid, byte compid,
								 int extendedStatus, int extra1, int extra2, int extra3, int position, int rcChannels,
								 int rawSensors, int rawControler) {
		requestMavlinkDataStream(uavId,sysid, compid, MAV_DATA_STREAM.MAV_DATA_STREAM_EXTENDED_STATUS, extendedStatus);
		//requestMavlinkDataStream(uavId,sysid, compid, MAV_DATA_STREAM.MAV_DATA_STREAM_EXTRA1, extra1);//
		requestMavlinkDataStream(uavId,sysid, compid, MAV_DATA_STREAM.MAV_DATA_STREAM_EXTRA2, extra2);//
		//requestMavlinkDataStream(uavId,sysid, compid, MAV_DATA_STREAM.MAV_DATA_STREAM_EXTRA3, extra3);//
		requestMavlinkDataStream(uavId,sysid, compid, MAV_DATA_STREAM.MAV_DATA_STREAM_POSITION, position);
		//requestMavlinkDataStream(uavId,sysid, compid, MAV_DATA_STREAM.MAV_DATA_STREAM_RAW_SENSORS, rawSensors);
		//requestMavlinkDataStream(uavId,sysid, compid, MAV_DATA_STREAM.MAV_DATA_STREAM_RAW_CONTROLLER,
		//rawControler);
		//requestMavlinkDataStream(uavId,sysid, compid, MAV_DATA_STREAM.MAV_DATA_STREAM_RC_CHANNELS, rcChannels);
	}

	private synchronized void requestMavlinkDataStream(String uavId,byte sysid, byte compid, int stream_id, int rate) {
		msg_request_data_stream msg = new msg_request_data_stream();
		msg.target_system = sysid;
		msg.target_component = compid;

		msg.req_message_rate = rate;
		msg.req_stream_id = (short) stream_id;
		msg.start_stop = 1;
		
		/*if (rate > 0) {
		msg.start_stop = 1;
		} else {
		msg.start_stop = 0;
		}
		 */
		if(uavId != null){
			sendMessage2G(uavId,msg);
		}else{
			sendMessage2G(msg);
		}
	}

}
	
