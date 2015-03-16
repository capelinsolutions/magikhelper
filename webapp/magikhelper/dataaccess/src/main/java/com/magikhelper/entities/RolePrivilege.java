package com.magikhelper.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the role_privilege database table.
 * 
 */
@Entity
@Table(name="role_privilege")
@NamedQuery(name="RolePrivilege.findAll", query="SELECT r FROM RolePrivilege r")
public class RolePrivilege extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rolePrivilegeId;
	private SysPrivilege sysPrivilege;
	private SystemRole systemRole;

	public RolePrivilege() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_privilege_id")
	public int getRolePrivilegeId() {
		return this.rolePrivilegeId;
	}

	public void setRolePrivilegeId(int rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}


	//bi-directional many-to-one association to SysPrivilege
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="privilege_id")
	public SysPrivilege getSysPrivilege() {
		return this.sysPrivilege;
	}

	public void setSysPrivilege(SysPrivilege sysPrivilege) {
		this.sysPrivilege = sysPrivilege;
	}


	//bi-directional many-to-one association to SystemRole
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	public SystemRole getSystemRole() {
		return this.systemRole;
	}

	public void setSystemRole(SystemRole systemRole) {
		this.systemRole = systemRole;
	}

}