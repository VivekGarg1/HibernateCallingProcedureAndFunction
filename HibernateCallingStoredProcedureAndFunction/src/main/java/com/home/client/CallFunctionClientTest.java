package com.home.client;

import java.sql.Types;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;
import com.home.util.HibernateUtil;
import com.mysql.cj.jdbc.CallableStatement;

public class CallFunctionClientTest  {
	
	public static void main(String[] args) {
		
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			int employeeId=2;
			final AtomicReference<Integer> phoneCount=new AtomicReference<>();
			session.doWork(connection -> {
				try(CallableStatement callableStatement=(CallableStatement) connection.prepareCall(
						"{? = call fn_count_phones(?) }")){
					callableStatement.registerOutParameter(1,Types.INTEGER);
					callableStatement.setInt(2, employeeId);
					callableStatement.execute();
					phoneCount.set(callableStatement.getInt(1));
				}
			});
			if(phoneCount != null)
				System.out.println("PhoneCount: "+phoneCount);
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
	}

}
