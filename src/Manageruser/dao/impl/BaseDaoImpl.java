/**
 * Coppyright (C)  2020 Luvina
 * BaseDaoImpl.java, Nov 17, 2020, BuiTienDung
 */
package Manageruser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Manageruser.dao.BaseDao;

/**
 * @author Bùi Tiến Dũng
 *
 */
public class BaseDaoImpl implements BaseDao {
	Connection conn = null;
	/**
	 *Tạo kết nối
	 */
	public void connection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("da ket noi");
		} catch (SQLException | ClassNotFoundException e) {
			
			System.out.println("Ket noi that bai");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Đóng kết nối tới DB
	 */
	public void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			} else {
				return;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
	}
	

}
