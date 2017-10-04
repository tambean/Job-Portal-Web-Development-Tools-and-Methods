
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

<h2>ADD JOBS</h2>

<form:form action="addjob.htm" commandName="job" method="post">

<table class = "table table-bordered xyz">

<tr>
    <td>Job title:</td>
    <td><form:input path="jobTitle" size="30" /> <font color="red"><form:errors path="jobTitle"/></font></td>
</tr>

 <tr>
    <td>Job Description :</td>
    <td><form:input path="jobDescription" size="30" /> <font color="red"><form:errors path="jobDescription"/></font></td>
</tr>

<tr>
    <td>Job Type:</td>
    <td><form:input path="jobType" size="30" /> <font color="red"><form:errors path="jobType"/></font></td>
</tr>

 <tr>
    <td>Experience:</td>
    <td><form:input path="experienceRequired" size="30" /> <font color="red"><form:errors path="experienceRequired"/></font></td>
</tr>


<tr>
    <td>Skills:</td>
    <td><form:input path="skillsRequired" size="30" /> <font color="red"><form:errors path="skillsRequired"/></font></td>
</tr>

 <tr>
    <td>Qualification :</td>
    <td><form:input path="qualificationRequired" size="30" /> <font color="red"><form:errors path="qualificationRequired"/></font></td>
</tr> 

<tr>
    <td>Location:</td>
    <td><form:input path="location" size="30" /> <font color="red"><form:errors path="location"/></font></td>
</tr>

<tr>
    <form:hidden path="postedBy" size="30" value ="${sessionScope.email}" />
</tr>


<tr>
    <td colspan="2"><input type="submit" class = "btn btn-info" value="Add Job" /></td>
    
</tr>
</table>
</form:form>
<button type="button" class="btn-lg"><a href = "homepage.htm"><b>GO TO YOUR HOMEPAGE</b></button>
</body>
</html>