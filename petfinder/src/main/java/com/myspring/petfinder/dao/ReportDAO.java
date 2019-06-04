package com.myspring.petfinder.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myspring.petfinder.pojo.CompleteReport;
import com.myspring.petfinder.pojo.HomelessReport;
import com.myspring.petfinder.pojo.HomelessReport.status;

public class ReportDAO extends DAO{

	public void create(HomelessReport report) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(report);
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating report: " + e.getMessage());
		}
	}
	
	public List<HomelessReport> getList() throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from HomelessReport order by status");
			List<HomelessReport> list = q.list();
			close();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while getting report list: " + e.getMessage());
		}
	}

	public List<HomelessReport> getList(status pending) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from HomelessReport where status=:status order by priority");
			q.setParameter("status", pending);
			List<HomelessReport> list = q.list();
			close();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while getting report list: " + e.getMessage());
		}
	}

	public HomelessReport getById(int id) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from HomelessReport where reportid=:reportId");
			q.setParameter("reportId", id);
			HomelessReport report = (HomelessReport)q.uniqueResult();
			close();
			return report;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while getting report list: " + e.getMessage());
		}
	}

	public CompleteReport getComplete(int id) throws Exception {
		// TODO Auto-generated method stub
		try {
			begin();
			System.out.println("inside DAO");
			Query q = getSession().createQuery("from CompleteReport where reportid=:reportId");
			q.setParameter("reportId", id);
			CompleteReport report = (CompleteReport)q.uniqueResult();
			close();
			return report;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while getting report list: " + e.getMessage());
		}
	}

	public void update(HomelessReport report) throws Exception{
		try {
			begin();
			System.out.println("inside DAO");
			getSession().update(report);
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating report: " + e.getMessage());
		}
		
	}
}
