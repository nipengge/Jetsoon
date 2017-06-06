package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title:2G IMEI �����ݲ����ӿ�</p>
 * <p>Description: ����2G IMEI ���ݿ�ӿ�</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface IMEILibrayDao {

    /**
     * ���IMEI
     * @param IMEI
     * @param date
     */
    void insert(String IMEI,String date) throws SQLException;

    /**
     * ����IMEI�Ų�ѯIMEI��Ϣ
     * @param IMEI
     * @return
     */
    Map<String,Object> findByIMEI(String IMEI) throws SQLException;

}
