/**
 * Coppyright (C)  2020 Luvina
 * TblUserLogicsImpl.java, Nov 17, 2020, BuiTienDung
 */
package Manageruser.logics.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import Manageruser.dao.TblUserDao;
import Manageruser.dao.impl.TblUserDaoImpl;
import Manageruser.entities.TblUserEntities;
import Manageruser.entities.UserInfor;
import Manageruser.logics.TblUserLogics;
import utils.Common;

/**
 * @author Bùi Tiến Dũng
 *
 */
public class TblUserLogicsImpl implements TblUserLogics {

	/**
	 * kieem tra tồn tại hay không
	 * 
	 * @param username Tên đăng nhập
	 * @param password mật khẩu
	 * @return Nếu tài khoản tồn tại thì return true, nếu không tồn tại thì return
	 *         false
	 */
	@Override
	public boolean checkExistLoginID(String username, String password) throws ClassNotFoundException {
		// Khởi tạo 1 đối tượng TblUserDaoImpl
		TblUserDaoImpl tbluser = new TblUserDaoImpl();
		TblUserEntities tb;

		boolean kt = true;

		try {
			// Lấy về 1 user
			tb = tbluser.getUserByUserName(username);
			if (tb != null) {
				Common cm = new Common();
				String tb1;

				// lấy pw,salt trong DB
				String passDB = tb.getPassword();
				String salt = tb.getSalt();
				// mã hóa mật khẩu
				tb1 = cm.EncodeCreatePassword(password, salt);
				// kiểm tra tồn tại
				kt = cm.compare(tb1, passDB);

			}
		} catch (NoSuchAlgorithmException | SQLException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return kt;

	}

	/**
	 * lay tong so user
	 * 
	 * @param groupId
	 * @param fullName
	 * @return tong so record
	 */
	public int getTotalRecords(int groupId, String fullName) {
		TblUserDao tb = new TblUserDaoImpl();
		return tb.getTotalUser(groupId, fullName);

	}

	/**
	 * lay danh sach user
	 * 
	 * @param ofset
	 * @param limit
	 * @param groupId
	 * @param sortType
	 * @param sortByFullName
	 * @param sortByCodeLevel
	 * @param sortByEndDate
	 * @return danh sach user
	 */
	public List<UserInfor> getListUsers(int ofset, int limit, int groupId,String fullName, String sortType, String sortByFullName,
			String sortByCodeLevel, String sortByEndDate) {
		TblUserDao tb = new TblUserDaoImpl();
		return tb.getListUsers(ofset, limit, groupId,fullName, sortType, sortByFullName, sortByCodeLevel, sortByEndDate);
	}

	
/**
 * 
 */
	@Override
	public String replaceWildcard(String fullName) {
		
		return(Common.replaceWildcard(fullName)) ;
	}
	public static void main(String[] args) {
		TblUserLogics tb = new TblUserLogicsImpl();
		try {
			System.out.println(tb.checkExistLoginID("dungdv", "123456"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
