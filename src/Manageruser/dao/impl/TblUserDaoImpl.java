/**
 * Coppyright (C)  2020 Luvina
 * TblUserDaoImpl.java, Nov 17, 2020, BuiTienDung
 */
package Manageruser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import Manageruser.dao.TblUserDao;
import Manageruser.entities.TblUserEntities;
import Manageruser.entities.UserInfor;
import utils.Constant;

/**
 * @author Bùi Tiến Dũng
 *
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {
	/**
	 * Lấy ra thông tin của user
	 * 
	 * @param userName tên đăng nhập
	 * @param pass     mật khẩu
	 * @param salt     đoạn mã thêm vào khi mã hóa mật khẩu return Trả về một user
	 *                 nếu tồn tại, trả về null nếu không tôn tại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public TblUserEntities getUserByUserName(String userName) throws SQLException, ClassNotFoundException {
		// Khởi tạo một đối tượng TblUserEntities =
		TblUserEntities user = new TblUserEntities();
		try {
			// tạo kết nối đến DB
			connection();
			// Nếu connect thành công
			if (conn != null) {
				// Tạo câu truy vấn sql

				String sql = "SELECT pass, salt FROM tbl_user WHERE login_name = ?";
				// Thực hiện câu truy vấn bằng PreparedStatement
				PreparedStatement pre = conn.prepareStatement(sql);
				// Truyền vào câu sql tham số userName
				pre.setString(1, userName);
				// Thực hiện câu truy vấn
				ResultSet rs = pre.executeQuery();
				// Nếu dữ liệu lấy ra khác rỗng
				if (rs.next()) {
					// Update dữ liệu mới cho pass
					user.setPassword(rs.getString(1));
					// Update dữ liệu mới cho salt
					user.setSalt(rs.getString(2));
				}
			}
			// Nếu bắt được lỗi
		} catch (SQLException e) {
			// ghi log
			e.printStackTrace();
			System.out.println("Lỗi");
		} finally {
			// Đóng kết nối
			closeConnection();
		}
		return user;
	}

	/**
	 * Lấy tổng số user
	 * 
	 * @param groupID
	 * @param fullName
	 * @return
	 */
	@Override
	public int getTotalUser(int groupID, String fullName) {
		int count = 0;
		try {
			connection();
			if (conn != null) {
				// tạo câu truy vấn sql
				StringBuilder sql = new StringBuilder(
						"SELECT count(*) FROM tbl_user u  inner join mst_group g on u.group_id=g.group_id where u.rule= ? ");

				if (groupID != 0) {
					sql.append(" and u.group_id= ? ");

				}
				if (!"".equals(fullName) && !fullName.equals(null)) {
					sql.append(" and u.full_name like ?   ");
				}

				PreparedStatement pre = conn.prepareStatement(sql.toString());
				// truyen vao sql tham so
				int i = 1;
				pre.setInt(i++, Constant.RULE);
				pre.setInt(i++, groupID);
				pre.setString(i++, "%" + fullName + "%");
				// thu hien cau truy van
				ResultSet rs = pre.executeQuery();
				// lay rs dau tien
				rs.next();
				count = rs.getInt(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// dong ket noi
			closeConnection();
		}
		return count;
	}

	// abc a = new abc(); //blank

	// abc a; // null
	//
	//
	/**
	 * Lay danh sach user
	 */
	@Override
	public List<UserInfor> getListUsers(int ofset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {

		List<UserInfor> lstUser = new ArrayList<>();
		try {
			// tao ket noi
			connection();
			// kiem tra ket noi thanh cong
			if (conn != null) {
				// tao cau truy van
				StringBuilder sql = new StringBuilder(
						"select u.user_id,u.full_name,u.birthday,u.email,u.tel , gr.group_name, jp.name_level, de.end_date,de.total");
				sql.append(" from tbl_user u left join mst_group gr on u.group_id = gr.group_id");
				sql.append(" left join tbl_detail_user_japan de on u.user_id = de.user_id");
				sql.append(" left join mst_japan jp on de.code_level = jp.code_level where u.rule= ? ");
				sql.append("and u.group_id = ? OR ?  = 0 ");
				sql.append("and u.full_name like ? ");
				sql.append(" ORDER BY " +  sortType);
				sql.append(" LIMIT ? OFFSET ? ");
				
				//gán giá trị truyền vào 
				int i = 1;
				PreparedStatement pre = conn.prepareStatement(sql.toString());
				pre.setInt(i++, Constant.RULE);
				pre.setInt(i++, groupId);
				pre.setInt(i++, groupId);
				pre.setString(i++, "%" + fullName + "%");
				pre.setInt(i++, limit);
				pre.setInt(i++, ofset);
			//	pre.setString(i++, sortType);
				// thu hien cau truy van
				ResultSet rs = pre.executeQuery();
				// khoi tao doi tuong userinfor
				while (rs.next()) {
					UserInfor us = new UserInfor();
					us.setUser_id(rs.getInt("user_id"));
					us.setFull_name(rs.getString("full_name"));
					us.setBirthday(rs.getDate("birthday"));
					us.setTel(rs.getInt("tel"));
					us.setEmail(rs.getString("email"));
					us.setGroup_name(rs.getString("group_name"));
					us.setName_level(rs.getString("name_level"));
					us.setEnd_date(rs.getDate("end_date"));
					us.setTotal(rs.getInt("total"));
					lstUser.add(us);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// dongket nois
			closeConnection();
		}
		// TODO Auto-generated method stub
		return lstUser;
	}
	public  boolean getColumn(String column, String table) throws SQLException {
		ArrayList<String> listColumn = new ArrayList<String>();
		try {
			ResultSet rs = conn.createStatement().executeQuery("SHOW COLUMNS FROM " + table);
			while (rs.next()) {
				listColumn.add(rs.getString(1));
			}
			if (column != null && listColumn.contains(column)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return false;
	}
	public static void main(String[] args) {
		TblUserDao tb = new TblUserDaoImpl();
		
		System.out.println(tb.getListUsers(0, 5, 0, "","full_name","","",""));
	}
}
