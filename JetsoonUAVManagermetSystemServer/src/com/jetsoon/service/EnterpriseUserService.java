package com.jetsoon.service;

import java.sql.SQLException;
import java.util.Map;


/**
 * 
 * <p>Title:企业账户数据层服务接口类</p>
 * <p>Description:操作企业账户表服务</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:11:23
 * @version 1.0
 */
public interface EnterpriseUserService {
	
	/**
	 * 验证企业账户
	 * @param username 账号
	 * @param passwrod 密码
	 * @return
	 * @throws SQLException 
	 */
	Map<String,Object> LoginCheck(String username, String passwrod) throws SQLException;
		
	
	/**
	 * 根据IMEI
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> findUserNameByIMEI(String IMEI) throws SQLException;
 
}
