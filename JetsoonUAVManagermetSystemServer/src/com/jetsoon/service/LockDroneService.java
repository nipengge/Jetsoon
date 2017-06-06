package com.jetsoon.service;

import java.sql.SQLException;

/**
 *
 * <p>Title:�������˻��б����ӿ�</p>
 * <p>Description: �����������˻��б����ݿ����ӿ�</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface LockDroneService {

    /**
     * ��֤��IMEI�Ƿ����
     * @param IMEI
     * @return
     */
    boolean isDroneIMEI(String IMEI) throws SQLException;

    /**
     * ����IMEIɾ��
     * @param IMEI
     */
    void deleteLockDrone(String IMEI) throws SQLException;

    /**
     * ����������¼
     */
    void insertLockDrone(String IMEI,String date) throws SQLException;
}
