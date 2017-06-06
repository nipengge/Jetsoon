package com.jetsoon.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:������ʷ��Ϣ����ӿ�</p>
 * <p>Description: �����ṩ��ʷ��Ϣ�ķ���ӿ�</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface FlightHistoryService {

    /**
     * ¼�����˻��켣��Ϣ
     * @param historyInfo
     */
   void inputFlightHistoryInfo(Map<String,Object> historyInfo) throws SQLException;

    /**
     * ����IMEI�źͿ�ʼ����ʱ���ѯ��ʷ�켣��Ϣ
     * @param IMEI
     * @param startDate
     * @param endDate
     * @return
     */
   List<Map<String ,Object>> findFlightHistoryInfoByIMEI(String IMEI,String startDate,String endDate) throws SQLException;
}
