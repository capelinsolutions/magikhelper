package com.magikhelper.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends com.magikhelper.entities.BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rowId;
	private String email;
	private String password;
	private Contact contact;
	private List<VendorSkill> vendorSkills;
	private List<Booking> clientBookings;
	private List<Booking> vendorBookings;
	private List<UserRole> userRoles;

	public User() {
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


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	//bi-directional many-to-one association to Contact
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="address_id")
	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}


	//bi-directional many-to-one association to VendorSkill
	@OneToMany(mappedBy="user")
	public List<VendorSkill> getVendorSkills() {
		return this.vendorSkills;
	}

	public void setVendorSkills(List<VendorSkill> vendorSkills) {
		this.vendorSkills = vendorSkills;
	}

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="client", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<Booking> getClientBookings() {
		return this.clientBookings;
	}

	public void setClientBookings(List<Booking> clientBookings) {
		this.clientBookings = clientBookings;
	}

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="vendor", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<Booking> getVendorBookings() {
		return this.vendorBookings;
	}

	public void setVendorBookings(List<Booking> vendorBookings) {
		this.vendorBookings = vendorBookings;
	}

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void addUserRole(UserRole userRole) {
        if (this.userRoles == null) {
            this.userRoles = new ArrayList<UserRole>();
        }
        this.userRoles.add(userRole);
        userRole.setUser(this);
    }


	@Override
	public String toString() {
		return "User [rowId=" + rowId + ", email=" + email + ", password=" + password + "]";
	}
	
}
