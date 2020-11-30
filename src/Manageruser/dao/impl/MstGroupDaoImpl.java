/**
 * Coppyright (C)  2020 Luvina
 * MstGroupDaoImpl.java, Nov 17, 2020, BuiTienDung
 */
package Manageruser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Manageruser.dao.MstGroupDao;
import Manageruser.entities.MstGroupEntities;

/**
 * @author Bùi Tiến Dũng
 *
 */
public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {
	// Connection conn=null;
	/**
	 * 
	 */
	@Override
	public List<MstGroupEntities> getAllMstGroup() {
		MstGroupEntities mst = null;
		List<MstGroupEntities> lstGr = new ArrayList<>();
		
		try {
			// tao kết nối
			connection();

			// kiem tra connect neu thanh cong
			if (conn != null) {
				// tao câu truy vấn
				String sql = "select group_id,group_name from mst_group ";
				// Thực hiện câu truy vấn
				PreparedStatement pstm = conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				// Nếu dữ liệu lấy ra 
				while (rs.next()) {
					mst = new MstGroupEntities();
					// Update dữ liệu mới cho group_id
					mst.setGroup_id(rs.getInt(1));
					// Update dữ liệu mới cho group_name
					mst.setGroup_name(rs.getString(2));
					lstGr.add(mst);
				}
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// Đóng kết nối
			closeConnection();
		}
		return lstGr;
	}
	public static void main(String[] args) {
		MstGroupDaoImpl ms = new MstGroupDaoImpl();
		System.out.println( ms.getAllMstGroup());
	}
}
