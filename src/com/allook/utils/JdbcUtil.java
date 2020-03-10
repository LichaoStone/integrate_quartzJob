package com.allook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 数据库连接
 * 
 * @author tangming
 * @date 2013-10-31
 */
public class JdbcUtil {

	/** 数据库连接 */
	private Connection conn = null;

	private static Logger log = Logger.getLogger(JdbcUtil.class);

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * 建立连接
	 * 
	 * @param dbtype 连接数据库类型 0：生产库 1：统计库
	 * @throws Exception
	 */
	public void init(int dbtype) throws Exception {
		String url = "";
		String userName = "";
		String pwd = "";
		String driverClassName = "";

		// 连接生产库
		if (dbtype == 0) {
			url = ConfigUtil.getValue("jdbc.url");
			userName = ConfigUtil.getValue("jdbc.username");
			pwd = ConfigUtil.getValue("jdbc.password");
			driverClassName = ConfigUtil.getValue("jdbc.driverClassName");

			// 连接统计库
		} else if (dbtype == 1) {
			url = ConfigUtil.getValue("jdbc1.url");
			userName = ConfigUtil.getValue("jdbc1.username");
			pwd = ConfigUtil.getValue("jdbc1.password");
			driverClassName = ConfigUtil.getValue("jdbc1.driverClassName");

			// 连接后台生产库
		} else if (dbtype == 2) {
			url = ConfigUtil.getValue("jdbc2.url");
			userName = ConfigUtil.getValue("jdbc2.username");
			pwd = ConfigUtil.getValue("jdbc2.password");
			driverClassName = ConfigUtil.getValue("jdbc2.driverClassName");
		} else {
			log.info("数据库连接参数不详，请修改!");
			return;
		}

		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, userName, pwd);

			// 连接生产库
			if (dbtype == 0) {
				log.info("生产库连接建立成功! " + conn);

				// 连接统计库
			} else if (dbtype == 1) {
				log.info("统计库连接建立成功! " + conn);
			} else {
				log.info("后台生产库连接建立成功! " + conn);
			}
		} catch (ClassNotFoundException e) {
			log.info("com.mysql.jdbc.Driver not founded");
			throw new Exception(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取数据
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDataList(String sql) throws Exception {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] name = new String[count];
			for (int i = 0; i < count; i++) {
				name[i] = rsmd.getColumnName(i + 1);
			}
			rs.last();
//			int rows = rs.getRow();
			rs.beforeFirst();
			while (rs.next()) {
				HashMap<String, String> hashMap = new HashMap<String, String>();
//				String tmp = rs.getString(1);
				try {
					for (int i = 1; i <= count; i++) {
						hashMap.put(name[i - 1], rs.getString(i));
					}
				} catch (Exception e) {

				}
				list.add(hashMap);
			}
		} catch (SQLException e) {
			log.info("method.getDateList.SQLException");
			list = null;
			throw new Exception(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取数字型数据
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Integer getCount(String sql) throws Exception {
		int a = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int count = rsmd.getColumnCount();
			while (rs.next()) {
				String tmp = rs.getString(1);
				a = a + Integer.parseInt(tmp);
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return a;
	}

	/**
	 * 更新数据
	 * 
	 * @param sql
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void insert_update(String sql) throws Exception {
		Statement stmt;
		int rs = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * 更新数据 -- 返回自增主键
	 * 
	 * @param sql
	 * @throws Exception
	 */
	public int insertForKey(String sql) throws Exception {

		Statement stmt;
		ResultSet rs = null;
		int key = 0;
		try {
			log.info("创建连接开始：" + System.currentTimeMillis());
			stmt = conn.createStatement();
			log.info("创建连接结束：" + System.currentTimeMillis());
			log.info("执行开始：" + System.currentTimeMillis());
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			conn.commit();
			log.info("执行结束：" + System.currentTimeMillis());
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}
		}
		return key;
	}

	/**
	 * 关闭连接
	 */
	public void closeConn() {
		try {
			conn.close();
			log.info("数据库连接关闭成功! " + conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
