package com.jetsoon.service;

import java.sql.SQLException;

/**
 *
 * <p>Title:2G IMEI库</p>
 * <p>Description: 2G IMEI 数据库操作服务接口</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:04:49
 * @version 1.0
 */
public interface IMEILibrayService {

    /**
     *  2G IMEI 入库接口
     * @param IMEI
     * @param date
     */
    void add2GIMEI(String IMEI,String date) throws SQLException;

    /**
     *  验证此IMEI是否存在
     * @param IMEI
     * @return
     */
    boolean isIMEI(String IMEI) throws SQLException;

}
