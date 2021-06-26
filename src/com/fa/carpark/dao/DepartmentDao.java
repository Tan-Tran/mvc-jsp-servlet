package com.fa.carpark.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fa.carpark.model.Department;
import com.fa.carpark.util.HibernateUtil;

public class DepartmentDao {
	
	private Department findByEmployeeId(Integer id) {
		Department department = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query query = session.createQuery("FROM Department WHERE id =: id");
			query.setParameter("id", id);
			department = (Department) query.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return department;
	}
	
}
