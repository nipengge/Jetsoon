package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:无人机飞行历史记录数据操作接口</p>
 * <p>Description: 操作无人机飞行历史记录数据接口</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public interface FlightHistoryDao {

    /**
     * 添加无人机历史轨迹信息
     * @param historyInfo
     */
    void insert(Map<String,Object> historyInfo) throws SQLException;

    /**
     * 根据时间和IMEI查询无人机历史轨迹信息
     * @param IMEI
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String,Object>> findByIMEIDate(String IMEI,String startDate,String endDate) throws SQLException;

}
