package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title:锁死无人机列表数据接口</p>
 * <p>Description: 操作锁死无人机列表数据库接口</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public interface LockDroneDao {

    /**
     * 根据IMEI 查询 无人机锁死信息
     * @param IMEI
     * @return
     */
    Map<String,Object> findDroneByIMEI(String IMEI) throws SQLException;

    /**
     * 插入无人机锁死信息
     * @param IMEI
     * @param date
     */
    void  insertLockDrone(String IMEI,String date) throws SQLException;

    /**
     * 根据IMEI删除锁死无人机信息
     * @param IMEI
     */
    void  deleteLockDrone(String IMEI) throws SQLException;
}
