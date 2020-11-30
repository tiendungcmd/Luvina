/**
 * Coppyright (C) 2020 Luvina
 * Bai 2 25/10/2020 BuiTienDung
 */
package Manageruser.entities;

import java.sql.Date;

/**
 * @author 440TranCung
 *
 */
public class UserInfor {
	private int user_id;
	private String full_name;
	private Date birthday;
	private String email;
	private int tel;
	private String group_name;
	private String name_level;
	private Date end_date;
	private int total;
	public int getUser_id;
	
	public UserInfor() {
		
	}
	@Override
	public String toString() {
		return "UserInfor [user_id=" + user_id + ", full_name=" + full_name + ", birthday=" + birthday + ", email="
				+ email + ", tel=" + tel + ", group_id=" + group_name + ", name_level=" + name_level + ", end_date="
				+ end_date + ", total=" + total + "]";
	}
	/**
	 * @param user_id
	 * @param full_name
	 * @param birthday
	 * @param email
	 * @param tel
	 * @param group_id
	 * @param name_level
	 * @param end_date
	 * @param total
	 */
	public UserInfor(int user_id, String full_name, Date birthday, String email, int tel, String group_name,
			String name_level, Date end_date, int total) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.birthday = birthday;
		this.email = email;
		this.tel = tel;
		this.group_name = group_name;
		this.name_level = name_level;
		this.end_date = end_date;
		this.total = total;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the full_name
	 */
	public String getFull_name() {
		return full_name;
	}
	/**
	 * @param full_name the full_name to set
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the tel
	 */
	public int getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(int tel) {
		this.tel = tel;
	}
	/**
	 * @return the group_id
	 */
	public String getGroup_name() {
		return group_name;
	}
	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	/**
	 * @return the name_level
	 */
	public String getName_level() {
		return name_level;
	}
	/**
	 * @param name_level the name_level to set
	 */
	public void setName_level(String name_level) {
		this.name_level = name_level;
	}
	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}
	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	
}
