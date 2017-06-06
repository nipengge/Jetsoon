package com.jetsoon.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:无人机信息服务层接口</p>
 * <p>Description:操作无人机信息数据库表</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public interface DroneInfoService {

    /**
     * 根据IMEI查询无人机信息
     * @param IMEI
     * @return
     */
    Map<String,Object> findByIEMI(String IMEI) throws SQLException;

    /**
     * 根据企业信息ID查询下属所有无人机信息
     * @companyId  企业id
     * @return 本公司下属所有无人机信息
     * @throws SQLException
     */
    List<Map<String,Object>> findByCompanyId(String companyId) throws SQLException;

    /**
     * 修改无人机在线状态
     * @param IMEI 唯一标示
     * @param onLine 在线状态
     */
    void  updateUAVOnLineStatus(String IMEI,String onLine);
}
