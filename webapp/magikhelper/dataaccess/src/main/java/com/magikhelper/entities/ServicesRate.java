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
 * The persistent class for the services_rates database table.
 * 
 */
@Entity
@Table(name="services_rates")
@NamedQuery(name="ServicesRate.findAll", query="SELECT s FROM ServicesRate s")
public class ServicesRate extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rowId;
	private BigDecimal rate;
	private ApplicationProperty service;

	public ServicesRate() {
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


	//bi-directional many-to-one association to ApplicationProperty
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="service_id")
	public ApplicationProperty getService() {
		return this.service;
	}

	public void setService(ApplicationProperty service) {
		this.service = service;
	}

}