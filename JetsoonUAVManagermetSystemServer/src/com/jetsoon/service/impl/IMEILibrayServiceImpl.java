package com.jetsoon.service.impl;

import com.jetsoon.dao.IMEILibrayDao;
import com.jetsoon.dao.impl.IMEILibrayDaoImpl;
import com.jetsoon.service.IMEILibrayService;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * <p>Title: 2G IMEI�����ӿ�ʵ����</p>
 * <p>Description:����2G IMEI ����Ϣ</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:11:23
 * @version 1.0
 */
public class IMEILibrayServiceImpl implements IMEILibrayService {

    IMEILibrayDao imeiLibrayDao = new IMEILibrayDaoImpl();

    /**
     * ���2Gģ��
     * @param IMEI
     * @param date
     * @throws SQLException
     */
    
    public void add2GIMEI(String IMEI, String date) throws SQLException {
        imeiLibrayDao.insert(IMEI,date);
    }

    /**
     * ��֤2Gģ���Ƿ����
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
