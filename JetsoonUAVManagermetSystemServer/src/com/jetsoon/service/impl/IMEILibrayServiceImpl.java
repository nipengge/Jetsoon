package com.jetsoon.service.impl;

import com.jetsoon.dao.IMEILibrayDao;
import com.jetsoon.dao.impl.IMEILibrayDaoImpl;
import com.jetsoon.service.IMEILibrayService;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title: 2G IMEI库服务接口实现类</p>
 * <p>Description:操作2G IMEI 库信息</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:11:23
 * @version 1.0
 */
public class IMEILibrayServiceImpl implements IMEILibrayService {

    IMEILibrayDao imeiLibrayDao = new IMEILibrayDaoImpl();

    /**
     * 入库2G模块
     * @param IMEI
     * @param date
     * @throws SQLException
     */
    
    public void add2GIMEI(String IMEI, String date) throws SQLException {
        imeiLibrayDao.insert(IMEI,date);
    }

    /**
     * 验证2G模块是否存在
     * @param IMEI
     * @return
     * @throws SQLException
     */
    
    public boolean isIMEI(String IMEI) throws SQLException {

         Map map = imeiLibrayDao.findByIMEI(IMEI);

         if(map != null){
             return true;
         }

        return false;
    }
}
