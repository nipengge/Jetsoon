package com.jetsoon.service;

import java.sql.SQLException;
import java.util.List;
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
	 * ��֤��ҵ�˻� �����˻�
	 * @param username �˺�
	 * @param passwrod ����
	 * @return
	 * @throws SQLException 
	 */
	Map<String,Object> LoginCheck(String username, String password) throws SQLException;
		
	
	/**
	 * ����IMEI��ѯ�����˻���Ϣ
	 * @param IMEI
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> findUserNameByIMEI(String IMEI) throws SQLException;
	
	/**
	 * ����IMEI��ѯ���������˻���Ϣ
	 * @param IMEI Ψһʶ���
	 * @return �����˻���Ϣ
	 * @throws SQLException
	 */
	List<Map<String,Object>> findAllAccountByIMEI(String IMEI) throws SQLException;

	
}
