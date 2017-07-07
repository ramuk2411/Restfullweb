package com.restemp.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.restemp.dao.EmpDaoImpl;
import com.restemp.model.DateOrder;
import com.restemp.model.Employee;


@Path("/employee")
public class EmpService {

	@GET
	@Path("/getAll")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getEMPDetails() {
		String empDetails = null;
		List<Employee> empList = null;

		EmpDaoImpl empdao=new EmpDaoImpl();

		empList = empdao.getEmpDetails();

		Gson gson = new Gson();
		empDetails = gson.toJson(empList);
		return empDetails;
	}
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getDateEmp(@PathParam("id") String id){
		
		String empDetails = null;
		List<DateOrder> empList = new ArrayList<>();

		EmpDaoImpl empdao=new EmpDaoImpl();

		empList = empdao.getEmpDate(id);

		Gson gson = new Gson();
		empDetails = gson.toJson(empList);
		return empDetails;
		
		
	}
}
