package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.Map;

import com.jetsoon.bean.Enterprise_User;

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
	Map<String,Object> LoginCheck(String username, String passwrod) throws SQLException;
	
	
	/**
	 * ����IMEI��ѯ������ҵ���ʻ���Ϣ
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> findUserNameByIMEI(String IMEI) throws SQLException;


}
