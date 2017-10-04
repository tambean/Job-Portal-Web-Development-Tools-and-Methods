<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background-color: lightblue;
}

.xyz {
	margin-left: 30px;
	margin-top: 30px;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${!empty sessionScope.email}">
			<jsp:include page="loginjsp.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="logout.jsp" />
		</c:otherwise>
	</c:choose>
	<h2>PROFILE OF THE CANDIDATE</h2>

	<h3>PROFESSIONAL EXPERIENCE</h3>
	
	<table class="table table-bordered xyz">
	<tr>
		<td><b>SKILLS </b></td>
			</tr>		
		<tr>
		<td>${skill}</td>
		</tr>
	</table>

	<table class="table table-bordered xyz">
		<tr>
			<td><b>EMPLOYER NAME</b></td>
			<td><b>DESIGNATION</b>
			<td><b>RESPONSIBILITIES</b>
			<td><b>START DATE</b>
			<td><b>END DATE</b>
		</tr>
		<c:forEach var="experience" items="${experience}">
			<tr>
				<td>${experience.companyName}</td>
				<td>${experience.designation}</td>
				<td>${experience.responsibilities}</td>
				<td>${experience.startDate}</td>
				<td>${experience.endDate}</td>
			</tr>
		</c:forEach>
	</table>
	<h3>ACADEMIC DETAILS</h3>
	<table class="table table-bordered xyz">
		<tr>
			<td><b>SCHOOL NAME</b></td>
			<td><b>SCHOOL ATTENDED FROM DATE</b>                
			<td><b>SCHOOL ATTENDED TO DATE </b>
			<td><b>UNDERGRADUATE DEGREE NAME</b>
			<td><b>UNDERGRADUATE ATTENDED FROM DATE</b>                
			<td><b>UNDERGRADUATE ATTENDED TO DATE </b>
			<td><b>GRADUATE DEGREE NAME</b>                
			<td><b>GRADUATE ATTENDED FROM DATE</b>                
			<td><b>GRADUATE ATTENDED TO DATE </b>
		</tr>
            <c:forEach var="education" items="${education}">
                <tr>
                	<td>${education.schoolName}</td>
                	<td>${education.schoolattendedFromDate}</td>
                	<td>${education.schoolattendedToDate}</td>
                	<td>${education.underGradDegreeName}</td>
                	<td>${education.underGradattendedFromDate}</td>
                	<td>${education.underGradattendedToDate}</td>
                	<td>${education.gradDegreeName}</td>
                	<td>${education.gradattendedFromDate}</td>
                	<td>${education.gradattendedToDate}</td>
                </tr>
            </c:forEach>
        </table>     
       
        
        <button type="button" class="btn-lg">
					<a href="homepage.htm"><b>GO TO YOUR HOMEPAGE</b>
				</button></body>
</html>