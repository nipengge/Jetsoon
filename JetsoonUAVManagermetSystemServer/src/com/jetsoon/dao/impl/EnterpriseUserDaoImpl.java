package com.jetsoon.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import com.jetsoon.dao.EnterpriseUserDao;
import com.jetsoon.util.C3P0Util;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

/**
 * 
 * <p>Title:企业账户数据管理类</p>
 * <p>Description:操作账户信息</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 下午03:41:26
 * @version 1.0
 */
public class EnterpriseUserDaoImpl implements EnterpriseUserDao  {

	public Map<String, Object> LoginCheck(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		
			QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
					
			return qr.query
					("select * from enterprise_user us, company_information c  where us.enterpriseInformationId " +
									"= c.eiId and accountName =? and euPassword =?",
						new MapHandler(),username,password);
	}

	public Map<String, Object> findUserNameByIMEI(String IMEI)
			throws SQLException {
		// TODO Auto-generated method stub
		
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		
		return qr.query("select  accountName  from drone_info dinfo, company_information ci,enterprise_user eu  where dinfo.droneId =? and " +
				"ci.eiId = dinfo.droneCompanyId and eu.enterpriseInformationId = ci.eiId", new MapHandler(),IMEI);
	}

	public List<Map<String, Object>> findSubAccountByIMEI(String IMEI)
			throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		
		return qr.query("select userName  from sub_account sub,enterprise_user u,drone_info d,company_information c " +
				"where droneId = ? and droneCompanyId = eiId and parentId=euid and eiid = enterpriseInformationId",new MapListHandler(),IMEI);
	}

	public Map<String, Object> subAccountLogin(String userName, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		
		return qr.query("select euid,userName as'accountName',password as 'euPassword',statu,registerDateTime,role,enterpriseInformationId,c.* " +
				"from sub_account sub,enterprise_user e,company_information c " +
				"where  eiId = enterpriseInformationId and euId = parentId and userName=? and password = ?", new MapHandler(),userName,password);
	}

	
	
	
	

}
