package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title:�������˻��б����ݽӿ�</p>
 * <p>Description: �����������˻��б����ݿ�ӿ�</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface LockDroneDao {

    /**
     * ����IMEI ��ѯ ���˻�������Ϣ
     * @param IMEI
     * @return
     */
    Map<String,Object> findDroneByIMEI(String IMEI) throws SQLException;

    /**
     * �������˻�������Ϣ
     * @param IMEI
     * @param date
     */
    void  insertLockDrone(String IMEI,String date) throws SQLException;

    /**
     * ����IMEIɾ���������˻���Ϣ
     * @param IMEI
     */
    void  deleteLockDrone(String IMEI) throws SQLException;
}
