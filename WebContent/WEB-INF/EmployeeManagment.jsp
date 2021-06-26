<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="static/employeeManagment.css">
<title>Employee Managment</title>
</head>
<body>
	<div id="main">
        <div id="header">
            <ul class="nav-left">
                <li>
                    Employee
                </li>
            </ul>

            <ul class="nav-right">
                <li>Welcome <%=session.getAttribute("userName")%></li>
                <li><a href="<%=request.getContextPath()%>/login.jsp">Logout</a></li>
            </ul>
        </div>

        <div id="contain">

            <div id="contain-left">
                <ul class="contain-left-nav">
                    <li>Dashboard</li>
                    <li>
                        Employee Management
                        <ul class="contain-subnav-left">
                            <li><a href="<%= request.getContextPath()%>/employeeListServlet">Employee List</a></li>
                            <li><a href="<%= request.getContextPath()%>/addEmployeeServlet">Add Employee</a></li>
                        </ul>
                    </li>
                </ul>
            </div>

            <div id="contain-right">
                <h1>Employee List</h1>
                <hr>
                <div class="contain-right-form">
                    <form action="<%= request.getContextPath() %>/employeeListServlet" method="POST">
                        <input type="text" name ="user-search" placeholder="User search">
                        <label for="filter">Filter By</label>
                        <select id="personal" name="personal">
                        	<option value="id">ID</option>
                            <option value="CONCAT_WS(' ',firstName, lastName)">Name</option>
                            <option value="dateOfBirth">Birth Day</option>
                            <option value="address">Address</option>
                            <option value="phone">Phone</option>
                            <option value="departmentName">Department</option>
                        </select>
                        <input type="submit" value="Search">
                    </form>
                </div>
                <div class="contain-right-table">
                    <table>
					  <tr>
					    <th>ID</th>
					    <th>Name</th>
					    <th>Date of birth</th>
					    <th>Address</th>
					    <th>Phone number</th>
					    <th>Department</th>
					    <th>Action</th>
					  </tr>
					<c:choose>
					    <c:when test="${employees != null}">
					    	<c:forEach items="${employees}" var="employee">
						       <tr>
							    <td><c:out value="${employee.id}"></c:out></td>
							    <td><c:out value="${employee.firstName} ${employee.lastName}"></c:out></td>
							    <td><fmt:formatDate value="${employee.dateOfBirth}" pattern="dd/MM/yyyy"/></td>
							    <td><c:out value="${employee.address}"></c:out></td>
							    <td><c:out value="${employee.phone}"></c:out></td>
							    <td><c:out value="${employee.departmentName}"></c:out></td>
 								<td><a href="<%=request.getContextPath()%>/addEmployeeServlet?id=${employee.id}" style="text-decoration: none">View</a></td>
							  </tr>
						  </c:forEach>
					    </c:when>    
					    <c:otherwise>
					        <tr>
						    <td>No info.</td>
						    <td>No info.</td>
						    <td>No info.</td>
						    <td>No info.</td>
						    <td>No info.</td>
						    <td>No info.</td>
							<td><a href="<%=request.getContextPath()%>/addEmployeeServlet?id=${employee.id}" style="pointer-events: none; text-decoration: none">View</a></td>
						  </tr>
					    </c:otherwise>
					</c:choose>
					  
					  
<%--  					 <c:forEach items="${employees}" var="employee">
						  <tr>
						    <td><c:out value="${employee.id}"></c:out></td>
						    <td><c:out value="${employee.firstName} ${employee.lastName}"></c:out></td>
						    <td><c:out value="${employee.dateOfBirth}"></c:out></td>
						    <td><c:out value="${employee.address}"></c:out></td>
						    <td><c:out value="${employee.phone}"></c:out></td>
						<td><form action="<%=request.getContextPath()%>/addEmployeeServlet" method="get">
								<input type="submit" name="view" value="${employee.id}" placeholder="View">
							</form></td>
							<td><a href="<%=request.getContextPath()%>/addEmployeeServlet?id=${employee.id}">View</a></td>
						  </tr>
					</c:forEach> --%>
					</table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>