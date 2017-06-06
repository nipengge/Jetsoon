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
 * <p>Title:������ʷ��Ϣ����ʵ����</p>
 * <p>Description: �Է������ݲ����ķ�����</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public class FlightHistoryServiceImpl implements FlightHistoryService{

    FlightHistoryDao flightHistoryDao = new FlightHistoryDaoImpl();

    /**
     * ¼�����˻�������Ϣ
     * @param historyInfo
     */
    
    public void inputFlightHistoryInfo(Map<String, Object> historyInfo) throws SQLException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        historyInfo.put("date",simpleDateFormat.format(new Date()));

        flightHistoryDao.insert(historyInfo);
    }

    /**
     *  ����ʱ���IMEI�Ų�ѯ���˻��켣��Ϣ
     * @param IMEI
     * @param startDate
     * @param endDate
     * @return
     */
    
    public List<Map<String, Object>> findFlightHistoryInfoByIMEI(String IMEI, String startDate, String endDate) throws SQLException {
        return flightHistoryDao.findByIMEIDate(IMEI,startDate,endDate);
    }
}
