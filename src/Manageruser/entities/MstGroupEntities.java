/**
 * Coppyright (C)  2020 Luvina
 * MstGroupEntities.java, Nov 17, 2020, BuiTienDung
 */
package Manageruser.entities;

/**
 * @author LA-PM
 *
 */
public class MstGroupEntities {
	private int group_id;
	private String group_name;
	
	public MstGroupEntities() {
		
	}
	public MstGroupEntities(int group_id, String group_name) {
		super();
		this.group_id = group_id;
		this.group_name = group_name;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	@Override
	public String toString() {
		return "MstGroupEntities [group_id=" + group_id + ", group_name=" + group_name + "]";
	}

}
