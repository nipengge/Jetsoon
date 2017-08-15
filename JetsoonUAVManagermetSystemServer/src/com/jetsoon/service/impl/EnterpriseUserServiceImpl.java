package com.jetsoon.service.impl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.jetsoon.dao.EnterpriseUserDao;
import com.jetsoon.dao.impl.EnterpriseUserDaoImpl;
import com.jetsoon.service.EnterpriseUserService;

/**
 * 
 * <p>Title:��ҵ�˺ű������</p>
 * <p>Description:������ҵ�˻�</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����03:28:22
 * @version 1.0
 */
public  class EnterpriseUserServiceImpl implements EnterpriseUserService {
	
	EnterpriseUserDao enterpriseUserDao = new EnterpriseUserDaoImpl();
	
	public Map<String, Object> LoginCheck(String userName, String password) throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> parentAccount =  enterpriseUserDao.LoginCheck(userName, password);
		
		if(parentAccount == null){
			
			return enterpriseUserDao.subAccountLogin(userName, password);
		}
		
		
		return parentAccount;
	}

	public Map<String, Object> findUserNameByIMEI(String IMEI)
			throws SQLException {
		// TODO Auto-generated method stub
		
		return enterpriseUserDao.findUserNameByIMEI(IMEI);
	}
	
	/**
	 * 根据IMEI查询所有账户信息包括 父账户和 子账户
	 */
	public List<Map<String, Object>> findAllAccountByIMEI(String IMEI)
			throws SQLException {
		// TODO Auto-generated method stub
		
		List<Map<String, Object>> subAccounts = enterpriseUserDao.findSubAccountByIMEI(IMEI);
		Map<String, Object> parentAccount =  enterpriseUserDao.findUserNameByIMEI(IMEI);
		
		if(subAccounts == null){
			subAccounts = new ArrayList<Map<String,Object>>();
		}
		
		if(parentAccount != null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", parentAccount.get("accountName"));
			subAccounts.add(map);
		}
		
		
		return subAccounts;
	}
}
