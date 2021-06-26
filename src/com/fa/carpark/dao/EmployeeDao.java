package com.fa.carpark.dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fa.carpark.model.Employee;
import com.fa.carpark.util.HibernateUtil;

public class EmployeeDao {
	
	public List<Employee> getAllEmployees(){
		
		List<Employee> employees = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query query = session.createQuery("FROM Employee");
			employees = query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public List<Employee> searchEmployee(String field, String value){
		
		List<Employee> employeeListBySearch = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query query = session.createQuery("FROM Employee WHERE " + field +" = " + "'" + value + "'");
			System.out.println("FROM Employee WHERE " + field +" = " + value);
			employeeListBySearch = query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return employeeListBySearch;
	}
	
	public void addEmployee(Employee emp) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			session.saveOrUpdate(emp);
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Employee findEmployee(Integer id) {
		Employee employee = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query query = session.createQuery("FROM Employee E WHERE E.id = :id");
			query.setParameter("id", id);
			employee = (Employee) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	public void deleteEmployee(Employee inputEmployee) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			session.delete(inputEmployee);
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
