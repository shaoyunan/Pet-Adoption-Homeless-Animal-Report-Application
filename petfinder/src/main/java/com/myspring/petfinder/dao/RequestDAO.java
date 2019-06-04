package com.myspring.petfinder.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myspring.petfinder.pojo.IndividualRequest;
import com.myspring.petfinder.pojo.IndividualRequest.status;

public class RequestDAO extends DAO {

	public RequestDAO() {
	}

	public List<IndividualRequest> getRequestI(int userid) {
		try {
			begin();
			Query q = getSession().createQuery("from individualadoptionrequest where requestby = :userId");
			q.setInteger("userId", userid);
			List<IndividualRequest> list = q.list();
			close();
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public IndividualRequest sendRequest(IndividualRequest i) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(i);
			commit();
			close();
			return i;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public IndividualRequest updateById(int requestid, String message) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from IndividualRequest where requestid=:Id");
			q.setInteger("Id", requestid);
			IndividualRequest i = (IndividualRequest) q.uniqueResult();
			i.setMessage(message);
			getSession().update(i);
			commit();
			close();
			return i;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}

	public IndividualRequest getById(int requestid) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from IndividualRequest where requestid=:Id");
			q.setInteger("Id", requestid);
			IndividualRequest i = (IndividualRequest) q.uniqueResult();
			close();
			return i;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}

	public void cancel(IndividualRequest ir) throws Exception {
		try {
			begin();

			System.out.println("deleting " + ir.getRequestid());
			getSession().delete(ir);
			commit();
			close();
			// return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}

	public void approve(IndividualRequest ir) throws Exception {
		try {
			begin();
			ir.setStatus(status.APPROVED);
			getSession().update(ir);
			commit();
			close();
			// return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}

	public void reject(IndividualRequest ir) throws Exception {
		try {
			begin();
			ir.setStatus(status.REJECTED);
			getSession().update(ir);
			commit();
			close();
			// return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}

	public boolean validateRequest(int petid, int userid) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from IndividualRequest where petid=:petId and requestby=:userId");
			q.setInteger("petId", petid);
			q.setInteger("userId", userid);
			List<IndividualRequest> list = q.list();
			close();
			// return true;
			if (list.size() != 0) {
				return false;
			}
			return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}
}
