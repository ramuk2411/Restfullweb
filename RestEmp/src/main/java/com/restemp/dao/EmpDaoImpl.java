package com.restemp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.restemp.model.DateOrder;
import com.restemp.model.Employee;



public class EmpDaoImpl {
	public String month1;

	public List<Employee> getEmpDetails() {

		List<Employee> empData = new ArrayList<>();
		

		Jdbcconnenction jdbcConnection = new Jdbcconnenction();

		Connection connection = jdbcConnection.getConnnection();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"select * from EMPINFO");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee ep = new Employee();
				ep.setEmpid(rs.getString("EMPID"));
				ep.setLogintime(rs.getString("Timestamp"));
				empData.add(ep);

			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empData;
	}

	public List<DateOrder> getEmpDate(String month) {
		String date1="'2017-"+month+"-%% %%:%%:%%'";
		month1=month;
		List<DateOrder> datelist=new ArrayList<>();
		List<Employee> empData = new ArrayList<>();

		Jdbcconnenction jdbcConnection = new Jdbcconnenction();

		Connection connection = jdbcConnection.getConnnection();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"select * from EMPINFO where Timestamp like "+date1);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee ep = new Employee();
				ep.setEmpid(rs.getString("EMPID"));
				ep.setLogintime(rs.getString("Timestamp"));
				empData.add(ep);

			}
			datelist=getdatewise(empData);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datelist;
	}

	public List<DateOrder> getdatewise(List<Employee> empdata){
		List<DateOrder> date1=new ArrayList<DateOrder>();
		List<Employee> emp1;
		Map<String,Integer> countmap=new HashMap<String,Integer>();
		for(Employee empto:empdata){
			String[] datearray=empto.getLogintime().split(" ");
			String date3=datearray[0];
			int n=0;
			for(Employee empto1:empdata){
				String[] datearrayin=empto1.getLogintime().split(" ");
				String date4=datearrayin[0];
				
				if(date3.equals(date4))
					n=n+1;
					
			}
			countmap.put(date3, n);
		}
		Iterator it=countmap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry count1 = (Map.Entry)it.next();
			DateOrder do1=new DateOrder();
			do1.setDate1(count1.getKey().toString());
			do1.setEMPCount(Integer.parseInt(count1.getValue().toString()));
			date1.add(do1);
		}
		return date1;
		
	}
	
}
