package com.jetsoon.service;

import java.sql.SQLException;
import java.util.List;
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
	 * 验证企业账户 所有账户
	 * @param username 账号
	 * @param passwrod 密码
	 * @return
	 * @throws SQLException 
	 */
	Map<String,Object> LoginCheck(String username, String password) throws SQLException;
		
	
	/**
	 * 根据IMEI查询所属账户信息
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> findUserNameByIMEI(String IMEI) throws SQLException;
	
	/**
	 * 根据IMEI查询所属所有账户信息
	 * @param IMEI 唯一识别号
	 * @return 所有账户信息
	 * @throws SQLException
	 */
	List<Map<String,Object>> findAllAccountByIMEI(String IMEI) throws SQLException;

	
}
