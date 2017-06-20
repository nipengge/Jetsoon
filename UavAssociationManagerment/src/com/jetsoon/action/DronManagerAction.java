package com.jetsoon.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jetsonn.dao.BaseDao;
import com.jetsoon.bean.Drone_Driver_Info;
import com.jetsoon.bean.Drone_Info;
import com.jetsoon.bean.Enterprise_User;

/**
 * ���˻���Ϣ����
 * @author nipengge
 * ����:������
 * ʱ��:2017-2-9 ����04:28:04
 */
public class DronManagerAction {
	
	private BaseDao baseDao;
	
	private Drone_Info droneInfo;
	
	private Drone_Driver_Info droneDriverInfo;
	
	private List<Drone_Info> droneInfos;
	
	private List<Drone_Driver_Info> droneDriverInfos;
	
	
	/**
	 * ������˻�
	 * @return
	 */
	public String addDroneInfo(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Enterprise_User user = (Enterprise_User) session.getAttribute("user");
		if(user != null){
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			droneInfo.setOperationDate(dateFormat.format(date));
			droneInfo.setDroneCompanyId(user.getCompanyInformation().getEiId());
			int i = baseDao.insert("AddDroneInfo", droneInfo);
			if(i>0){
				session.setAttribute("add", "��ӳɹ�");
			}
		}
	
		return "AddDroneInfo";
	}
	
	
	/**
	 * ��ҳ��ѯ���˻���Ϣ
	 * @return
	 */
	public String qureyCompanyDroneInfo(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Enterprise_User user = (Enterprise_User) session.getAttribute("user");
	
		droneInfo.getPagerBean().setBaseDao(baseDao);
		droneInfo.getPagerBean().setSql("countCompanyDroneInfo");
		if(user !=null){//���ݹ�˾��ѯ
			droneInfo.setDroneCompanyId(user.getCompanyInformation().getEiId());
		}
		droneInfo.getPagerBean().setObj(droneInfo);
		session.setAttribute("page",droneInfo.getPagerBean());
		droneInfos = baseDao.qureyAll("QureyCompanyDroneInfo", droneInfo);
		
			
		return "QureyCompanyDroneInfo";
	}
	
	/**
	 * ��ҳģ����ѯ���˻���Ϣ
	 * @return
	 */
/*	public String likeQureyCompanyDroneInfo(){
		HttpSession session =ServletActionContext.getRequest().getSession();
		
		if(session.getAttribute("backUser")!=null){
			droneInfo.getPagerBean().setSql("countCompanyDroneInfo");
			droneInfo.getPagerBean().setObj(droneInfo);
			session.setAttribute("page",droneInfo.getPagerBean());
			droneInfos = baseDao.qureyAll("likeQueryDroneInfo", droneInfo);
		}
		
		return "QureyCompanyDroneInfo";
	}
*/
	
	/**
	 * ��ѯ����˾��ʻԱ
	 * @return
	 */
	public String qureyCompanyDroneDriver(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Enterprise_User user =  (Enterprise_User) session.getAttribute("user");
		if(user !=null){
			droneDriverInfo.setCompanyId(user.getCompanyInformation().getEiId());
		}
		droneDriverInfo.getPagerBean().setSql("countDroneDriver");
		droneDriverInfo.getPagerBean().setObj(droneDriverInfo);
		session.setAttribute("page",droneDriverInfo.getPagerBean());
		droneDriverInfos = 	baseDao.qureyAll("QureyCompanyDroneDriver", droneDriverInfo);
		
		return "QureyCompanyDroneDriver";
	}
	
	/**
	 * ������˻�֮ǰ��ѯ��ʻԱ�б�
	 * @return
	 */
	public String toAddDrone(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Enterprise_User user =  (Enterprise_User) session.getAttribute("user");
		
		droneDriverInfo = null;
		if(droneDriverInfo ==null){
			droneDriverInfo = new Drone_Driver_Info();
		}
		
		if(user !=null){
			droneDriverInfo.setCompanyId(user.getCompanyInformation().getEiId());
		}
		
			droneDriverInfos = 	baseDao.qureyAll("toAddDrone", droneDriverInfo);
		
		
		return "toAddDrone";
	}	
	
	/**
	 * ��Ӽ�ʻԱ
	 * @return
	 */
	public String addDroneDriver(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		Enterprise_User user = (Enterprise_User) session.getAttribute("user");
		if(user!=null){
			droneDriverInfo.setCompanyId(user.getCompanyInformation().getEiId());
			int i = baseDao.insert("AddDroneDriver",droneDriverInfo);
			if(i>0){
				request.setAttribute("add", "��ӳɹ�");
			}
			return "AddDroneDriver";
		}else{
			return "Not AddDroneDriver";
		}
		
		
	}
	
	/**
	 * �޸����˻���Ϣ֮ǰ
	 * @return
	 */
	public String toUpdateDroneInfo(){
		
		droneInfo = (Drone_Info) baseDao.LoginCheck("toUpdateDroneInfo", droneInfo);
		
		return "toUpdateDroneInfo";
	}
	
	/**
	 * �޸����˻���Ϣ
	 * @return
	 */
	public 	String updateDroneInfo(){
		int i = baseDao.update("updateDroneInfo", droneInfo);
		System.out.println(droneInfo.getUseCompanyName());
		if(i>0){
			return "updateDroneInfo";
		}
		return "Not updateDroneInfo";
	}
	
	/**
	 * �޸ķ�����Ϣ֮ǰ
	 * @return
	 */
	public String toUpdateDroneDriverInfo(){
		
		droneDriverInfo = (Drone_Driver_Info) baseDao.LoginCheck("toUpdateDroneDriverInfo", droneDriverInfo);
		
		return "toUpdateDroneDriverInfo";
	}
	
	/**
	 * �޸ķ�����Ϣ
	 * @return
	 */
	public String updateDroneDriverInfo(){
		
		int i = baseDao.update("updateDroneDriverInfo", droneDriverInfo);
		if(i>0){
			return "updateDroneDriverInfo";
		}
		return "Not updateDroneDriverInfo";
	}
	
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public Drone_Info getDroneInfo() {
		return droneInfo;
	}

	public void setDroneInfo(Drone_Info droneInfo) {
		this.droneInfo = droneInfo;
	}

	public List<Drone_Info> getDroneInfos() {
		return droneInfos;
	}

	public void setDroneInfos(List<Drone_Info> droneInfos) {
		this.droneInfos = droneInfos;
	}

	public List<Drone_Driver_Info> getDroneDriverInfos() {
		return droneDriverInfos;
	}

	public void setDroneDriverInfos(List<Drone_Driver_Info> droneDriverInfos) {
		this.droneDriverInfos = droneDriverInfos;
	}

	public Drone_Driver_Info getDroneDriverInfo() {
		return droneDriverInfo;
	}

	public void setDroneDriverInfo(Drone_Driver_Info droneDriverInfo) {
		this.droneDriverInfo = droneDriverInfo;
	}
	
	
	
}
