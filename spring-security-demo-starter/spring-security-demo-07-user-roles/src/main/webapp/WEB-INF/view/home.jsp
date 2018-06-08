<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Security Demo Home Page</title>
</head>
<body>
	<h2>Welcome to Spring Security Demo Home Page</h2>
	<hr>
	<p>
		security demo
	</p>
	<hr>
	<!-- Display user name and roles -->
	<p>
		User: <security:authentication property="principal.username"/>
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>
	
	<security:authorize access="hasRole('MANAGER')">
		<hr>
		<!--  Add link to point to /leader... this is for manager -->
		<p>
			<a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a> (Only for Manager role)
		</p>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
		<hr>
		<!--  Add link to point to /systems... this is for admin -->
		<p>
			<a href="${pageContext.request.contextPath}/systems">Administrator Meeting</a> (Only for Administrator role)
		</p>
	</security:authorize>
	<hr>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" /> 
	</form:form>
</body>
</html>