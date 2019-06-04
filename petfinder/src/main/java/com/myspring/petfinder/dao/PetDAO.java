package com.myspring.petfinder.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.hibernate.HibernateException;

import org.hibernate.query.Query;

import java.lang.Math;

import com.myspring.petfinder.pojo.Pet;
import com.myspring.petfinder.pojo.Pet.gender;

public class PetDAO extends DAO {

	public PetDAO() {
	}

	public Pet get(int petid) {
		try {
			begin();
			Query q = getSession().createQuery("from Pet where petid = :petId");
			q.setInteger("petId", petid);
			Pet pet = (Pet) q.uniqueResult();
			close();
			return pet;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public boolean updatePet(Pet p) {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().saveOrUpdate(p);
			commit();
			close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public List<Pet> getByPageI(int pageNumber, int ownerid) {
		try {
			begin();
			Query q = getSession().createQuery("from Pet where petownerid != :ownerId and listed != false");
			q.setInteger("ownerId", ownerid);
			List<Pet> list = q.list();
			List<Pet> result = list.subList(0, Math.max(list.size() - 1, 0));
			if (pageNumber != 1) {
				result = list.subList(pageNumber * 5 - 5, Math.max(list.size() - 5, list.size()));
			}
			// System.out.println(list.size());
			close();
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int getTotalPage(int ownerid) {
		try {
			begin();
			Query q = getSession().createQuery("from Pet where petownerid != :ownerId");
			q.setInteger("ownerId", ownerid);
			int number = q.list().size();
			int result = (int) Math.ceil(number / 5);
			// System.out.println(number+result);
			close();
			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public boolean refreshPet(Pet p) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().saveOrUpdate(p);
			commit();
			close();
			return true;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(Pet p) throws Exception {
		try {
			begin();
			getSession().delete(p);
			commit();
			close();
			// return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}

	public void unlist(Pet p) throws Exception {
		// TODO Auto-generated method stub
		try {
			begin();
			p.setListed(false);
			getSession().update(p);
			commit();
			close();
			// return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}

	public void list(Pet p) throws Exception {
		// TODO Auto-generated method stub
		try {
			begin();
			p.setListed(true);
			getSession().update(p);
			commit();
			close();
			// return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while updating request: " + e.getMessage());
		}
	}

	public List<Pet> query(int userid, String type, String breed, String color, String agefrom, String ageto,
			String sex) throws Exception {
		try {
			begin();
			// System.out.println("from Pet where petownerid != :ownerId and listed =
			// true"+query);
			CriteriaBuilder cb = getSession().getCriteriaBuilder();
			CriteriaQuery<Pet> cq = cb.createQuery(Pet.class);
			Root e = cq.from(Pet.class);
			cq.select(e);
			Predicate p = cb.conjunction();
			// Path<User> user = e.get(Pet.owner);
			// cr.add(Restrictions.ne("petownerid", userid));
			// cq.where(cb.notEqual(e.get("petownerid"), userid));
			p = cb.and(p, cb.notEqual(e.join("owner"), userid));
			if (!type.equals("")) {
				// cr.add(Restrictions.like("animaltype", type));
				p = cb.and(p, cb.like(e.get("animalType"), type));
			}
			if (!breed.equals("")) {
				// cr.add(Restrictions.like("breed", breed));
				p = cb.and(p, cb.like(e.get("breed"), "%" + breed + "%"));
			}
			if (!color.equals("")) {
				// cr.add(Restrictions.like("color", color));
				p = cb.and(p, cb.like(e.get("color"), color));
			}
			if (!agefrom.equals("")) {
				// cr.add(Restrictions.ge("petage", Integer.parseInt(agefrom)));
				p = cb.and(p, cb.greaterThanOrEqualTo(e.get("petAge"), agefrom));
			}
			if (!ageto.equals("")) {
				// cr.add(Restrictions.le("petage", Integer.parseInt(ageto)));
				p = cb.and(p, cb.lessThanOrEqualTo(e.get("petAge"), ageto));
			}
			if (!sex.equals("")) {
				// cr.add(Restrictions.le("petage", Integer.parseInt(ageto)));
				gender g = null;
				if (sex.equalsIgnoreCase("female") || sex.equalsIgnoreCase("f")) {
					g = gender.FEMALE;
				}
				if (sex.equalsIgnoreCase("male") || sex.equalsIgnoreCase("m")) {
					g = gender.MALE;
				}
				p = cb.and(p, cb.equal(e.get("gender"), g));
			}
			cq.where(p);
			cq.select(e);
			// Query<Pet> query = getSession().createQuery(cq);
			List<Pet> list = getSession().createQuery(cq).getResultList();
			System.out.println(list.size());
			close();
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Pet> getOthers(int uid) {
		try {
			begin();
			Query q = getSession().createQuery("from Pet where petownerid != :ownerId and listed != false");
			q.setInteger("ownerId", uid);
			List<Pet> list = q.list();
			// System.out.println(list.size());
			close();
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
