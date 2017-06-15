package com.jetsoon.service.impl;

import com.jetsoon.dao.DroneInfoDao;
import com.jetsoon.dao.impl.DroneInfoDaoImpl;
import com.jetsoon.service.DroneInfoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title:企业账户数据层服务实现类</p>
 * <p>Description:操作企业账户表服务</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午04:11:23
 * @version 1.0
 */
public class DroneInfoServiceImpl implements DroneInfoService{

    DroneInfoDao droneInfoDao = new DroneInfoDaoImpl();

    /**
     * 根据IMEI查询无人机信息
     * @param IMEI 2GIMEI号
     * @return
     */
   
    public Map<String, Object> findByIEMI(String IMEI) throws SQLException {
        return droneInfoDao.findByIMEI(IMEI);
    }

    /**
     * 根据企业ID查询下属飞机信息
     * @param companyId
     * @param role
     * @return
     * @throws SQLException
     */
    
    public List<Map<String, Object>> findByCompanyId(String companyId,int role,int currentPage) throws SQLException {
    	
    	if(role == 0){
    		return droneInfoDao.qureyDronInfo(currentPage);
    	}
    	
        return droneInfoDao.findByCompanyId(companyId,currentPage);
    }
    

    /**
     *
     * @param IMEI 唯一标示
     * @param onLine 在线状态
     */
    public void updateUAVOnLineStatus(String IMEI, int onLine) throws SQLException {
    	droneInfoDao.updateOnLie(IMEI, onLine);
    }

    /**
    * 此无人机是否锁死
    * @param IMEI 唯一标示
    * @return 
    * @throws SQLException 
    */
	public boolean isDroneLock(String IMEI) throws SQLException {
		// TODO Auto-generated method stub
		
		Map<String,Object> map = droneInfoDao.findByIMEI(IMEI);
		
		int onLine = (Integer) map.get("onLine");
		
		if(onLine == 1){//锁死状态
			
			return true;
		}
		//正常状态
		return false;
	}
    
}
