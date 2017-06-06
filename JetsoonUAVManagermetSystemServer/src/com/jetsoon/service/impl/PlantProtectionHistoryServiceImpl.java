package com.jetsoon.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jetsoon.dao.PlantProtectionHistoryDao;
import com.jetsoon.dao.impl.PlantProtectionHistoryDaoImpl;

/**
*
* <p>Title:ֲ����ʷ��¼����ʵ����</p>
* <p>Description:����ֲ����ʷ��¼�����</p>
* <p>Company:www.jetsoon.cn</p>
* @author nipengge
* @date:2017-4-11 ����04:11:23
* @version 1.0
*/
public class PlantProtectionHistoryServiceImpl implements
		com.jetsoon.service.PlantProtectionHistoryService {
	
	PlantProtectionHistoryDao plantProtectionHistoryDao = new PlantProtectionHistoryDaoImpl();
	
	public List<Map<String, Object>> findPlantProtectionHistoryByIMEI (
			String IMEI) throws SQLException {
		// TODO Auto-generated method stub
		return plantProtectionHistoryDao.findPlantProtectionHistoryByIMEI(IMEI);
	}

	public void inputPlantProtectionHistory(String uavName,String IMEI, String startDate,
			String endDate, String cropperName, float sprayingWidth) throws SQLException {
		// TODO Auto-generated method stub
		plantProtectionHistoryDao.inputPlantProtectionHistory(uavName,IMEI, startDate, endDate, cropperName, sprayingWidth);
	}

}
