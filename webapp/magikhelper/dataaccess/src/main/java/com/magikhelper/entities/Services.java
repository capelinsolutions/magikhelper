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
 * The persistent class for the services database table.
 * 
 */
@Entity
@Table(name="services")
@NamedQuery(name="Services.findAll", query="SELECT s FROM Services s")
public class Services extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rowId;
	private BigDecimal rate;
	private Integer zipcode;
	private ApplicationProperty service;

	public Services() {
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


	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}


	//bi-directional many-to-one association to ApplicationProperty
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="service_id")
	public ApplicationProperty getService() {
		return this.service;
	}

	public void setService(ApplicationProperty service) {
		this.service = service;
	}


	@Override
	public String toString() {
		return "Service [rowId=" + rowId + ", rate=" + rate + ", zipcode=" + zipcode + "]";
	}

	
}