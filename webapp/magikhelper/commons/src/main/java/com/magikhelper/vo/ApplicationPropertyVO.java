package com.magikhelper.vo;

public class ApplicationPropertyVO {
	private Integer propertyId;
	private String type;
	private String name;
	private String value;
	private Integer sortOrder;
	
	
	public ApplicationPropertyVO() {
	}
	public ApplicationPropertyVO(Integer propertyId, String type, String name,
			String value, Integer sortOrder) {
		this.propertyId = propertyId;
		this.type = type;
		this.name = name;
		this.value = value;
		this.sortOrder = sortOrder;
	}
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

}
