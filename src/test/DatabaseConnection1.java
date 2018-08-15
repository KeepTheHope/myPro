package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection1 {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	
	
	private static final String DBURL = "jdbc:mysql://192.168.1.201:3306/ali";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) throws Exception {
		Class.forName(DBDRIVER) ;
		Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD) ;
		long currentPage = 2 ; 
		long lineSize = 5 ;
		String sql = "SELECT mid,name FROM member LIMIT ?,?" ;
		PreparedStatement pstmt = conn.prepareStatement(sql) ;
		pstmt.setLong(1, (currentPage - 1) * lineSize);
		pstmt.setLong(2, lineSize); 
		ResultSet rs = pstmt.executeQuery() ;	// 执行查询
		while (rs.next()) {
			long mid = rs.getLong(1) ;
			String name = rs.getString(2) ;
			System.out.println("mid = " + mid + "、name = " + name);
		}
		conn.close();
	}
}
