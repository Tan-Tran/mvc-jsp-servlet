package com.fa.carpark.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fa.carpark.dao.AccountDao;
import com.fa.carpark.dao.EmployeeDao;
import com.fa.carpark.model.Account;
import com.fa.carpark.model.Employee;

/**
 * Servlet implementation class editEmployeeServlet
 */
@WebServlet("/addEmployeeServlet")
public class addEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeDao employeeDao;
	private AccountDao accountDao;
	
	@Override
	public void init() throws ServletException {
		employeeDao = new EmployeeDao();
		accountDao = new AccountDao();
	}
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("userName") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/AddEmployee.jsp");
//			getServletConfig().getInitParameter("userName");
			ServletConfig config = getServletConfig();
			String id = request.getParameter("id");
			System.out.println(id);
			if (id != null) {
				Employee e = employeeDao.findEmployee(Integer.valueOf(id));
				Account a = accountDao.findAccountByEmployeeId(e.getId());
//				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/AddEmployee.jsp");
				request.setAttribute("employee", e);
				request.setAttribute("account", a);
				rd.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/AddEmployee.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect("login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		System.out.println(id);
		String function = request.getParameter("function");
		
		if (function.equals("Add/Update")) {
			// Do add functions
			if(id.equals("")) {
		
					Employee newEmployee = new Employee();
					
					//=======================================================
					
					String firstName = request.getParameter("fname");
					newEmployee.setFirstName(firstName);
					
					String lastName = request.getParameter("lname");
					newEmployee.setLastName(lastName);
					
					String phone = request.getParameter("phone");
					newEmployee.setPhone(phone);
					
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					
					Date date;
					try {
						date = fm.parse(request.getParameter("birthdate"));
						newEmployee.setDateOfBirth(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					String gender = request.getParameter("gender");
					Integer valueGender;
					if (gender.equals("male")) {
						valueGender = 1;
					} else {
						valueGender = 0;
					}
					newEmployee.setGender(valueGender);
					
					String address = request.getParameter("address");
					System.out.println(address);
					newEmployee.setAddress(address);
					
					String department = request.getParameter("department");
					newEmployee.setDepartmentName(department);
					
					String remark = request.getParameter("remark");
					System.out.println(remark);
					newEmployee.setRemark(remark);
					
					employeeDao.addEmployee(newEmployee);
					
					//=======================================================
					
					Account newAccount = new Account();
					
					String account = request.getParameter("account");
					newAccount.setAccount(account);
					
					String email =  request.getParameter("email");
					newAccount.setEmail(email);
					
					String password = request.getParameter("password");
					newAccount.setPassword(password);
					
					String status = request.getParameter("status");
					Integer valueStatus;
					if (status != null) {
						valueStatus = 1;
					} else {
						valueStatus = 0;
					}
					newAccount.setStatus(valueStatus);
					
					newAccount.setEmployee(newEmployee);
					
					accountDao.addAccount(newAccount);
					
					HttpSession session = request.getSession();
					
					List<Employee> employees = employeeDao.getAllEmployees();
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
					request.setAttribute("employees", employees);
					dispatcher.include(request, response);
					
			// Do update functions	
			} else {
				
				Employee employee = employeeDao.findEmployee(Integer.valueOf(id));
				
				if (employee != null) {
					employee.setId(Integer.valueOf(id));
					Account account = accountDao.findAccountByEmployeeId(Integer.valueOf(id));
					
					
					String firstName = request.getParameter("fname");
					employee.setFirstName(firstName);
					
					String lastName = request.getParameter("lname");
					employee.setLastName(lastName);
					
					String phone = request.getParameter("phone");
					employee.setPhone(phone);
					
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					
					Date date;
					try {
						date = fm.parse(request.getParameter("birthdate"));
						employee.setDateOfBirth(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					String gender = request.getParameter("gender");
					Integer valueGender;
					if (gender.equals("male")) {
						valueGender = 1;
					} else {
						valueGender = 0;
					}
					employee.setGender(valueGender);
					
					String address = request.getParameter("address");
					System.out.println(address);
					employee.setAddress(address);
					
					String department = request.getParameter("department");
					employee.setDepartmentName(department);
					
					String remark = request.getParameter("remark");
					System.out.println(remark);
					employee.setRemark(remark);
					
					employeeDao.addEmployee(employee);
					
					//=======================================================
					
					account.setId(Integer.valueOf(id));
					
					String inpputAccount = request.getParameter("account");
					account.setAccount(inpputAccount);
					
					String email =  request.getParameter("email");
					account.setEmail(email);
					
					String password = request.getParameter("password");
					account.setPassword(password);
					
					String status = request.getParameter("status");
					
					Integer valueStatus;
					if (status != null) {
						valueStatus = 1;
					} else {
						valueStatus = 0;
					}
					account.setStatus(valueStatus);
					
					accountDao.addAccount(account);
					
					List<Employee> employees = new ArrayList<>();
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
					employees.add(employee);
					request.setAttribute("employees", employees);
					dispatcher.include(request, response);
				} else {
					Employee newEmployee = new Employee();
					
					//=======================================================
					
					String firstName = request.getParameter("fname");
					newEmployee.setFirstName(firstName);
					
					String lastName = request.getParameter("lname");
					newEmployee.setLastName(lastName);
					
					String phone = request.getParameter("phone");
					newEmployee.setPhone(phone);
					
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					
					Date date;
					try {
						date = fm.parse(request.getParameter("birthdate"));
						newEmployee.setDateOfBirth(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					String gender = request.getParameter("gender");
					Integer valueGender;
					if (gender.equals("male")) {
						valueGender = 1;
					} else {
						valueGender = 0;
					}
					newEmployee.setGender(valueGender);
					
					String address = request.getParameter("address");
					System.out.println(address);
					newEmployee.setAddress(address);
					
					String department = request.getParameter("department");
					newEmployee.setDepartmentName(department);
					
					String remark = request.getParameter("remark");
					System.out.println(remark);
					newEmployee.setRemark(remark);
					
					employeeDao.addEmployee(newEmployee);
					
					//=======================================================
					
					Account newAccount = new Account();
					
					String account = request.getParameter("account");
					newAccount.setAccount(account);
					
					String email =  request.getParameter("email");
					newAccount.setEmail(email);
					
					String password = request.getParameter("password");
					newAccount.setPassword(password);
					
					String status = request.getParameter("status");
					Integer valueStatus;
					if (status != null) {
						valueStatus = 1;
					} else {
						valueStatus = 0;
					}
					newAccount.setStatus(valueStatus);
					
					newAccount.setEmployee(newEmployee);
					
					accountDao.addAccount(newAccount);
					
					List<Employee> employees = employeeDao.getAllEmployees();
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
					request.setAttribute("employees", employees);
					dispatcher.include(request, response);
				}
			}
			// Do delete functions
		} else {
			if (id != "") {
				
				Employee deleteEmployee = employeeDao.findEmployee(Integer.valueOf(id));
				
				if (deleteEmployee != null) {
					Account deleteAccount = accountDao.findAccountByEmployeeId(deleteEmployee.getId());
					
					accountDao.deleteAccount(deleteAccount);
					
					employeeDao.deleteEmployee(deleteEmployee);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
					
					List<Employee> employees = employeeDao.getAllEmployees();
					
					if (employees != null) {
						request.setAttribute("employees", employees);
						dispatcher.forward(request, response);
					}
				} else {			
					response.setContentType("text/html");
					PrintWriter pw=response.getWriter();
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Wrong.Can not delete!!');");
					pw.println("</script>");
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/EmployeeManagment.jsp");
					List<Employee> employees = employeeDao.getAllEmployees();
					request.setAttribute("employees", employees);
					rd.include(request, response);
				}
			}
		}
	}
}
