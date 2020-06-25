package org.edwith.webbe.cardmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	public static Connection getConnetion() throws Exception {
		String url = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=UTC";
		String user = "wanni";
		String pw = "wanni0928";
		Connection conn = null;
		// 드라이버 로딩
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		// java sql 패키지가 가지고 있는 DriverManager라고 하는 클래스로 getConnection이라는 메소드 사용,
		// 매개변수로는 DB의 URL, user, password로 전달.
		try {
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	public static void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
