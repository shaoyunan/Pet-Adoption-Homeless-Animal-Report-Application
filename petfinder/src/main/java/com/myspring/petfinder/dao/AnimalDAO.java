package com.myspring.petfinder.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myspring.petfinder.pojo.HomelessReport;
import com.myspring.petfinder.pojo.ShelterAnimal;

public class AnimalDAO extends DAO {
	public void register(ShelterAnimal a) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(a);
			commit();
			close();

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public List<ShelterAnimal> getList() throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from ShelterAnimal");
			List<ShelterAnimal> list = q.list();
			close();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while getting report list: " + e.getMessage());
		}
	}

	public ShelterAnimal get(int id) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from ShelterAnimal where animalid=:ID");
			q.setInteger("ID", id);
			ShelterAnimal sa = (ShelterAnimal) q.uniqueResult();
			close();
			return sa;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while getting report list: " + e.getMessage());
		}
	}

	public void update(ShelterAnimal a) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().update(a);
			commit();
			close();

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}

	}

	public void delete(ShelterAnimal a) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().delete(a);
			commit();
			close();

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}

	}
}
