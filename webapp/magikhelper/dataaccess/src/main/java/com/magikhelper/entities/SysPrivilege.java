package com.magikhelper.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the sys_privilege database table.
 * 
 */
@Entity
@Table(name="sys_privilege")
@NamedQuery(name="SysPrivilege.findAll", query="SELECT s FROM SysPrivilege s")
public class SysPrivilege extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int privilegeId;
	private String description;
	private String title;
	private List<RolePrivilege> rolePrivileges;

	public SysPrivilege() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="privilege_id")
	public int getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//bi-directional many-to-one association to RolePrivilege
	@OneToMany(mappedBy="sysPrivilege")
	public List<RolePrivilege> getRolePrivileges() {
		return this.rolePrivileges;
	}

	public void setRolePrivileges(List<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

}