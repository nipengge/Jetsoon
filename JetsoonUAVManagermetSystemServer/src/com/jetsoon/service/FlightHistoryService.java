package com.jetsoon.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:飞行历史信息服务接口</p>
 * <p>Description: 对外提供历史信息的服务接口</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public interface FlightHistoryService {

    /**
     * 录入无人机轨迹信息
     * @param historyInfo
     */
   void inputFlightHistoryInfo(Map<String,Object> historyInfo) throws SQLException;

    /**
     * 根据IMEI号和开始结束时间查询历史轨迹信息
     * @param IMEI
     * @param startDate
     * @param endDate
     * @return
     */
   List<Map<String ,Object>> findFlightHistoryInfoByIMEI(String IMEI,String startDate,String endDate) throws SQLException;
}
