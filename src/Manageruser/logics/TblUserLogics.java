/**
 * Coppyright (C)  2020 Luvina
 * TblUserLogics.java, Nov 17, 2020, BuiTienDung
 */
package Manageruser.logics;


import Manageruser.logics.impl.TblUserLogicsImpl;
import utils.Common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import Manageruser.dao.TblUserDao;
import Manageruser.dao.impl.TblUserDaoImpl;
import Manageruser.entities.TblUserEntities;
import Manageruser.entities.UserInfor;
/**
 * @author Bùi Tiến Dũng
 *
 */
public interface TblUserLogics {
	public boolean checkExistLoginID(String username, String password) throws ClassNotFoundException ;
	public List<UserInfor> getListUsers(int ofset,int limit,int grId,String fullName,String sortType,String sortByFullName,String sortByCodeLevel,String sortByEndDate);
	public String replaceWildcard(String fullName);
}
