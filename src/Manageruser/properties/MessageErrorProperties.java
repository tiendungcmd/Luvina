/**
 * Coppyright (C)  2020 Luvina
 * MessageErrorProperties.java, Nov 19, 2020, BuiTienDung
 */
package Manageruser.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import utils.Constant;

/**
 * @author BuiTienDung
 *
 */
public class MessageErrorProperties {
	// lưu các cặp <key, value> trong file properties
	private static Map<String, String> mapDBProperties = new HashMap<String, String>();
	static {
		try {
			// tạo đối tượng kiểu Properties
			Properties properties = new Properties();
			// đọc file properties
			properties.load(new InputStreamReader(MessageErrorProperties.class.getClassLoader()
					.getResourceAsStream(Constant.PROPERTIES_MESSAGE_ERROR_PATH), "UTF-8"));
			// lưu các giá trị key trong file properties
			Enumeration<?> enumeration = properties.propertyNames();
			// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
			while (enumeration.hasMoreElements()) {
				// key = key tiếp theo
				String key = (String) enumeration.nextElement();
				// lấy value tương ứng với key
				String value = properties.getProperty(key);
				// thêm vào list
				mapDBProperties.put(key, value);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * lấy value tương ứng với key trong file properties
	 * 
	 * @param key tên key trong file properties
	 * @return trả về value tương ứng với key
	 */
	public static String getValueByKey(String key) {
		String value = mapDBProperties.get(key);
		return value;
	}
	public static void main(String[] args) {
		MessageErrorProperties ms = new MessageErrorProperties();
		System.out.println(ms.getValueByKey("MSG005"));
	}
}
