package com.fa.carpark.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fa.carpark.dao.AccountDao;
import com.fa.carpark.model.Account;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountDao accountDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@Override
	public void init() throws ServletException {
		accountDao = new AccountDao();
	}
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		session.setAttribute("userName", "");
//		
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcherToEmployeeManagementPage = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
		
		String account = request.getParameter("txtUsername");
		String password = request.getParameter("txtPassword");
		
		Account inputAccount = accountDao.login(account, password);
		
		HttpSession session = request.getSession();
		
		
		
		if (inputAccount != null) {
			session.setAttribute("userName",account);
			dispatcherToEmployeeManagementPage.forward(request, response);
		} else {
			RequestDispatcher dispatcherBackToLoginPage = request.getRequestDispatcher("login.jsp");
			if (account.equals("") || password.equals("")) {
				dispatcherBackToLoginPage.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Wrong account or password");
				dispatcherBackToLoginPage.forward(request, response);
			}
		}
	}

}
