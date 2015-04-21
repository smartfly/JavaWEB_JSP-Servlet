package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBHelper {
	private static final String driver = "com.mysql.jdbc.Driver";// 数据库驱动
	// 连接数据库的URL地址
	private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";// 数据库的用户名
	private static final String password = "";// 数据库的密码

	private static Connection conn = null;

	// 静态代码负责加载驱动
	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 单例模式返回数据库连接创建
	public static Connection getConnection() throws SQLException {
		if (conn == null) {
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
			return conn;
		}
		return conn;
	}

	public static void main(String[] args) {
		try {
			Connection connection = DBHelper.getConnection();
			if (connection != null) {
				System.out.println("数据库连接正常");
			} else {
				System.out.println("数据库连接异常");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
