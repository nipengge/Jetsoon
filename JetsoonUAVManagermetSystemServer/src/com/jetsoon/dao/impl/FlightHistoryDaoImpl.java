package com.jetsoon.dao.impl;

import com.jetsoon.dao.FlightHistoryDao;
import com.jetsoon.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:无人机飞行历史记录数据操作实现类</p>
 * <p>Description: 操作无人机飞行历史记录数据</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public class FlightHistoryDaoImpl implements FlightHistoryDao{

    /**
     * 添加无人机历史轨迹信息
     * @param historyInfo
     */
    
    public void insert(Map<String, Object> historyInfo) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("insert into flight_history value(null,?,?,?,?,?,?,?)"
                ,historyInfo.get("date"),(Double) historyInfo.get("lon"),
                (Double)historyInfo.get("lat"),(Float) historyInfo.get("voltage"),(Float) historyInfo.get("alt"),(Float) historyInfo.get("speed"),historyInfo.get("IMEI"));
    }

    /**
     * 根据时间和IMEI号查询无人机轨迹信息
     * @param IMEI
     * @param startDate
     * @param endDate
     * @return
     */
    
    public List<Map<String, Object>> findByIMEIDate(String IMEI, String startDate, String endDate) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from flight_history where imei = ? and flightTime > ? and flightTime < ?",new MapListHandler(),IMEI,startDate,endDate);
    }
}
