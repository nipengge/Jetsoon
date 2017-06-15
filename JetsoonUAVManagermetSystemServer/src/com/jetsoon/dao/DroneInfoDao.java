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
     * 根据企业ID查询下属所有无人机信息
     * @companyId  企业ID
     * @currentPage 当前第几页
     * @return 本公司下属所有无人机信息
     * @throws SQLException
     */
    List<Map<String,Object>> findByCompanyId(String companyId,int currentPage) throws SQLException;
    
    /**
     * 
    * @Title: qureyDronInfo
    * @Description: TODO(查询所有无人机并分页,默认10条)
    * @param @param currenPage 当前第几页
    * @param @return 无人机信息列表
    * @param @throws SQLException    设定文件
    * @return List<Map<String,Object>>    返回类型
    * @throws 异常
     */
    List<Map<String, Object>> qureyDronInfo(int currenPage) throws SQLException;

    /**
     * 修改OnLie
     * @param IMEI 唯一标示
     * @param onLine 值
     */
    void updateOnLie(String IMEI,int onLine) throws SQLException;
}
