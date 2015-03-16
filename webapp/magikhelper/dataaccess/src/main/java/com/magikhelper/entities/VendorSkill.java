package com.magikhelper.entities;

import java.io.Serializable;
import java.math.BigDecimal;

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
 * The persistent class for the vendor_skill database table.
 * 
 */
@Entity
@Table(name="vendor_skill")
@NamedQuery(name="VendorSkill.findAll", query="SELECT v FROM VendorSkill v")
public class VendorSkill extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rowId;
	private BigDecimal rates;
	private User user;
	private ApplicationProperty skill;

	public VendorSkill() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="row_id")
	public int getRowId() {
		return this.rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}


	public BigDecimal getRates() {
		return this.rates;
	}

	public void setRates(BigDecimal rates) {
		this.rates = rates;
	}


	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vendor_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	//bi-directional many-to-one association to ApplicationProperty
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="skill_id")
	public ApplicationProperty getSkill() {
		return this.skill;
	}

	public void setSkill(ApplicationProperty skill) {
		this.skill = skill;
	}

}