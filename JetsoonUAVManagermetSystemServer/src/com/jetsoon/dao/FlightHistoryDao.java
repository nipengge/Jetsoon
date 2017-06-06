package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:���˻�������ʷ��¼���ݲ����ӿ�</p>
 * <p>Description: �������˻�������ʷ��¼���ݽӿ�</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface FlightHistoryDao {

    /**
     * ������˻���ʷ�켣��Ϣ
     * @param historyInfo
     */
    void insert(Map<String,Object> historyInfo) throws SQLException;

    /**
     * ����ʱ���IMEI��ѯ���˻���ʷ�켣��Ϣ
     * @param IMEI
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String,Object>> findByIMEIDate(String IMEI,String startDate,String endDate) throws SQLException;

}
