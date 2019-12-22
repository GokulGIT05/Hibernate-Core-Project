package com.gk1.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionTest {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "hbstudent";
		String password = "hbstudent";
		try {
			System.out.println("Connecting to Database" + jdbcUrl);
			Connection mycon = DriverManager.getConnection(jdbcUrl, user, password);

			System.out.println("Connection is successfull!!!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
