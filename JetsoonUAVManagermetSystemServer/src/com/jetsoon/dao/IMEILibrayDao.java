package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title:2G IMEI 库数据操作接口</p>
 * <p>Description: 操作2G IMEI 数据库接口</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public interface IMEILibrayDao {

    /**
     * 添加IMEI
     * @param IMEI
     * @param date
     */
    void insert(String IMEI,String date) throws SQLException;

    /**
     * 根据IMEI号查询IMEI信息
     * @param IMEI
     * @return
     */
    Map<String,Object> findByIMEI(String IMEI) throws SQLException;

}
