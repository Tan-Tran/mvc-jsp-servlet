<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="static/addEmployee.css">
<title>Add Employee</title>
<script>
    function goBack() {	
        window.history.back();
        window.opener.location.reload();
        window.close();
    }
</script>
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
                <li>Welcome <span style="color: red"><%=session.getAttribute("userName")%></span></li>
                <li><a href="<%= request.getContextPath()%>/login.jsp">Logout</a></li>
            </ul>
        </div>

        <div id="contain">

            <div id="contain-left">
                <ul class="contain-left-nav">
                    <li>Dashboard</li>
                    <li>
                        Employee Managerment
                        <ul class="contain-subnav-left">
                            <li><a href="<%= request.getContextPath()%>/employeeListServlet">Employee List</a></li>
                            <li><a href="<%= request.getContextPath()%>/addEmployeeServlet">Add Employee</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div id="contain-right">
                <h1>Add Employee</h1>
                <hr>
                <form action="<%= request.getContextPath() %>/addEmployeeServlet" method="POST">
                    <input type="hidden" id="redirect" name="id" value=<c:out value="${employee.id}"></c:out>>
                    <label for="fname">First name</label><br>
                    <input type="text" id="fname" name="fname" placeholder="First name" required="required" value=<c:out value="${employee.firstName}"></c:out>><br>

                    <label for="lname">Last name</label><br>
                    <input type="text" id="lname" name="lname" placeholder="Last name" required="required" value=<c:out value="${employee.lastName}"></c:out>><br>

                    <label for="phone">Phone number</label><br>
                    <input type="tel" id="phone" name="phone" placeholder="phone" required="required" pattern="(84|0[3|5|7|8|9])+([0-9]{8})$" title="Must be contain 10 numbers and begin 0 or 84" value=<c:out value="${employee.phone}"></c:out> ><br>

                    <label for="birthdate">Date of birth</label><br>
                    <input type="date" id="birthdate" name="birthdate" placeholder="Birth Date" required="required" value=<c:out value="${employee.dateOfBirth}"></c:out> ><br>

                    <label for="gender">Gender</label><br>
                    <input <c:if test = "${employee.gender == 1}">checked="checked"</c:if>type="radio" id="male" name="gender" value="male" >
					<label for="male">Male</label><br>
					<input <c:if test = "${employee.gender == 0}">checked="checked"</c:if> type="radio" id="female" name="gender" value="femail">
					<label for="female">Female</label><br>

                    <label for="account">Account</label><br>
                    <input type="text" id="account" name="account" placeholder="Account" required="required" value=<c:out value="${account.account}"/>><br>

                    <label for="email">Email</label><br>
                    <input type="email" id="email" name="email" placeholder="Email" required="required" value=<c:out value="${account.email}"/>><br>

                    <label for="password">Password</label><br>
                    <input type="password" id="password" name="password" placeholder="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters" value=<c:out value="${account.password}"/>><br>
					
                    <label for="address">Address</label><br>
					<c:choose>
					    <c:when test="${employee.address != null}">
					        <textarea rows="4" cols="50" name="address">${employee.address}</textarea><br>
					    </c:when>    
					    <c:otherwise>
					        <textarea rows="4" cols="50" name="address">Enter text here...</textarea><br>
					    </c:otherwise>
					</c:choose>

                    <label for="status">Status</label><br>
                    <input <c:if test = "${account.status == 1}">checked="checked"</c:if> type="checkbox" name="status" value = "1"> Active <br>

                    
                    <label for="department">Department</label><br>
                    <select id="department" name="department">
                        <option value="fsoft">Fsoft Academy</option>
                    </select><br>

                    <label for="remark">Remark</label><br>
                    
                    <c:choose>
					    <c:when test="${employee.remark != null}">
					        <textarea rows="4" cols="50" name="remark">${employee.remark}</textarea><br>
					    </c:when>    
					    <c:otherwise>
					        <textarea rows="4" cols="50" name="remark">Enter text here...</textarea><br>
					    </c:otherwise>
					</c:choose>
                          
                    <input type="button" onclick="goBack()" value="<<Back">
                    
                    <input type="reset" value="Reset">
                    <input type="submit" name="function" value="Add/Update">
<!--                     <input type="submit" name="function" value="Delete"> -->
                    <c:if test = "${employee.id != null}"><input type="submit" name="function" value="Delete"></c:if>
                </form>
            </div>
        </div>
    </div>
</body>
</html>