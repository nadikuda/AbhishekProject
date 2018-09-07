/***********************AUTHOR : ABHISHEK KAUHSIK (155164)*********************************/

/* **************Establishing connection between code and Database*************** */
package com.capgemini.asset.dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtilB {
	public static Connection getConn(){
			
			Connection conn = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@10.219.34.3:1521:orcl","trg211","training211");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return conn;
			
		}
}
