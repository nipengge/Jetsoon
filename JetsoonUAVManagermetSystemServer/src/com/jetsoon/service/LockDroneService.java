package com.jetsoon.service;

import java.sql.SQLException;

/**
 *
 * <p>Title:锁死无人机列表服务接口</p>
 * <p>Description: 操作锁死无人机列表数据库服务接口</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public interface LockDroneService {

    /**
     * 验证此IMEI是否存在
     * @param IMEI
     * @return
     */
    boolean isDroneIMEI(String IMEI) throws SQLException;

    /**
     * 根据IMEI删除
     * @param IMEI
     */
    void deleteLockDrone(String IMEI) throws SQLException;

    /**
     * 插入锁死记录
     */
    void insertLockDrone(String IMEI,String date) throws SQLException;
}
