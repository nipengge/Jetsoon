package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
*
* <p>Title:植保历史数据层接口</p>
* <p>Description:操作植保历史数据库表</p>
* <p>Company:www.jetsoon.cn</p>
* @author nipengge
* @date:2017-4-11 下午04:04:49
* @version 1.0
*/
public interface PlantProtectionHistoryDao {
	
	/**
	 * 录入植保历史记录
	 * @param IMEI 设备ID
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param cropperName 农作名称
	 * @param sprayingWidth 喷洒宽度
	 */
	void inputPlantProtectionHistory(String uavName,String IMEI, String startDate,
			String endDate, String cropperName, float sprayingWidth) throws SQLException;
	
	/**
	 * 根据IMEI查询植保历史
	 * @param IMEI
	 * @throws SQLException 
	 */
	List<Map<String,Object>> findPlantProtectionHistoryByIMEI(String IMEI) throws SQLException;

}
