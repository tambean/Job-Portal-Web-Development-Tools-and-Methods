<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
    background-color: lightblue;
}
.xyz{

    margin-left: 30px;
    margin-top:30px;
	
}

</style>
</head>
    <body>
    	<c:choose>
            <c:when test="${!empty sessionScope.email}">
                <jsp:include page="loginjsp.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="logout.jsp"/>
            </c:otherwise>
  </c:choose>
    	<h2>APPLIED CANDIDATES </h2>
    	<br/>
        <table class = "table table-bordered xyz">
            <tr>
                <td><b>Job ID</b></td>
                <td><b>Employer Name</b></td>                
                <td><b>Candidate EMAIL</b></td>
                <td><b>Candidate Profile</b></td>
            
            </tr>
            <c:forEach var="app" items="${apps}">
                <tr>
               		<td>${app.jobID}</td>
                	<td>${app.employerName}</td>
                	<td>${app.sender}</td>
                	<td><a href = 'viewcandidateprofile.htm?action=${app.sender}' >VIEW PROFILE</a></td>
                </tr>
            </c:forEach>
        </table>
        <button type="button" class="btn-lg"><a href = "homepage.htm"><b>GO TO YOUR HOMEPAGE</b></button>
    </body>
</html>