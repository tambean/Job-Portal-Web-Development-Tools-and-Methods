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
<p>Job Posted Successfully!!</p>
Please click Add Job for Posting Additional Jobs<br/>
<button type="button" class="btn-lg"><a href = "addjob.htm"><b>Add Jobs</b></button>
<button type="button" class="btn-lg"><a href = "homepage.htm"><b>GO TO YOUR HOMEPAGE</b></button>
</body>
</html>