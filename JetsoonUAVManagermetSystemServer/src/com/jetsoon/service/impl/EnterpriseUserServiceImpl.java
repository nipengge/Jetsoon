package com.jetsoon.service.impl;


import java.sql.SQLException;
import java.util.Map;


import com.jetsoon.dao.EnterpriseUserDao;
import com.jetsoon.dao.impl.EnterpriseUserDaoImpl;
import com.jetsoon.service.EnterpriseUserService;

/**
 * 
 * <p>Title:企业账号表服务类</p>
 * <p>Description:操作企业账户</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午03:28:22
 * @version 1.0
 */
public  class EnterpriseUserServiceImpl implements EnterpriseUserService {
	
	EnterpriseUserDao enterpriseUserDao = new EnterpriseUserDaoImpl();
	
	public Map<String, Object> LoginCheck(String username, String passwrod) throws SQLException {
		// TODO Auto-generated method stub
			return enterpriseUserDao.LoginCheck(username, passwrod);
	}

	public Map<String, Object> findUserNameByIMEI(String IMEI)
			throws SQLException {
		// TODO Auto-generated method stub
		
		return enterpriseUserDao.findUserNameByIMEI(IMEI);
	}
}
