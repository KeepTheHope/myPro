package test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class JDBC批处理操作 {

	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private static final String DBURL = "jdbc:mysql://192.168.1.201:3306/ali";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public static void main(String[] args) throws Exception {
		Class.forName(DBDRIVER);
		Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
		Statement ps = null;
		File file = new File("C:/Users/HYTX/Desktop/hello1.txt");
		List<String> list = org.apache.commons.io.FileUtils.readLines(file, "UTF-8");
		System.err.println(list.size());
		ps = conn.createStatement();
		for (int x = 0; x < list.size(); x++) {
			String sql = list.get(x);
			ps.addBatch(sql);
			if (x % 10 == 0 && x != 0) {
				ps.executeBatch();
				ps.clearBatch();
			}
		}
		ps.executeBatch();
		ps.clearBatch();
		conn.close();
	}
}
