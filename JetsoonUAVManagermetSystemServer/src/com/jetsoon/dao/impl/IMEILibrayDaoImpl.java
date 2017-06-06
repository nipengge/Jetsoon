package com.jetsoon.dao.impl;


import com.jetsoon.dao.IMEILibrayDao;
import com.jetsoon.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title:2G IMEI �����ݲ���ʵ����</p>
 * <p>Description: ����2G IMEI ���ݿ�ӿ�</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public class IMEILibrayDaoImpl implements IMEILibrayDao{

    /**
     * ���һ����¼
     * @param IMEI
     * @param date
     * @throws SQLException
     */

    public void insert(String IMEI, String date) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

        qr.update("insert into imei_library value (null,?,?)",IMEI,date);
    }

    /**
     * ����IMEI��ѯIMEI��Ϣ
     * @param IMEI
     * @return
     */

    public Map<String, Object> findByIMEI(String IMEI) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

        return qr.query("select * from imei_library where imei = ?",new MapHandler(),IMEI);
    }
}
