package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import util.DBHelper;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import entity.Items;

//��Ʒ��ҵ���߼���
public class ItemDAO {

	// ���������Ʒ��Ϣ
	public ArrayList<Items> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>();// ��Ʒ����
		try {
			conn = DBHelper.getConnection();
			String sql = "Select * from items;";// SQL���
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = (ResultSet) stmt.executeQuery();
			while (rs.next()) {
				Items items = new Items();
				items.setId(rs.getInt("id"));
				items.setName(rs.getString("name"));
				items.setCity(rs.getString("city"));
				items.setNumber(rs.getInt("number"));
				items.setPrice(rs.getInt("price"));
				items.setPicture(rs.getString("picture"));
				list.add(items);// ����Ʒ���뼯��
			}
			return list;// ���ؼ���
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
	//������Ʒ��Ż�ȡ��Ʒ����
	public Items getItemsById(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "Select * from items where id=?;";// SQL���
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = (ResultSet) stmt.executeQuery();
			if (rs.next()) {
				Items items = new Items();
				items.setId(rs.getInt("id"));
				items.setName(rs.getString("name"));
				items.setCity(rs.getString("city"));
				items.setNumber(rs.getInt("number"));
				items.setPrice(rs.getInt("price"));
				items.setPicture(rs.getString("picture"));
				return items;
			}else {
				return null;// ���ؿ�
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	//�����������ǰ������Ʒ��Ϣ
	public ArrayList<Items> getViewList(String list){
		ArrayList<Items> itemList = new ArrayList<Items>();
		int iCount = 5;//ÿ�η���ǰ������¼
		if (list!=null&&list.length()>0) {
			String[] arr = list.split(",");
			if (arr.length >= 5) {
				for (int i = arr.length-1; i >= arr.length-iCount; i--) {
					itemList.add(getItemsById(Integer.parseInt(arr[i])));
				}
			}else {
				for (int i = arr.length-1; i >= 0; i--) {
					itemList.add(getItemsById(Integer.parseInt(arr[i])));
				}
			}
			return itemList;
		}else {
			return null;
		}
	}
}
