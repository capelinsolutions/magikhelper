package com.magikhelper.vo;

public class UserRoleVO {

	private int roleId;
	private String description;
	private String title;

	public UserRoleVO() {
	}
	public UserRoleVO(int roleId, String description, String title) {
		this.roleId = roleId;
		this.description = description;
		this.title = title;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "UserRoleVO [roleId=" + roleId + ", description=" + description
				+ ", title=" + title + "]";
	}
	
}
