package com.magikhelper.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.magikhelper.entities.enums.ApplicationPropertyType;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the application_properties database table.
 * 
 */
@Entity
@Table(name="application_properties")
@NamedQueries({
	@NamedQuery(name="ApplicationProperty.findAll", query="SELECT a FROM ApplicationProperty a")
})
public class ApplicationProperty extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int propertyId;
	private String name;
	private int sortOrder;
	private ApplicationPropertyType type;
	private String value;
	private List<Services> services;

	public ApplicationProperty() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="property_id")
	public int getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

    @Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="sort_order")
	public int getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Column(name="type")
    @Enumerated(EnumType.STRING)
	public ApplicationPropertyType getType() {
		return this.type;
	}

	public void setType(ApplicationPropertyType type) {
		this.type = type;
	}

    @Column(name="value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	//bi-directional many-to-one association to Services
    @OneToMany(mappedBy = "service", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<Services> getServices() {
		return this.services;
	}

	public void setServices(List<Services> services) {
		this.services = services;
	}


	@Override
	public String toString() {
		return "ApplicationProperty [propertyId=" + propertyId + ", name="
				+ name + ", sortOrder=" + sortOrder + ", type=" + type
				+ ", value=" + value + "]";
	}
	
	
}