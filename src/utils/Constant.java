/**
 * Coppyright (C)  2020 Luvina
 * Constant.java, Nov 17, 2020, BuiTienDung
 */
package utils;

/**
 * @author LA-PM
 *
 */
public class Constant {

	//public static final String PROPERTIES_DATABASE_PATH = null;

	// đường dẫn đến database.properties
			public static final String PROPERTIES_DATABASE_PATH = "properties/database.properties";
			// đường dẫn đến message_ja.properties
			public static final String PROPERTIES_MESSAGE_PATH = "properties/message_ja.properties";
			// đường dẫn đến message_ja.properties
			public static final String PROPERTIES_MESSAGE_ERROR_PATH = "properties/message_err_ja.properties";
			// đường link đến ADM001.jsp
			public static final String LINK_ADM001_JSP = "view/jsp/ADM001.jsp";
			// đường link đến ADM002.jsp
			public static final String LINK_ADM002_JSP = "view/jsp/ADM002.jsp";
			public static final String SYSTEM_ERROR_JSP = "view/jsp/system_error.jsp";
			// URL đến ListUserController
			public static final String URL_ADM002 = "ADM002";
			// URL đến LoginController
			public static final String URL_LOGIN = "Login.do";
			// URL đến LogoutController
			public static final String URL_LOGOUT = "Logout";
			public static final int RULE = 1;
			//--------------CÁC HẠNG MỤC DEFAULT------------------
			public static final int GROUP_ID_DEFAULT = 0;
			public static final int OFFSET_DEFAULT = 0;
			public static final String FULL_NAME_DEFAULT = "";
			public static final String SORT_TYPE_DEFAULT = "full_name";
			public static final String SORT_FULL_NAME_DEFAULT = "ASC";
			public static final String SORT_NAME_LEVEL_DEFAULT = "ASC";
			public static final String SORT_END_DATE_DEFAULT = "DESC";
			public static final int pageSize = 3;
}
