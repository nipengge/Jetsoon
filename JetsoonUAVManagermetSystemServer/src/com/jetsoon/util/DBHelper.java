package com.jetsoon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DBHelper {
	
	public Connection conn = null;
	
	public PreparedStatement ps = null;
	
	public ResultSet rs = null;
	
	public int us;
	
	
	public DBHelper (String sql){
		
		try {
			
			String url="jdbc:mysql://localhost:3306/uavassociationmanagerment?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true";
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			conn = DriverManager.getConnection(url,"root","Public168");
			
			ps = conn.prepareStatement(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void doSelect(){
		try {
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	public void doUpdate(){
		try {
			us = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public void doClose(){
		try {
			if(rs != null ){
				rs.close();
			}
			
			if(ps != null){
				ps.close();
			}
			
			if(conn != null){
				conn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
