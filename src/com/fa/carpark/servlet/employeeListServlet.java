package com.fa.carpark.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fa.carpark.dao.EmployeeDao;
import com.fa.carpark.model.Employee;

/**
 * Servlet implementation class employeeListServlet
 */
@WebServlet("/employeeListServlet")
public class employeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() throws ServletException {
		employeeDao = new EmployeeDao();
	}
	
    public employeeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
		
		HttpSession httpSession = request.getSession();
		
		String userName = (String) httpSession.getAttribute("userName");
		
		if (userName != null) {
			List<Employee> employees = employeeDao.getAllEmployees();
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("login.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
		
		String key = request.getParameter("personal");
		String value = request.getParameter("user-search");
		
		if (key.equals("dateOfBirth")) {
			SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat cv = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = fm.parse(value);
				value = cv.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	
		List<Employee> employees = employeeDao.searchEmployee(key, value);
		
		if (!employees.isEmpty()) {
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
			rd.forward(request, response);
		}
	}

}
