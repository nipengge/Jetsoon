package com.jetsoon.service.impl;

import com.jetsoon.dao.DroneInfoDao;
import com.jetsoon.dao.impl.DroneInfoDaoImpl;
import com.jetsoon.service.DroneInfoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:��ҵ�˻����ݲ����ʵ����</p>
 * <p>Description:������ҵ�˻������</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:11:23
 * @version 1.0
 */
public class DroneInfoServiceImpl implements DroneInfoService{

    DroneInfoDao droneInfoDao = new DroneInfoDaoImpl();

    /**
     * ����IMEI��ѯ���˻���Ϣ
     * @param IMEI 2GIMEI��
     * @return
     */
   
    public Map<String, Object> findByIEMI(String IMEI) throws SQLException {
        return droneInfoDao.findByIMEI(IMEI);
    }

    /**
     * ������ҵID��ѯ�����ɻ���Ϣ
     * @param companyId
     * @return
     * @throws SQLException
     */
    
    public List<Map<String, Object>> findByCompanyId(String companyId) throws SQLException {
        return droneInfoDao.findByCompanyId(companyId);
    }

    /**
     *
     * @param IMEI Ψһ��ʾ
     * @param onLine ����״̬
     */
    public void updateUAVOnLineStatus(String IMEI, String onLine) {

    }
}
