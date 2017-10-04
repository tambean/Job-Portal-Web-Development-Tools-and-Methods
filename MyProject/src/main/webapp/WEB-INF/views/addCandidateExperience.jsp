
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<style>
body {
    background-color: lightblue;
}
.xyz{

    margin-left: 200px;
    margin-top:30px;
	
}
</style>
<body>
	<c:choose>
            <c:when test="${!empty sessionScope.email}">
                <jsp:include page="loginjsp.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="logout.jsp"/>
            </c:otherwise>
  </c:choose>

<h1>Add Experience</h1>

<form:form action="addExperience.htm" commandName="experience" method="post">

<table class= "table table-bordered xyz">

 <tr>
    <td>Company Name:</td>
    <td><form:input path="companyName" size="30" /> <font color="red"><form:errors path="companyName"/></font></td>
</tr> 

<tr>
    <td>Designation:</td>
    <td><form:input path="designation" size="30" /> <font color="red"><form:errors path="designation"/></font></td>

</tr>

<tr>
    <td>Responsibilities:</td>
    <td><form:input path="responsibilities" size="30" /> <font color="red"><form:errors path="responsibilities"/></font></td>
</tr>

<tr>
    <td>Start Date:</td>
    <td><form:input path="startDate" type="date"  size="30" /> <font color="red"><form:errors path="startDate"/></font></td>
</tr>


<tr>
    <td>End Date</td>
    <td><form:input path="endDate" type="date"  size="30" /> <font color="red"><form:errors path="endDate"/></font></td>
</tr>
<tr>
    <td colspan="4"><button type="submit" class="btn btn-info">Add Experience</button></td>
</tr>		
	<input type='hidden' value='${sessionScope.email}' name='email' />
	</table>
</form:form>
<br><br>
<button type="button" class="btn btn-info"><a href = "homepage.htm"><b>Go to Homepage</b></button>	
</body>
</html>