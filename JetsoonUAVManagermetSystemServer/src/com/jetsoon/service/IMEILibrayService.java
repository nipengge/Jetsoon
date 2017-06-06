package com.jetsoon.service;

import java.sql.SQLException;

/**
 *
 * <p>Title:2G IMEI��</p>
 * <p>Description: 2G IMEI ���ݿ��������ӿ�</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface IMEILibrayService {

    /**
     *  2G IMEI ���ӿ�
     * @param IMEI
     * @param date
     */
    void add2GIMEI(String IMEI,String date) throws SQLException;

    /**
     *  ��֤��IMEI�Ƿ����
     * @param IMEI
     * @return
     */
    boolean isIMEI(String IMEI) throws SQLException;

}
