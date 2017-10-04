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
    	<h2>YOUR JOBS </h2>
    	<br/>
        <table class= "table table-bordered xyz">
            <tr>
            	<td><b>JOB ID</b></td>
                <td><b>EXPERIENCE REQUIRED</b></td>
                <td><b>QUALIFICATION REQUIRED</b></td>
                <td><b>SKILLS REQUIRED</b></td>
                <td><b>JOB DESCRIPTION</b></td>
                <td><b>JOB TITLE</b></td>
                <td><b>JOB TYPE</b></td>
                <td><b>LOCATION</b>
                <td><b>POSTED ON</b></td>
                <td><b>POSTED BY</b></td>
                <td><b>UPDATE</b></td>                 
            </tr>
            <c:forEach var="job" items="${jobs}">
                <tr>
              		<td>${job.jobID}</td>
                	<td>${job.experienceRequired}</td>
                	<td>${job.qualificationRequired}</td>
                	<td>${job.skillsRequired}</td>
                    <td>${job.jobDescription}</td>
                    <td>${job.jobTitle}</td>
                     <td>${job.jobType}</td>
                    <td>${job.location}</td>
                    <td>${job.postedOn}</td>
                    <td>${job.postedBy}</td>
                    <td><a href = 'employerupdatePostedJob.htm?email=${sessionScope.email}&Id= ${job.jobID}'>Update Job Details</a></td>                    
                </tr>
            </c:forEach>
        </table>
        <button type="button" class="btn-lg"><a href = "homepage.htm"><b>GO TO YOUR HOMEPAGE</b></button>
    </body>
</html>