package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 负责数据库连接定义的程序类
 * @author mldn
 * 只要你进行了该类对象的实例化操作，则类的内部会自动帮助用户处理数据库连接；
 */
public class DatabaseConnection implements AutoCloseable {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver" ;
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:MLDN" ;
	private static final String USER = "scott" ;
	private static final String PASSWORD = "tiger" ;
	private Connection conn ;	// 由于DatabaseConnetion需要描述连接，所以将连接类型定义为属性
	/**
	 * 提供无参构造方法主要的目的是获取数据库连接，即：实例化本类对象就具有了数据库连接对象
	 */
	public DatabaseConnection() {
		try {	// 一旦连接出现了错误，整个程序都无发执行
			Class.forName(DBDRIVER) ;
			this.conn = DriverManager.getConnection(DBURL, USER, PASSWORD) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 取得一个数据库的连接对象，如果没有连接则返回null
	 * @return 实例化的Connection接口对象
	 */
	public Connection getConnection() {
		return this.conn ;
	}
	/**
	 * 进行数据库的关闭处理。
	 */
	public void close() {
		if (this.conn != null) {	// 避免NullPointerException
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
}
