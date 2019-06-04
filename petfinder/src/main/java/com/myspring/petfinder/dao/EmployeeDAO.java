package com.myspring.petfinder.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myspring.petfinder.pojo.Employee;

public class EmployeeDAO extends DAO {

	public EmployeeDAO() {
	}

	public Employee get(String username, String password) throws Exception {
		try {

			begin();
			Query q = getSession().createQuery("from Employee where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);
			Employee user = (Employee) q.uniqueResult();
			close();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get employee " + username, e);
		}
	}

	public Employee get(String username) throws Exception{
		try {

			begin();
			Query q = getSession().createQuery("from Employee where username = :username");
			q.setString("username", username);
			Employee user = (Employee) q.uniqueResult();
			close();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get employee " + username, e);
		}
	}
}
