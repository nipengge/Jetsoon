package com.jetsoon.service.impl;

import com.jetsoon.dao.LockDroneDao;
import com.jetsoon.dao.impl.LockDroneDaoImpl;
import com.jetsoon.service.LockDroneService;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title:锁死无人机列表服务实现类</p>
 * <p>Description: 操作锁死无人机列表数据库服务实现类</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public class LockDroneServiceImpl implements LockDroneService {

    LockDroneDao lockDroneDao = new LockDroneDaoImpl();

    public boolean isDroneIMEI(String IMEI) throws SQLException {

        Map map =  lockDroneDao.findDroneByIMEI(IMEI);

        if(map != null){
            return true;
        }

        return false;
    }

    public void deleteLockDrone(String IMEI) throws SQLException {
        lockDroneDao.deleteLockDrone(IMEI);
    }

    public void insertLockDrone(String IMEI, String date) throws SQLException {
        lockDroneDao.insertLockDrone(IMEI,date);
    }
}
