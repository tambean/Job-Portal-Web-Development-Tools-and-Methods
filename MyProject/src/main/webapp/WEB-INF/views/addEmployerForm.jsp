<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Add Employer Form</title>
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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

<h2>Register a New Employer</h2>

<form:form action="employerSign.htm" commandName="employer" method="post">

<table class= "table table-bordered xyz">

 <tr>
    <td>Email Id:</td>
    <td><form:input path="emailId" size="30" /> <font color="red"><form:errors path="emailId"/></font></td>
</tr> 

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="30" /> <font color="red"><form:errors path="password"/></font></td>
    <form:hidden path="type" value="employer"/>
</tr>

<tr>
    <td>Employer Name:</td>
    <td><form:input path="employerName" size="30" /> <font color="red"><form:errors path="employerName"/></font></td>
</tr>

<tr>
    <td>Location:</td>
    <td><form:input path="location" size="30" /> <font color="red"><form:errors path="location"/></font></td>
</tr>
<tr>
    <td colspan="4"><button type="submit" class="btn btn-info">Create</button></td>
</tr>
</table>

</form:form>

</body>
</html>