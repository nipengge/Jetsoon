package com.jetsoon.serverconn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.jetsoon.clientconn.UAVSocketCilent;
import com.jetsoon.config.ServerConfig;
import com.jetsoon.frame.UavMainFrame;
/**
 * 服务器服务管理类
 * 负责连接服务器客户端的分发
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-26 下午08:05:38
 */
public class UAVSocketServer extends Thread{
	
	private ServerSocket serverSocket;
	
	private List<UAVSocketCilent> mlist_click = new ArrayList<UAVSocketCilent>();
	
	
	public void run(){
		
		super.run();
		
		try {
			
			serverSocket = new ServerSocket(ServerConfig.PORT);
			
			UavMainFrame.setMessage("服务器已开启端口:"+ServerConfig.PORT);
			System.out.println("服务器已开启端口:"+ServerConfig.PORT);
			//死循环监听此端口有客户端连接
			while (true) {
				//监听此端口
				Socket clientSocket = serverSocket.accept();
				//创建客户端连接分配一个线程
				UAVSocketCilent uavSocketCilent = new UAVSocketCilent(this,clientSocket);
				//启动
				uavSocketCilent.start();
				
				UavMainFrame.setMessage("客户端连接："+clientSocket);
				
				mlist_click.add(uavSocketCilent);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	/**
	 * 客户端断开连接 删除此连接
	 * @param uavSocketCilent
	 */
	public void removeClient(UAVSocketCilent uavSocketCilent){
		
		if(uavSocketCilent != null){
			
			synchronized (mlist_click) {//数据同步线程安全
				
					if(mlist_click.contains(uavSocketCilent)){//此连接存在
						 mlist_click.remove(uavSocketCilent);
						 UavMainFrame.setMessage("设备"+uavSocketCilent+"断开连接");
					}
			}
		}
		
		
	}

}
