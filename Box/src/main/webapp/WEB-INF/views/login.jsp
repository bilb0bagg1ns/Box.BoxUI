<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<div align="center">
		<h1>Logincc</h1>
		<form:form action="home" method="post" modelAttribute="user">
		<table>
			<tr>
				<td>Username:</td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Sign In"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>