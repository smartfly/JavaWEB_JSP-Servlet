package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBHelper {
	private static final String driver = "com.mysql.jdbc.Driver";// ���ݿ�����
	// �������ݿ��URL��ַ
	private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";// ���ݿ���û���
	private static final String password = "";// ���ݿ������

	private static Connection conn = null;

	// ��̬���븺���������
	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����ģʽ�������ݿ����Ӵ���
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
				System.out.println("���ݿ���������");
			} else {
				System.out.println("���ݿ������쳣");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
