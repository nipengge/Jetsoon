package com.jetsoon.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jetsonn.dao.BaseDao;
import com.jetsoon.bean.Backstage_User;
import com.jetsoon.bean.Enterprise_User;

/**
 * 企业账户
 * @author nipengge
 * 作者:吕国朋
 * 时间:2017-2-6 上午11:47:20
 */
public class EnterpriseUserAction {
	
	private BaseDao baseDao;
	
	private Enterprise_User enterpriseUser;
	
	private List<Enterprise_User> enterpriseUsers;
	
	/**
	 * 查询所有企业账户 + 分页 + 筛选查询
	 * @return
	 */
	public String queryNotChecked(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		enterpriseUser.getPagerBean().setBaseDao(baseDao);
		enterpriseUser.getPagerBean().setSql("countNotChecked");
		
		enterpriseUser.getPagerBean().setObj(enterpriseUser);
		
		enterpriseUsers = baseDao.qureyAll("QueryNotChecked", enterpriseUser);
		
		session.setAttribute("page", enterpriseUser.getPagerBean());
		
		return "queryNotChecked";
	}
	
	/**
	 * 禁用此账号
	 * @return
	 */
	public String disableAccount(){
		
		int i = baseDao.update("DisableAccount", enterpriseUser.getEuId());
		
		 HttpSession session = ServletActionContext.getRequest().getSession();
		    session.setAttribute("accountName", enterpriseUser.getAccountName());
		    if(i>0){
		    	session.setAttribute("code", "DisableAccount");
		    	return "DisableAccount";
		    }else{
		    	session.setAttribute("code", "Not DisableAccount");
				return  "Not DisableAccount";
		    }
	}

	/**
	 * 通过审核
	 * @return
	 */
	public 	String throughAudit(){
		
	    int i  = baseDao.update("ThroughAudit", enterpriseUser.getEuId());
	    HttpSession session = ServletActionContext.getRequest().getSession();
	    session.setAttribute("accountName", enterpriseUser.getAccountName());
	    if(i>0){
	    	session.setAttribute("code", "ThroughAudit");
	    	return "ThroughAudit";
	    }else{
	    	session.setAttribute("code", "Not ThroughAudit");
			return  "Not ThroughAudit";
	    }
	}
	
	/**
	 * 修改账户信息之前
	 * @return
	 */
	public String toUpdateAccount(){
		HttpServletRequest request = ServletActionContext.getRequest();
		enterpriseUser = (Enterprise_User) baseDao.LoginCheck("toUpdateAccount", enterpriseUser);
		request.setAttribute("enterpriseUser", enterpriseUser);
		
		return "toUpdateAccount";
	}
	
	/**
	 * 修改企业账户
	 * @return
	 */
	public String updateAccount(){
		int i = baseDao.update("updateAccount", enterpriseUser);
		if(i>0){
			return "updateAccount";
		}
		return "Not updateAccount";
	}
	

	public Enterprise_User getEnterpriseUser() {
		return enterpriseUser;
	}

	public void setEnterpriseUser(Enterprise_User enterpriseUser) {
		this.enterpriseUser = enterpriseUser;
	}
	
	public BaseDao getBaseDao() {
		return baseDao;
	}


	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public List<Enterprise_User> getEnterpriseUsers() {
		return enterpriseUsers;
	}


	public void setEnterpriseUsers(List<Enterprise_User> enterpriseUsers) {
		this.enterpriseUsers = enterpriseUsers;
	}
	
}
