package com.magikhelper.vo;

import java.math.BigDecimal;

public class MagikHelperService {

	private Integer serviceId;
	private String name;
	private BigDecimal rate;
	private Integer zipcode;
	
	public MagikHelperService() {
	}
	
	public MagikHelperService(Integer serviceId, String name) {
		this.serviceId = serviceId;
		this.name = name;
	}

	public MagikHelperService(Integer serviceId, String name, BigDecimal rate) {
		this.serviceId = serviceId;
		this.name = name;
		this.rate = rate;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getRate() {
		return rate;
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

	@Override
	public String toString() {
		return "MagikHelperService [serviceId=" + serviceId + ", name=" + name + ", rate=" + rate + ", zipcode=" + zipcode + "]";
	}

	
	
}
