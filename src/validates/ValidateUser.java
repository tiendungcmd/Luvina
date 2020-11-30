/**
 * Coppyright (C)  2020 Luvina
 * ValidateUser.java, Nov 17, 2020, BuiTienDung
 */
package validates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Manageruser.logics.TblUserLogics;
import Manageruser.logics.impl.TblUserLogicsImpl;
import Manageruser.properties.DatabaseProperties;
import Manageruser.properties.MessageErrorProperties;

/**
 * @author Bùi Tiến Dũng
 *
 */
public class ValidateUser {
	/**
	 * Kiểm tra thông tin về tìa khoản đăng nhập vào hệ thống
	 * 
	 * @param username dl người dùng nhập
	 * @param password dl người dùng nhập
	 * @return trả về danh sách lỗi
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<String> validateLogin(String username, String password) throws SQLException, ClassNotFoundException {

		TblUserLogicsImpl tblUerLogics = new TblUserLogicsImpl();
		List<String> lstErr = new ArrayList<String>();

		// kiểm tra nhập username hoặc password có rỗng hay không
		if ("".equals(username)) {
			// Thêm câu thông báo lỗi vào danh sách
			lstErr.add(MessageErrorProperties.getValueByKey("ER001_loginName"));
		}
		if ("".equals(password)) {
			// Thêm câu thông báo lỗi vào danh sách
			lstErr.add(MessageErrorProperties.getValueByKey("ER001_password"));
		}
		// kiểm tra listErr có trống không và kiểm tra tính đúng đắn của username password
		if (lstErr.isEmpty() && !tblUerLogics.checkExistLoginID(username, password)) {
			// Thêm câu thông báo lỗi vào danh sách
			lstErr.add(MessageErrorProperties.getValueByKey("ER016"));
		}
		return lstErr;
	}

	
}
