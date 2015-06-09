package com.magikhelper.vo;

import java.util.ArrayList;
import java.util.List;

public class UserVO {
	private int userId;
	private String email;
	private String password;
	
	private ContactVO contact = new ContactVO();
	private List<ClientBookingsVO> bookings = new ArrayList<ClientBookingsVO>();
	private List<UserRoleVO> roles = new ArrayList<UserRoleVO>();
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public ContactVO getContact() {
		return contact;
	}

	public void setContact(ContactVO contact) {
		this.contact = contact;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ClientBookingsVO> getBookings() {
		return bookings;
	}

	public void setBookings(List<ClientBookingsVO> bookings) {
		this.bookings = bookings;
	}

	public List<UserRoleVO> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoleVO> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", email=" + email + ", password="
				+ password + ", contact=" + contact + ", bookings=" + bookings
				+ ", roles=" + roles + "]";
	}

	
}
