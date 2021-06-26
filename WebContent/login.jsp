<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<h1>Login page</h1>
	<h3></h3>
	<form action="<%= request.getContextPath() %>/login" method="post">
		<label class="warning"> <c:out default="" value="${errorMessage}"></c:out></label>
		<div>
			<table>
				<tr>
					<td>User name</td>
					<td><input type="text" name="txtUsername" size="50" required="required"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="txtPassword" size="50" required="required"></td>
				</tr>
				<tr>
					<td><input type="submit" name="btnSubmit" value="Login"></td>
					<td><input type="reset" name="btnReset" value="Reset"></td>
				</tr>
			</table>
		</div>	
		<div>
			<a href="recover-account.jsp">Forgot password</a>
		</div>
	</form>
</body>
</html>