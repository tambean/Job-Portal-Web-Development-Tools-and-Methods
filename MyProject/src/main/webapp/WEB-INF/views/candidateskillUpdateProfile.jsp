<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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

.xyz {
	margin-left: 30px;
	margin-top: 30px;
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
<h2>Update Your Skills</h2>
<form:form action="viewUpdatedMySkillProfile.htm?email=${sessionScope.email}&update=skill" commandName="can" method="post">

<table class= "table table-bordered xyz">
 
<tr>
    <td>Skills</td>
    <td><form:input path="skill" size="30" value = '${skills}'/><font color="red"><form:errors path="skill"/></font></td>
</tr>

<tr>
    <td colspan="4"><button type="submit" class="btn btn-info">Save</button></td>
</tr>
</table>

</form:form>

</body>
</html>