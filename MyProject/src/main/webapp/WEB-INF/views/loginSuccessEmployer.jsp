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
<h1>Welcome</h1>
<div class="btn-group">
  	<button type="button" class="btn btn-lg"><a href = "addjob.htm"><b>POST JOBS</b></a></button>
  	<button type="button" class="btn btn-lg"><a href = "appliedjobcandidate.htm?email=${sessionScope.email}"><b>CANDIDATE APPLICATIONS</b></a></button>
  	<button type="button" class="btn btn-lg"><a href = "viewEmployerPostedJob.htm?email=${sessionScope.email}"><b>VIEW UPDATE POSTED JOBS</b></a></button>
  </div>
</body>
</html>