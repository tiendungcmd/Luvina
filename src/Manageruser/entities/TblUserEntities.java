/**
 * Coppyright (C)  2020 Luvina
 * TblUserEntities.java, Nov 17, 2020, BuiTienDung
 */
package Manageruser.entities;

import java.sql.Date;

/**
 * @BuiTienDung
 *
 */
public class TblUserEntities {
	private int user_id;
	private int group_id;
	private String login_name;
	private String password;
	private String full_name;
	private String full_name_kana;
	private String email;
	private String tel;
	private Date birthday;
	private int rule;
	private String Salt;
	
	public TblUserEntities() {
		
	}
	@Override
	public String toString() {
        return user_id + " " + group_id + " " + login_name+" "+password+" "+full_name+" "+full_name_kana+" "+email
        		+" "+tel+" "+birthday+" "+rule+" "+Salt;
    }
 
	public TblUserEntities(int user_id, int group_id, String login_name, String password, String full_name,
			String full_name_kana, String email, String tel, Date birthday, int rule, String salt) {
		super();
		this.user_id = user_id;
		this.group_id = group_id;
		this.login_name = login_name;
		this.password = password;
		this.full_name = full_name;
		this.full_name_kana = full_name_kana;
		this.email = email;
		this.tel = tel;
		this.birthday = birthday;
		this.rule = rule;
		Salt = salt;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getFull_name_kana() {
		return full_name_kana;
	}
	public void setFull_name_kana(String full_name_kana) {
		this.full_name_kana = full_name_kana;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getRule() {
		return rule;
	}
	public void setRule(int rule) {
		this.rule = rule;
	}
	public String getSalt() {
		return Salt;
	}
	public void setSalt(String salt) {
		Salt = salt;
	}
	
	
}
