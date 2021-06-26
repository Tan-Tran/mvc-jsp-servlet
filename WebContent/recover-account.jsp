<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recover Account</title>
</head>
<body>
	<h1>Recover Account</h1>
	<h3></h3>
	<form action="<%= request.getContextPath() %>/recoveryAccountServlet" method="post">
		<label class="warning"> <c:out default="" value="${errorMessage}"></c:out></label>
		<div>
			<table>
				<tr>
					<td>User name</td>
					<td><input type="text" id="account" name="account" placeholder="User name" required="required" value=<c:out value="${txtAccount}"></c:out>></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="email" id="recover-emailemail" name="recover-email" placeholder="Email" required="required" value=<c:out value="${txtEmail}"></c:out>></td>
				</tr>
				<tr>
					<td>New password</td>
					<td><input type="password" id ="txtNewPassword" name="txtNewPassword" size="50" placeholder="New password" required="required"></td>
				</tr>
				<tr>
					<td><input type="submit" name="btnSubmit" value="Submit"></td>
				</tr>
			</table>
		</div>	
		<div>
			<a href="login.jsp">Back to Login Page</a>
		</div>
	</form>
</body>
</html>