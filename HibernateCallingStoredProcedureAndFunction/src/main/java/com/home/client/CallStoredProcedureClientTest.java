package com.home.client;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.home.util.HibernateUtil;

public class CallStoredProcedureClientTest {
	
	public static void main(String[] args) {
		
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			
			StoredProcedureQuery procedureQuery=session.createStoredProcedureQuery("sp_count_phones");
			procedureQuery.registerStoredProcedureParameter("employeeId", Long.class, ParameterMode.IN);
			procedureQuery.registerStoredProcedureParameter("phoneCount", Long.class, ParameterMode.OUT);
			procedureQuery.setParameter("employeeId", 2L);
			procedureQuery.execute();
			Long phoneCount=(Long) procedureQuery.getOutputParameterValue("phoneCount");
			System.out.println("PhoneCount: "+phoneCount);
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
	}

}
