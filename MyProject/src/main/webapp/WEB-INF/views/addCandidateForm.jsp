<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Add Candidate Form</title>
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

<h2>Register a New User</h2>

<form:form action="candidateSign.htm" commandName="candidate" method="post">

<table class= "table table-bordered xyz">

 <tr>
    <td>Email Id:</td>
    <td><form:input path="emailId" size="30" /> <font color="red"><form:errors path="emailId"/></font></td>
</tr> 

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="30" /> <font color="red"><form:errors path="password"/></font></td>
    <form:hidden path="type" value="candidate"/>
</tr>

<tr>
    <td>First Name:</td>
    <td><form:input path="firstName" size="30" /> <font color="red"><form:errors path="firstName"/></font></td>
</tr>

<tr>
    <td>Last Name:</td>
    <td><form:input path="lastName" size="30" /> <font color="red"><form:errors path="lastName"/></font></td>
</tr>


<tr>
    <td>Phone Number:</td>
    <td><form:input path="phone" size="30" /> <font color="red"><form:errors path="phone"/></font></td>
</tr>

<tr>
    <td>Skills</td>
    <td><form:input path="skill" size="30" /> <font color="red"><form:errors path="skill"/></font></td>
</tr>

<tr>
    <td colspan="4"><button type="submit" class="btn btn-info">Create</button></td>
</tr>
</table>

</form:form>

</body>
</html>