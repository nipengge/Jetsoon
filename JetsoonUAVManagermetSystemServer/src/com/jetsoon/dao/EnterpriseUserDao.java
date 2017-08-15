package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * 
 * <p>Title:企业账户Dao接口类</p>
 * <p>Description:操作企业账户数据库表</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public interface EnterpriseUserDao {
	
	/**
	 * 验证账号
	 * @param username 账号
	 * @param passwrod 密码
	 * @return
	 * @throws SQLException 
	 */
	Map<String,Object> LoginCheck(String username, String password) throws SQLException;
	
	
	Map<String,Object> subAccountLogin(String userName,String password) throws SQLException;
	
	/**
	 * 根据IMEI查询所属企业的帐户信息
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> findUserNameByIMEI(String IMEI) throws SQLException;
	
	/**
	 * 根据IMEI查询所有账户名称
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> findSubAccountByIMEI(String IMEI) throws SQLException;


}
