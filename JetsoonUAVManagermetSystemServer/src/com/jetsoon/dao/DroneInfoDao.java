package com.jetsoon.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:���˻���Ϣ���ݲ�ӿ�</p>
 * <p>Description:�������˻���Ϣ���ݿ��</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����04:04:49
 * @version 1.0
 */
public interface DroneInfoDao {

    /**
     * ����imei��ѯ���˻���Ϣ
     * @param IMEI  2Gģ��IEMI��
     * @return
     */
    Map<String,Object> findByIMEI(String IMEI) throws SQLException;

    /**
     * ������ҵ��ϢID��ѯ�����������˻���Ϣ
     * @companyId  ��ҵid
     * @return ����˾�����������˻���Ϣ
     * @throws SQLException
     */
    List<Map<String,Object>> findByCompanyId(String companyId) throws SQLException;

    /**
     * �޸�OnLie
     * @param IMEI Ψһ��ʾ
     * @param onLine ֵ
     */
    void updateOnLie(String IMEI,int onLine) throws SQLException;
}
