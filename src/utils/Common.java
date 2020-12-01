/**
 * Coppyright (C)  2020 Luvina
 * Common.java, Nov 17, 2020, BuiTienDung
 */
package utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import Manageruser.properties.MessageErrorProperties;

/**
 * @author Bùi Tiến Dũng
 *
 */
public class Common {
	/**
	 * hàm mã hóa password
	 * 
	 * @param pw   mat khẩu
	 * @param salt salt
	 * @return Chuoi đã mã hóa
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public String EncodeCreatePassword(String pw, String salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String saltIp = pw + salt;
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(saltIp.getBytes("UTF-8"));
		return new BigInteger(1, crypt.digest()).toString(16);
	}

	// kiểm tra mã hoa
	/**
	 * So sáng pass do người dùng nhập vào sau khi được mã hóa với pass lấy ra được
	 * trong DB
	 * 
	 * @param pass   pass do người dùng nhập vào sau khi được mã hóa
	 * @param passDB pass lấy ra được trong DB
	 * @return nếu giống nhau trả về true, nếu khác nhau trả về false
	 */
	public boolean compare(String pass, String passDB) {
		// Nếu 1 trong 2 pass bị null
		if (pass == null || passDB == null) {
			//
			return false;
		}
		return pass.equals(passDB);
	}

	/**
	 * Kiểm tra xem session đã tồn tại chưa
	 * 
	 * @param ss tên session
	 * @return
	 */
	// kiem tra login session
	public static boolean checkLogin(HttpSession ss) {
		boolean check = true;
		if ("".equals(ss.getAttribute("loginName")))
			check = false;
		return check;
	}

	/**
	 * Tao chuoi paging
	 * 
	 * @param totalUser
	 * @param limit
	 * @param currentPage
	 * @return
	 */
	public static List<Integer> getListPaging(int totalUser, int limit, int currentPage) {
		List<Integer> lst = new ArrayList<>();
		int count = totalUser / limit;

//		if(currentPage>page) {
//			
//		}
		for (int i = 1; i <= count; i++) {
			lst.add(i);
		}
		return lst;
	}

	/**
	 * lay vi tri data can lay
	 * 
	 * @param currentPage
	 * @param limit
	 * @return vi tri data can lay
	 */
	public static int getOffset(int currentPage, int limit) {
		return currentPage * limit - limit + 1;
	}

	/**
	 * lay so luong hien thi ban ghi tren 1 trang
	 * 
	 * @return
	 */
	public static int getLimit() {
		MessageErrorProperties me = new MessageErrorProperties();
		return Integer.parseInt(me.getValueByKey("RECORD"));
	}

	/**
	 * tinh tong so trang
	 * 
	 * @return
	 */
	public static int getTotalPage(int totalUser, int limit) {
		int totalPage = totalUser / limit;
		int totalPage1 = totalUser % limit;
		if (totalPage1 != 0) {
			return totalPage + 1;
		}
		return totalPage;
	}
/**
 *  thay the các kí tự đặc biệt 
 * @param fullName
 * @return
 */
	public static String replaceWildcard(String fullName) {
		fullName.replace("\\", "\\\\");

		fullName.replace("%", "\\%");

		fullName.replace("_", "\\_");

		return fullName;
	}

	public static void main(String[] args) {
		Common cm = new Common();
		try {
			System.out.println(cm.EncodeCreatePassword("123456", "d1"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
