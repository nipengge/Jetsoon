package com.jetsoon.service;

import java.sql.SQLException;
import java.util.Map;


/**
 * 
 * <p>Title:��ҵ�˻����ݲ����ӿ���</p>
 * <p>Description:������ҵ�˻������</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:11:23
 * @version 1.0
 */
public interface EnterpriseUserService {
	
	/**
	 * ��֤��ҵ�˻�
	 * @param username �˺�
	 * @param passwrod ����
	 * @return
	 * @throws SQLException 
	 */
	Map<String,Object> LoginCheck(String username, String passwrod) throws SQLException;
		
	
	/**
	 * ����IMEI
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> findUserNameByIMEI(String IMEI) throws SQLException;
 
}
