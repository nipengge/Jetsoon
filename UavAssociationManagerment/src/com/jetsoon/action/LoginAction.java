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
	 * 企业登录验证
	 * @param enterpriseUser
	 * @return
	 */
	public String loginCheck(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		Enterprise_User user = (Enterprise_User) baseDao.LoginCheck("LoginCheck",enterpriseUser);
		
		if(user !=null){
			if(user.getStatu() == 1){//账户未审核
				request.setAttribute("code", "Account not audited");
			}else if(user.getStatu() == 2){//账户正常使用
				session.setAttribute("user", user);
				return "LoginCheck";
			}else if(user.getStatu() == 3){//账户被禁用
				request.setAttribute("code", "Account is disabled");
			}
		}else {
			request.setAttribute("code", "Not LoginCheck");
		}
		
		return "Not LoginCheck";
	}
	
	/**
	 * 企业登录验证  ajax登录
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
				if(user.getStatu() == 1){//账户未审核
					writer.print("Account not audited");
				}else if(user.getStatu() == 2){//账户正常使用
					session.setAttribute("user", user);
					writer.print(Action.SUCCESS);
				}else if(user.getStatu() == 3){//账户被禁用
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
	 * 注册账号
	 * @return
	 */
	public String registeredAccount(){
		
		enterpriseUser.setStatu(2);//设置默认开通状态
		enterpriseUser.setRole(1);//设置默认企业帐户
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		enterpriseUser.setRegisterDateTime(dateFormat.format(date));
		
		int i = baseDao.insert("AddCompanyInfo", enterpriseUser);//插入公司信息获取公司信息ID
		
		if ( i>0 ){//插入公司信息成功继续插入账号
		    int j =	baseDao.insert("AddUser", enterpriseUser);//
		    
		    if ( j>0 ){//插入账号成功
		    	
		    	return "RegisteredAccount";
		    }else {
		    	return "Not RegisteredAccount";
			}
		    
		}else {
			return "Not RegisteredAccount";
		}
	}
	
	/**
	 * 后台账号登录
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
	 * 退出登录
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
