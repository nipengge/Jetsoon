package com.jetsoon.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Statement;

/**
 * 
 * <p>Title:����Դ������</p>
 * <p>Description:��������Դ</p>
 * <p>Company:www.jetsoon.cn</p>
 * @author nipengge
 * @date:2017-4-11 ����02:55:42
 * @version 1.0
 */
public class C3P0Util {
	
	//��������Դ
	private static DataSource dataSource = new ComboPooledDataSource();
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnection(){
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("����������");
		}
		
	}

	public static void release(Connection conn,Statement statement,ResultSet resultSet){
		//�ر���Դ
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
		}
		
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement = null;
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conn = null;
		}
	}
	

}
