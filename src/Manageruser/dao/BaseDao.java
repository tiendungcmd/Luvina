/**
 * Coppyright (C)  2020 Luvina
 * BaseDao.java, Nov 17, 2020, BuiTienDung
 */
package Manageruser.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Manageruser.dao.impl.BaseDaoImpl;
import Manageruser.logics.TblUserLogics;

/**
 * @author Bùi Tiến Dũng
 *
 */
public  interface BaseDao {
	public static final String url = "jdbc:mysql://localhost:3306/21_buitiendung_manageuser";
	public static final String user = "root";
	public static final String password = "0000";

	public void connection() throws ClassNotFoundException, SQLException;

	public void closeConnection();
	}
	

