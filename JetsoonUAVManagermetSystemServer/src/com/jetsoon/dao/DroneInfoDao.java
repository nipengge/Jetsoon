package com.jetsoon.dao;

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
public interface DroneInfoDao {

    /**
     * 根据imei查询无人机信息
     * @param IMEI  2G模块IEMI号
     * @return
     */
    Map<String,Object> findByIMEI(String IMEI) throws SQLException;

    /**
     * 根据企业信息ID查询下属所有无人机信息
     * @companyId  企业id
     * @return 本公司下属所有无人机信息
     * @throws SQLException
     */
    List<Map<String,Object>> findByCompanyId(String companyId) throws SQLException;

    /**
     * 修改OnLie
     * @param IMEI 唯一标示
     * @param onLine 值
     */
    void updateOnLie(String IMEI,int onLine) throws SQLException;
}
