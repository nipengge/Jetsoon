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
     * ������ҵID��ѯ�����������˻���Ϣ
     * @companyId  ��ҵID
     * @currentPage ��ǰ�ڼ�ҳ
     * @return ����˾�����������˻���Ϣ
     * @throws SQLException
     */
    List<Map<String,Object>> findByCompanyId(String companyId,int currentPage) throws SQLException;
    
    /**
     * 
    * @Title: qureyDronInfo
    * @Description: TODO(��ѯ�������˻�����ҳ,Ĭ��10��)
    * @param @param currenPage ��ǰ�ڼ�ҳ
    * @param @return ���˻���Ϣ�б�
    * @param @throws SQLException    �趨�ļ�
    * @return List<Map<String,Object>>    ��������
    * @throws �쳣
     */
    List<Map<String, Object>> qureyDronInfo(int currenPage) throws SQLException;

    /**
     * �޸�OnLie
     * @param IMEI Ψһ��ʾ
     * @param onLine ֵ
     */
    void updateOnLie(String IMEI,int onLine) throws SQLException;
}
