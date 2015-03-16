package com.magikhelper.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Query;
import org.hibernate.Session;

import com.magikhelper.dao.pojo.Code;


@Path("/code")
public class CodeFacadeREST extends AbstractREST<Code> {

	public CodeFacadeREST() {
		super(Code.class);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String AddCode(Code code) {
		super.upsert(code);
		return "Success";
	}
	
	@GET
	@Path("{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Code> getCode(@PathParam("type") String type){
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery("SELECT code FROM Code as code join code.codeType as codeType with codeType.name= :type");
		query.setParameter("type", type);
		return query.list();
	}
}
