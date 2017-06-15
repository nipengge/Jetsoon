package com.jetsoon.dao.impl;

import com.jetsoon.dao.DroneInfoDao;
import com.jetsoon.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

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
public class DroneInfoDaoImpl implements DroneInfoDao {

    /**
     * ����IEMI�Ų�ѯ���˻���Ϣ
     * @param IMEI  2Gģ��IEMI��
     * @return ���˻���Ϣ
     */
    
    public Map<String, Object> findByIMEI(String IMEI) throws SQLException {

        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

        return qr.query("select * from drone_info where droneId =?",new MapHandler(),IMEI);
    }

    /**
     * ������ҵID��ѯ�����������˻���Ϣ
     * @companyId ��ҵID
     * @currentPage ��ǰ�ڼ�ҳ
     * @return
     * @throws SQLException
     */
    
    public List<Map<String, Object>> findByCompanyId(String companyId,int currentPage) throws SQLException {

        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

        return qr.query("select * from drone_info dinfo where dinfo.droneCompanyId =? limit "+(currentPage-1)*10+",10",new MapListHandler(),companyId);
    }

    /**
     * �޸�����״̬
     * @param IMEI Ψһ��ʶ
     * @param onLine ����״̬
     * 0��ʶû������ �� 1��ʶ����״̬
     */
    public void updateOnLie(String IMEI, int onLine) throws SQLException {
    	
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

        qr.update("update drone_info set onLine = ? where droneId = ? ",IMEI,onLine);
    }

	/* (�� Javadoc) 
	* <p>Title: qureyDronInfo</p> 
	* <p>Description: ��ҳ��ѯ�������˻��б�</p> 
	* @param currenPage
	* @return
	* @throws SQLException 
	* @see com.jetsoon.dao.DroneInfoDao#qureyDronInfo(int) 
	*/
	public List<Map<String, Object>> qureyDronInfo(int currentPage)
			throws SQLException {
		// TODO Auto-generated method stub
		 QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

	     return qr.query("select * from drone_info dinfo limit "+(currentPage-1)*10+",10",new MapListHandler());
	}
    
    
}
