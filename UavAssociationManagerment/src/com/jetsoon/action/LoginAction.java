package com.jetsoon.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jetsonn.dao.BaseDao;
import com.jetsoon.bean.Backstage_User;
import com.jetsoon.bean.Enterprise_User;
import com.opensymphony.xwork2.Action;

public class LoginAction {
	
	private BaseDao baseDao;
	
	private Enterprise_User enterpriseUser;
	
	private Backstage_User backstageUser;
	
	/**
	 * ��ҵ��¼��֤
	 * @param enterpriseUser
	 * @return
	 */
	public String loginCheck(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		Enterprise_User user = (Enterprise_User) baseDao.LoginCheck("LoginCheck",enterpriseUser);
		
		if(user !=null){
			if(user.getStatu() == 1){//�˻�δ���
				request.setAttribute("code", "Account not audited");
			}else if(user.getStatu() == 2){//�˻�����ʹ��
				session.setAttribute("user", user);
				return "LoginCheck";
			}else if(user.getStatu() == 3){//�˻�������
				request.setAttribute("code", "Account is disabled");
			}
		}else {
			request.setAttribute("code", "Not LoginCheck");
		}
		
		return "Not LoginCheck";
	}
	
	/**
	 * ��ҵ��¼��֤  ajax��¼
	 * @param enterpriseUser
	 * @return
	 */
	public void ajaxLoginCheck(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		Enterprise_User user = (Enterprise_User) baseDao.LoginCheck("LoginCheck",enterpriseUser);
		HttpServletResponse response = ServletActionContext.getResponse();
		
		PrintWriter writer = null;
		
		try {
			writer = response.getWriter();;
			if(user !=null){
				if(user.getStatu() == 1){//�˻�δ���
					writer.print("Account not audited");
				}else if(user.getStatu() == 2){//�˻�����ʹ��
					session.setAttribute("user", user);
					writer.print(Action.SUCCESS);
				}else if(user.getStatu() == 3){//�˻�������
					writer.print("Account is disabled");
				}
			}else {
				writer.print("Not LoginCheck");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				
				writer.flush();
				writer.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * ע���˺�
	 * @return
	 */
	public String registeredAccount(){
		
		enterpriseUser.setStatu(2);//����Ĭ�Ͽ�ͨ״̬
		enterpriseUser.setRole(1);//����Ĭ����ҵ�ʻ�
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		enterpriseUser.setRegisterDateTime(dateFormat.format(date));
		
		int i = baseDao.insert("AddCompanyInfo", enterpriseUser);//���빫˾��Ϣ��ȡ��˾��ϢID
		
		if ( i>0 ){//���빫˾��Ϣ�ɹ����������˺�
		    int j =	baseDao.insert("AddUser", enterpriseUser);//
		    
		    if ( j>0 ){//�����˺ųɹ�
		    	
		    	return "RegisteredAccount";
		    }else {
		    	return "Not RegisteredAccount";
			}
		    
		}else {
			return "Not RegisteredAccount";
		}
	}
	
	/**
	 * ��̨�˺ŵ�¼
	 * @return
	 */
	public String backLogin(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Backstage_User backUser = (Backstage_User) baseDao.LoginCheck("backLogin", backstageUser); 
		
		if(backUser!=null){
			session.setAttribute("backUser", backUser);
			return "backLogin";
		}else {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("code", "Not backLogin");
		}
		
		return "Not backLogin";
	}
	
	/**
	 * �˳���¼
	 * @return
	 */
	public String signOut(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("backUser")!=null){
			session.setAttribute("backUser", null);
			session.setAttribute("user", null);
			return "backSignOut";
		}else if(session.getAttribute("user")!=null){
			session.setAttribute("backUser", null);
			session.setAttribute("user", null);
		}
		
		return "singOut";
	}
	
	

	public BaseDao getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public Enterprise_User getEnterpriseUser() {
		return enterpriseUser;
	}

	public void setEnterpriseUser(Enterprise_User enterpriseUser) {
		this.enterpriseUser = enterpriseUser;
	}
	
	public Backstage_User getBackstageUser() {
		return backstageUser;
	}

	public void setBackstageUser(Backstage_User backstageUser) {
		this.backstageUser = backstageUser;
	}
	
}
