package com.fa.carpark.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.fa.carpark.model.Account;
import com.fa.carpark.util.HibernateUtil;

public class AccountDao {
	
	public Account login(String account, String password) {
		Account accountFromDB = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query query = session.createQuery("FROM Account WHERE account = :account AND password = :password");
			query.setParameter("account", account);
			query.setParameter("password", password);
			accountFromDB = (Account) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return accountFromDB;
	}
	
	public String addAccount(Account newAccount) {
		String addStatus = "Insert successfull";
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			session.saveOrUpdate(newAccount);
			session.getTransaction().commit();
			System.out.println("Insert successfull");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return addStatus;
	}
	
	public Account findAccountByEmployeeId(Integer id) {
		Account account = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
//			Query query = session.createQuery("select A from Account A join fetch A.employee E WHERE E.id = : employeeID");
			Query query = session.createQuery("from Account WHERE id = : id");
			query.setParameter("id", id);
			account = (Account) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	
	public void deleteAccount(Account account) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			session.delete(account);
			session.getTransaction().commit();
			System.out.println("delete");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Account findByAccountAndEmail(String inputAccount, String inputEmail){
		Account account = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query query = session.createQuery("FROM Account WHERE account = :inputAccount AND email = :inputEmail");
			query.setParameter("inputAccount", inputAccount);
			query.setParameter("inputEmail", inputEmail);
			account = (Account) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return account;
	}
}
