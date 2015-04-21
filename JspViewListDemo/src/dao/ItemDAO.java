package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import util.DBHelper;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import entity.Items;

//商品的业务逻辑类
public class ItemDAO {

	// 获得所有商品信息
	public ArrayList<Items> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>();// 商品集合
		try {
			conn = DBHelper.getConnection();
			String sql = "Select * from items;";// SQL语句
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
				list.add(items);// 把商品加入集合
			}
			return list;// 返回集合
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 释放语句对象
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
	//根据商品编号获取商品资料
	public Items getItemsById(int id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "Select * from items where id=?;";// SQL语句
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
				return null;// 返回空
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 释放语句对象
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
	//获得最近浏览的前五条商品信息
	public ArrayList<Items> getViewList(String list){
		ArrayList<Items> itemList = new ArrayList<Items>();
		int iCount = 5;//每次返回前五条记录
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
