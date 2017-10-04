
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

<h1>Add Education</h1>

<form:form action="addEducation.htm" commandName="education" method="post">

<table class= "table table-bordered xyz">

 <tr>
    <td>School Name:</td>
    <td><form:input path="schoolName" size="30" /> <font color="red"><form:errors path="schoolName"/></font></td>
</tr> 
<tr>
    <td>School Attended From Date:</td>
    <td><form:input path="schoolattendedFromDate" type="date"  size="30" /> <font color="red"><form:errors path="schoolattendedFromDate"/></font></td>
</tr>


<tr>
    <td>School Attended To Date</td>
    <td><form:input path="schoolattendedToDate" type="date"  size="30" /> <font color="red"><form:errors path="schoolattendedToDate"/></font></td>
</tr>


<tr>
    <td>Undergraduate Degree Name:</td>
    <td><form:input path="underGradDegreeName" size="30" /> <font color="red"><form:errors path="underGradDegreeName"/></font></td>

</tr>
<tr>
    <td>Undergraduate Attended From Date:</td>
    <td><form:input path="underGradattendedFromDate" type="date"  size="30" /> <font color="red"><form:errors path="underGradattendedFromDate"/></font></td>
</tr>

<tr>
    <td>Undergraduate Attended To Date</td>
    <td><form:input path="underGradattendedToDate" type="date"  size="30" /> <font color="red"><form:errors path="underGradattendedToDate"/></font></td>
</tr>



<tr>
    <td>Graduate Degree Name:</td>
    <td><form:input path="gradDegreeName" size="30" /> <font color="red"><form:errors path="gradDegreeName"/></font></td>
</tr>

<tr>
    <td>Graduate Attended From Date:</td>
    <td><form:input path="gradattendedFromDate" type="date"  size="30" /> <font color="red"><form:errors path="gradattendedFromDate"/></font></td>
</tr>


<tr>
    <td>Graduate Attended To Date</td>
    <td><form:input path="gradattendedToDate" type="date"  size="30" /> <font color="red"><form:errors path="gradattendedToDate"/></font></td>
</tr>
<tr>
    <td colspan="4"><button type="submit" class="btn btn-info">Add Education</button></td>
</tr>		
	<input type='hidden' value='${sessionScope.email}' name='email' />
	</table>
</form:form>
<br><br>
<button type="button" class="btn btn-info"><a href = "homepage.htm"><b>Go to Homepage</b></button>	
</body>
</html>