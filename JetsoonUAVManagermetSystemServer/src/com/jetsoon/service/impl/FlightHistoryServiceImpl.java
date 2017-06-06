package com.jetsoon.service.impl;

import com.jetsoon.dao.FlightHistoryDao;
import com.jetsoon.dao.impl.FlightHistoryDaoImpl;
import com.jetsoon.service.FlightHistoryService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:飞行历史信息服务实现类</p>
 * <p>Description: 对飞行数据操作的服务类</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public class FlightHistoryServiceImpl implements FlightHistoryService{

    FlightHistoryDao flightHistoryDao = new FlightHistoryDaoImpl();

    /**
     * 录入无人机飞行信息
     * @param historyInfo
     */
    
    public void inputFlightHistoryInfo(Map<String, Object> historyInfo) throws SQLException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        historyInfo.put("date",simpleDateFormat.format(new Date()));

        flightHistoryDao.insert(historyInfo);
    }

    /**
     *  根据时间和IMEI号查询无人机轨迹信息
     * @param IMEI
     * @param startDate
     * @param endDate
     * @return
     */
    
    public List<Map<String, Object>> findFlightHistoryInfoByIMEI(String IMEI, String startDate, String endDate) throws SQLException {
        return flightHistoryDao.findByIMEIDate(IMEI,startDate,endDate);
    }
}
