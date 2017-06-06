package com.jetsoon.util;

import net.sf.json.JSONObject;

import java.sql.SQLException;


public class ResponseData {
	
	private DBHelper db1 = null;
	
	
	/**
	 * 
	 * @param username
	 * @param passwrod
	 */
	public JSONObject CheckLogin(String username, String passwrod){
		
		JSONObject json = new JSONObject();
		
		try {
			//¿Í»§¶ËµÇÂ¼
			String sql = "select * from enterprise_user us, company_information c  where us.enterpriseInformationId = c.eiId and accountName =? and euPassword =?";
			
			db1 = new DBHelper(sql);
			
			db1.ps.setString(1, username);
			
			db1.ps.setString(2, passwrod);
			
			db1.doSelect();
			
			if(db1.rs.next()){
				

					json.put("euId", db1.rs.getInt("euId"));
					json.put("accountName", db1.rs.getString("accountName"));
					json.put("eiId", db1.rs.getString("eiId"));
					json.put("companyName", db1.rs.getString("companyName"));
					

			}else{
				
				return null;
			}
			
			db1.doClose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}

}
