package com.jetsoon.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
*
* <p>Title:ֲ����ʷ��¼������ӿ�</p>
* <p>Description: ����ֲ����ʷ��¼�����ݿ����ӿ�</p>
* <p>Company:www.jetsoon.cn</p>
* @author nipengge
* @date:2017-4-11 ����04:04:49
* @version 1.0
*/
public interface PlantProtectionHistoryService {
	
	/**
	 * ¼��ֲ����ʷ��¼
	 * @param IMEI �豸ID
	 * @param startDate ��ʼʱ��
	 * @param endDate ����ʱ��
	 * @param cropperName ũ������
	 * @param sprayingWidth ��������
	 */
	void inputPlantProtectionHistory(String uavName,String IMEI,String startDate,String endDate,String cropperName,float sprayingWidth) throws SQLException;
	
	/**
	 * ����IMEI��ѯֲ����ʷ
	 * @param IMEI
	 */
	List<Map<String,Object>> findPlantProtectionHistoryByIMEI(String IMEI) throws SQLException;

}