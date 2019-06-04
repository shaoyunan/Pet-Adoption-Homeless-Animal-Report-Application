package com.myspring.petfinder.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myspring.petfinder.pojo.Pet;
import com.myspring.petfinder.pojo.User;


public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String username, String password) throws Exception {
		try {
			
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			close();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + username, e);
		}
	}
	
	public User get(String username){
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :userName");
			q.setString("userName", username);
			User user = (User) q.uniqueResult();
			close();
			return user;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
			return null;
		
	}
	
	public boolean validateUsername(int userid, String username){
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :userName and petownerid != :userId");
			q.setString("userName", username);
			q.setInteger("userId", userid);
			User user = (User) q.uniqueResult();
			close();
			if(user!=null) {
				return false;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
			return true;
	}
	
	public User register(User u) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(u);
			commit();
			close();
			return u;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	public boolean updateUser(String email) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from User where email = :Email");
			q.setString("Email", email);
			User user = (User) q.uniqueResult();
			if(user!=null){
				user.setStatus(1);
				getSession().update(user);
				commit();
				close();
				return true;
			}else{
				return false;
			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	
	}
	
	public Pet registerPet(Pet pet) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(pet);
			commit();
			close();
			return pet;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	
	public boolean changeUser(User u) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().saveOrUpdate(u);
			commit();
			close();
			return true;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

}