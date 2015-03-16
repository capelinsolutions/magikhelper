package com.magikhelper.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.magikhelper.dao.pojo.User;


@Path("/user")
public class UserFacadeREST extends AbstractREST<User> {

	public UserFacadeREST() {
		super(User.class);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String AddUser(User user) {
		super.upsert(user);
		return "Success";
	}
}
