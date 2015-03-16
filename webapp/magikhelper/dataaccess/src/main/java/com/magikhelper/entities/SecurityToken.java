package com.magikhelper.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the security_tokens database table.
 * 
 */
@Entity
@Table(name="security_tokens")
@NamedQuery(name="SecurityToken.findAll", query="SELECT s FROM SecurityToken s")
public class SecurityToken extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private int rowId;

	private String token;

	private String deviceId;
	
	public SecurityToken() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="row_id")
	public int getRowId() {
		return this.rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	@Column(name="token")
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name="device_id")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}