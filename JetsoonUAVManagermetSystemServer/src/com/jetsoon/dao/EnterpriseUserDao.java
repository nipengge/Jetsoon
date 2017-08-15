package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * 
 * <p>Title:��ҵ�˻�Dao�ӿ���</p>
 * <p>Description:������ҵ�˻����ݿ��</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface EnterpriseUserDao {
	
	/**
	 * ��֤�˺�
	 * @param username �˺�
	 * @param passwrod ����
	 * @return
	 * @throws SQLException 
	 */
	Map<String,Object> LoginCheck(String username, String password) throws SQLException;
	
	
	Map<String,Object> subAccountLogin(String userName,String password) throws SQLException;
	
	/**
	 * ����IMEI��ѯ������ҵ���ʻ���Ϣ
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> findUserNameByIMEI(String IMEI) throws SQLException;
	
	/**
	 * ����IMEI��ѯ�����˻�����
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> findSubAccountByIMEI(String IMEI) throws SQLException;


}
