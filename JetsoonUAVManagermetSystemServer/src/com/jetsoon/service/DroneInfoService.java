package com.jetsoon.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:���˻���Ϣ�����ӿ�</p>
 * <p>Description:�������˻���Ϣ���ݿ��</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface DroneInfoService {

    /**
     * ����IMEI��ѯ���˻���Ϣ
     * @param IMEI
     * @return
     */
    Map<String,Object> findByIEMI(String IMEI) throws SQLException;

    /**
     * ������ҵ��ϢID��ѯ�����������˻���Ϣ
     * @companyId  ��ҵid
     * @return ����˾�����������˻���Ϣ
     * @throws SQLException
     */
    List<Map<String,Object>> findByCompanyId(String companyId) throws SQLException;

    /**
     * �޸����˻�����״̬
     * @param IMEI Ψһ��ʾ
     * @param onLine ����״̬
     */
    void  updateUAVOnLineStatus(String IMEI,String onLine);
}
