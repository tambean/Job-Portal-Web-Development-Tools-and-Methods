<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
body {
    background-color: lightblue;
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

	<div class="btn-group">
  	<h1> WELCOME TO YOUR HOMEPAGE </h1>
  	<button type="button" class="btn"><a href = "lookjob.htm"><b>LOOK FOR JOBS</b></button>
		
<c:choose>
    <c:when test="${sessionScope.user.type == 'candidateEdu'}">
       <button type="button" class="btn" disabled><b>ADD EDUCATION</b></button>
    </c:when>
         <c:when test="${condition == 'disable'}">
        <button type="button" class="btn" disabled><b>ADD EDUCATION</b></button>
    </c:when>
    <c:when test="${sessionScope.user.type == 'candidate'}">
        <button type="button" class="btn"><a href = "addEducation.htm"><b>ADD EDUCATION</b></button>
    </c:when>  
    <c:otherwise>
        No comment sir...
    </c:otherwise>
</c:choose>		
		
		
		
  	<button type="button" class="btn"><a href = "addExperience.htm"><b>ADD EXPERIENCE</b></button>
  	<button type="button" class="btn"><a href = "viewJobs.htm?email=${sessionScope.email}"><b>VIEW APPLIED JOBS</b></button>
  	<button type="button" class="btn"><a href = "viewMyProfile.htm?email=${sessionScope.email}"><b>VIEW MY PROFILE</b></button>

	</div>
</body>
</html>