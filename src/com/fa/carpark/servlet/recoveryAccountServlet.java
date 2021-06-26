package com.fa.carpark.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fa.carpark.dao.AccountDao;
import com.fa.carpark.model.Account;

/**
 * Servlet implementation class recoveryAccount
 */
@WebServlet("/recoveryAccountServlet")
public class recoveryAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		accountDao = new AccountDao();
	}
    public recoveryAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String account  = request.getParameter("account");
		String email  = request.getParameter("recover-email");
		String newPassword = request.getParameter("txtNewPassword");
		
			Account accountNeedRecovery = accountDao.findByAccountAndEmail(account, email);
			
			if (accountNeedRecovery != null) {
				accountNeedRecovery.setPassword(newPassword);
				accountDao.addAccount(accountNeedRecovery);
				response.sendRedirect("login.jsp");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("recover-account.jsp");
				request.setAttribute("errorMessage", "Account or email is not correct, please check!");
				request.setAttribute("txtAccount", account);
				request.setAttribute("txtEmail", email);
				dispatcher.forward(request, response);
			
			}
		}
}
