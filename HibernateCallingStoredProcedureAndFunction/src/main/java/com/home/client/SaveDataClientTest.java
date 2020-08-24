package com.home.client;

import java.util.Date;

import org.hibernate.Session;
import com.home.entities.Employee;
import com.home.entities.Phone;
import com.home.entities.PhoneType;
import com.home.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			
			Employee employee1=new Employee();
			employee1.setEmployeeName("Paras");
			employee1.setEmail("paras@gmail.com");
			employee1.setDoj(new Date());
			employee1.setSalary(16000.00);
			
			Phone phone1=new Phone();
			phone1.setPhoneNumber("123456789");
			phone1.setPhoneType(PhoneType.MOBILE);
			phone1.setEmployee(employee1);
			
			employee1.getPhone().add(phone1);
				
			Employee employee2=new Employee();
			employee2.setEmployeeName("Prabhat Singh");
			employee2.setEmail("prabhat.singh@gmail.com");
			employee2.setDoj(new Date());
			employee2.setSalary(17000.00);
			
			Phone phone2=new Phone();
			phone2.setPhoneNumber("987654321");
			phone2.setPhoneType(PhoneType.LAND_LINE);
			phone2.setEmployee(employee2);
			
			employee2.getPhone().add(phone2);
			
			Phone phone3=new Phone();
			phone3.setPhoneNumber("987654321");
			phone3.setPhoneType(PhoneType.MOBILE);
			phone3.setEmployee(employee2);
			
			employee2.getPhone().add(phone3);
					
			session.save(employee1);
			session.save(employee2);
			
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
