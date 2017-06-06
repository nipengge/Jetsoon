package com.jetsoon.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.jetsoon.dao.PlantProtectionHistoryDao;
import com.jetsoon.util.C3P0Util;
/**
*
* <p>Title:植保历史记录数据实现类</p>
* <p>Description: 操作植保历史列表数据库实现类</p>
* <p>Company:www.jetsoon.cn</p>
* @author nipengge
* @date:2017-4-11 下午04:04:49
* @version 1.0
*/
public class PlantProtectionHistoryDaoImpl implements PlantProtectionHistoryDao {

	public List<Map<String, Object>> findPlantProtectionHistoryByIMEI(
			String IMEI) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from plant_protection_history where IMEI = ?", new MapListHandler(),IMEI);
	}

	public void inputPlantProtectionHistory(String uavName,String IMEI, String startDate,
			String endDate, String cropperName, float sprayingWidth) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		
		qr.update("insert into plant_protection_history values(null,?,?,?,?,?,?) ", uavName,IMEI,startDate,endDate,cropperName,sprayingWidth);
	}

}
