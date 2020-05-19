<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="resources/js/captchaScripts.js"></script>
<title>Account Update</title>
</head>
<body>

	<ul class="nav nav-pills" role="tablist">
		<li role="presentation"><a href="home.jsp">Home</a></li>
		<li role="presentation"><a href="login">Login</a></li>
		<li role="presentation"><a href="registration">Registration</a></li>
	</ul>

	<form:form id="updateForm" modelAttribute="user" action="updateProcess"
		method="post">

		<table align="center">

			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" required="true" /></td>
			</tr>

			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="confirmPassword">Confirm Password</form:label></td>
				<td><form:password path="confirmPassword" required="true" /></td>
			</tr>

			<tr>
				<td><form:label path="captcha">Captcha:</form:label></td>
				<td><form:input path="captcha" required="true"></form:input></td>
				<td><img src="resources/images/reset.png" style="width: 50px;"
					onclick="refreshCaptcha()" /><img
					src='${pageContext.request.contextPath}/captcha'></td>
			</tr>

			<tr>
				<td></td>
				<td><form:button id="register" name="register">Update</form:button></td>
			</tr>

			
			<tr>
				<td> <form:errors path="*" cssStyle="font-style: italic; color: red;"/> </td>
			</tr>

		</table>
	</form:form>

	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
		</tr>
	</table>

</body>
</html>