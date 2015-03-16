package com.magikhelper.ws;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.magikhelper.dao.AbstractFacade;

public class AbstractREST<T> extends AbstractFacade<T> {
	
	@Context 
    private ServletContext context; 

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

	public AbstractREST(Class<T> entityClass) {
		super(entityClass);
	}

	/*@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object find(@PathParam("id") Integer id) {
		Object entity =  (T) super.find(id);
		return entity;
	}*/

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> findAll() {
		return super.findAll();
	}
	
}
