package com.jetsoon.dao.impl;

import com.jetsoon.dao.DroneInfoDao;
import com.jetsoon.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:无人机信息数据层接口</p>
 * <p>Description:操作无人机信息数据库表</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public class DroneInfoDaoImpl implements DroneInfoDao {

    /**
     * 根据IEMI号查询无人机信息
     * @param IMEI  2G模块IEMI号
     * @return 无人机信息
     */
    
    public Map<String, Object> findByIMEI(String IMEI) throws SQLException {

        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

        return qr.query("select * from drone_info where droneId =?",new MapHandler(),IMEI);
    }

    /**
     * 根据企业ID查询下属所有无人机信息
     * @companyId 企业ID
     * @return
     * @throws SQLException
     */
    
    public List<Map<String, Object>> findByCompanyId(String companyId) throws SQLException {

        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

        return qr.query("select * from drone_info dinfo where dinfo.droneCompanyId =?",new MapListHandler(),companyId);
    }

    /**
     * 修改锁死状态
     * @param IMEI 唯一标识
     * @param onLine 上锁状态
     * 0标识没有锁死 ， 1标识锁死状态
     */
    public void updateOnLie(String IMEI, int onLine) throws SQLException {
    	
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

        qr.update("update drone_info set onLine = ? where droneId = ? ",IMEI,onLine);
    }
    
}
