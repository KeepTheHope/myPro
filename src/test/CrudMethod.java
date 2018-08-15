package test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.DataSources;
import com.mysql.jdbc.Statement;

import utils.ConvertorUtil;

public class CrudMethod {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private static final String DBURL = "jdbc:mysql://192.168.1.201:3306/ali";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	public static Logger logger = Logger.getLogger(CrudMethod.class);
	private static final String JDBC_DRIVER = "driverClass";
	private static final String JDBC_URL = "jdbcUrl";

	private static DataSource ds;
	public static Connection con = null;
	/** 
	* 初始化连接池代码块 
	*/
	static {
		initDBSource();
	}

	/** 
	 * 初始化c3p0连接池 
	 */
	private static final void initDBSource() {
		Properties c3p0Pro = new Properties();
		try {
			// 加载配置文件
			c3p0Pro.load(CrudMethod.class.getResourceAsStream("/c3p0.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String drverClass = c3p0Pro.getProperty(JDBC_DRIVER);
		if (drverClass != null) {
			try {
				// 加载驱动类
				Class.forName(drverClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

		Properties jdbcpropes = new Properties();
		Properties c3propes = new Properties();
		for (Object key : c3p0Pro.keySet()) {
			String skey = (String) key;
			if (skey.startsWith("c3p0.")) {
				c3propes.put(skey, c3p0Pro.getProperty(skey));
			} else {
				jdbcpropes.put(skey, c3p0Pro.getProperty(skey));
			}
		}

		try {
			// 建立连接池
			DataSource unPooled = DataSources.unpooledDataSource(c3p0Pro.getProperty(JDBC_URL), jdbcpropes);
			ds = DataSources.pooledDataSource(unPooled, c3propes);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * 获取数据库连接对象 
	 * @return 数据连接对象 
	 * @throws SQLException  
	 */
	public static synchronized Connection getConnection() throws SQLException {
		final Connection con = ds.getConnection();
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		return con;
	}
	// 1. ResultSet executeQuery(String sql); 执行SQL查询，并返回ResultSet 对象。
	// 2.int executeUpdate(String sql); 可执行增，删，改，返回执行受到影响的行数。
	// 3. boolean execute(String sql); 可执行任何SQL语句，返回一个布尔值，表示是否返回ResultSet 。

	/**
	 * 执行新增插入语句，返回执行受到影响的行数(String)。
	 * @return
	 */
	public static int insertToInt(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int b = 0;
		if (sql.equals("") || sql == null) {
			return b;
		}
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			b = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("执行新增插入语句，返回执行受到影响的行数(String)=" + sql);
		} finally {
			closeConnBatch(conn, rs, ps, null);
		}
		logger.info("执行新增插入语句，返回执行受到影响的行数(String)=" + b);
		return b;
	}

	/**
	 * 执行修改语句，返回执行受到影响的行数(String)。
	 * @return
	 */
	public static int UpdateToInt(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int b = 0;
		if (sql.equals("") || sql == null) {
			return b;
		}
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			b = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("执行修改语句，返回执行受到影响的行数(String)=" + sql);
		} finally {
			closeConnBatch(conn, rs, ps, null);
		}
		logger.info("执行修改语句，返回执行受到影响的行数(String)=" + b);
		return b;
	}

	/**
	 * 删除数据库方法，返回boolean;
	 * @return
	 */
	public static boolean DelTableInfo(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean b = false;
		if (sql.equals("") || sql == null) {
			return b;
		}
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
			if (i == 1) {
				b = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("删除数据库方法，返回boolean==" + sql);
		} finally {
			closeConnBatch(conn, rs, ps, null);
		}
		return b;
	}

	/**
	 * 可执行删除返回执行受到影响的行数(String)。
	 * @return
	 */
	public static int deleteToInt(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int b = 0;
		if (sql.equals("") || sql == null) {
			return b;
		}
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			b = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("可执行删除返回执行受到影响的行数(String)=" + sql);
		} finally {
			closeConnBatch(conn, rs, ps, null);
		}
		logger.info("删除数据成功条数" + b);
		return b;
	}

	/**
	 * 查询数据库方法，返回list<String>
	 * 
	 * @author yangjianrui
	 */
	public static List<String> selectInfo(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (sql.equals("") || sql == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// 可以得到有多少列
			int columnNum = rsmd.getColumnCount();
			// 将数据封装到list中
			while (rs.next()) {
				list.add(rs.getObject(columnNum).toString());
				/*
				 * Object[] objects = new Object[columnNum]; for (int i = 0; i < objects.length;
				 * i++) { objects[i] = rs.getObject(i + 1); } list.add(objects.toString());
				 */
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("查询数据库方法，返回list<String>==" + sql);
		} finally {
			closeConnBatch(conn, rs, ps, null);
		}
		logger.info("查询数据库方法，返回list<String>==" + list.size());
		return list;
	}

	/**
	 * 查询数据库方法，返回list<String>
	 * 
	 * @author yangjianrui
	 */
	public static List<Map<String, Object>> selectInfoListMap(String sql) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (sql.equals("") || sql == null) {
			return null;
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// 可以得到有多少列
			int columnNum = rsmd.getColumnCount();
			// 将数据封装到list中
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnNum; i++) {
					rowData.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("查询数据库方法，返回list<String>的==" + sql);
		} finally {
			// closeConnBatch(con, null,null);
			closeConnBatch(conn, rs, ps, null);
		}
		logger.info("执行查询数据库方法，返回list<String>数据成功条数" + list.size());
		return list;
	}

	/**
	 * 
	 * @describe:执行批量插入
	 * @author: yangjianrui
	 * @time: 2017-5-27上午9:15:35
	 * @param sqllist
	 * @return
	 */
	public static int InsertListInt(List<Object> sql) {

		int[] numa = {};
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int b = 0;
		if (sql.equals("") || sql == null) {
			return b;
		}
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 设置手动提交
			ps = (PreparedStatement) conn.createStatement();
			for (int a = 0; a < sql.size(); a++) {
				ps.addBatch(sql.get(a).toString());
			}
			numa = ps.executeBatch();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("执行批量插入==" + sql);
		} finally {
			closeConnBatch(conn, rs, ps, null);
		}
		logger.info("执行批量插入数据成功条数" + numa.length);
		return numa.length;
	}

	/**
	 * 
	 * @describe:执行批量插入（占位符）
	 * @author: yangjianrui
	 * @time: 2017-5-27上午9:15:35
	 * @param sqllist
	 * @return
	 * @throws Exception 
	 */
	public static int InsertListPlaceInt(String sql, List<String[]> list) throws Exception {
		Class.forName(DBDRIVER) ;
		Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD) ;
		
		PreparedStatement ps = null;
		int[] numa = {};
		int b = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			conn.setAutoCommit(false); // 设置手动提交
			int size = list.size();
			Object[] o = null;
			for (int i = 0; i < size; i++) {
				o = list.get(i);
				for (int j = 0; j < o.length; j++) {
					ps.setObject(j + 1, o[j]);
				}
				ps.addBatch();
			}

			// ps.executeBatch();
			numa = ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info("执行批量插入（占位符）" + sql);
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
				logger.info("执行批量插入（占位符）" + sql);
			}
		} finally {
			closeConnBatch(conn, null, ps, null);
		}
		logger.info("执行（占位符）批量插入数据成功条数" + numa.length);
		return numa.length;
	}

	/**
	 * 关闭连接
	 * @param conn
	 * @param rs
	 */
	public static void closeConnBatch(Connection conn, ResultSet rs, PreparedStatement ps, Statement sql_statement) {
		try {
			if (rs != null) {
				// System.out.println("关闭成功rs！");
				rs.close();
			}
			if (sql_statement != null) {
				sql_statement.close();
			}

			if (ps != null) {
				// System.out.println("关闭成功ps！");
				ps.close();
			}
			if (conn != null) {
				// System.out.println("关闭成功conn！");
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 可执行增，删，改，返回执行受到影响的行数(String)。
	 * @return
	 */
	public static int insetIntoInfoError(Map<String, Object> map) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int b = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(map.get("sql").toString());
			b = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("可执行增，删，改，返回执行受到影响的行数(String)==" + map);
		} finally {
			closeConnBatch(conn, rs, ps, null);
		}
		logger.info("执行（占位符）批量插入数据成功条数" + b);
		return b;
	}
	
	public static Integer insert(List<Map<String, Object>> list, int dataglag, int type) throws Exception {
		Integer intoNum = 0;
		StringBuffer sql = new StringBuffer();
		List<String[]> lists = new ArrayList<String[]>();
		sql.append(
				"INSERT INTO rbc_slot_word (word_id, dict_id, name, alias, create_time, create_user_id, ent_id, space) VALUES (?,?,?,?,?,?,?,?)");
		String[] strList = new String[8];
		for (int x = 0; x < list.size(); x++) {
			if (list.get(x).get("word_id") != null) {
				strList[0] = list.get(x).get("word_id").toString();
			}
			if (list.get(x).get("dict_id") != null) {
				strList[1] = list.get(x).get("dict_id").toString();
			}
			if (list.get(x).get("name") != null) {
				strList[2] = list.get(x).get("name").toString();
			}
			strList[3] = null;
			strList[4] = null;
			strList[5] = null;
			strList[6] = "ROBOT23";
			strList[7] = null;
			lists.add(strList);
			if (x != 0 && (x % 5000 == 0 || list.size() == x + 1)) {
				intoNum = intoNum + CrudMethod.InsertListPlaceInt(sql.toString(), lists);
				lists = new ArrayList<String[]>();
			}
		}
		return intoNum;
	}
	public static void main(String[] args) throws Exception {
		File file = new File("C:/Users/HYTX/Desktop/hello1.txt");
		List<String> list = org.apache.commons.io.FileUtils.readLines(file, "UTF-8");
		for(int x = 0;x < list.size();x ++) {
			
		}
		
		
	}
}
