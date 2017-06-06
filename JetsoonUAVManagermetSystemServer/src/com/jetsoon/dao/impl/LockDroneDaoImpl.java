package com.jetsoon.dao.impl;

import com.jetsoon.dao.LockDroneDao;
import com.jetsoon.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title:锁死无人机列表数据实现类</p>
 * <p>Description: 操作锁死无人机列表数据库实现类</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public class LockDroneDaoImpl implements LockDroneDao {

    public Map<String, Object> findDroneByIMEI(String IMEI) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from lock_drone where IMEI=?",new MapHandler(),IMEI);
    }

    public void insertLockDrone(String IMEI, String date) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("insert into lock_drone value(null,?,?)",IMEI,date);
    }

    public void deleteLockDrone(String IMEI) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("delete from lock_drone where IMEI = ?",IMEI);
    }
}
