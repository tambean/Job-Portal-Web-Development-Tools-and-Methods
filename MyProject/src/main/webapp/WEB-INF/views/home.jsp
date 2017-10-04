<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>
	 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<title>Home</title>
	<style>
body {
    background-color: lightblue;
}
div.container {

    margin-left: 500px;
    margin-top:300px;
}
.xyz{

    margin-left: 200px;
    margin-top:30px;
	
}
.yz{
	position: absolute;
    top: 20;
    right:80;
	
}
.z{
	position: absolute;
    top: 20;
    right:220;
	
}
</style>
</head>
<body>

<div class= "container">  
  <form class="form-horizontal" role="form">
  	<div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Search Job:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="pwd" placeholder="Enter Search Word">        
      </div>
      <div class="col-sm-4">
        <button type="submit" class="btn btn-default">Search</button>
      </div>
      <div class="col-sm-4 xyz">
      	<a href="employerSign.htm" class="btn btn-info" role="button">Employer Signup</a>
      	<a href="candidateSign.htm" class="btn btn-info" role="button">Candidate Signup</a>         
      </div>
    </div>    
  </form>
</div>

  <button type="button"  class="btn btn-info z" data-toggle="modal" data-target="#myModal" role="button">Login</button>

	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Login Credentials</h4>
				</div>
				<div class="modal-body">
				<form class="form-horizontal" action ="login.htm" method = "POST" role="form">
                  <div class="form-group">
				<table>
				<tr>
				<td>UserName</td>
				<td><input type="text" class="form-control" id="usr" name = "user"/></td>
				</tr>
				<tr>
				<td>Password</td>
				<td><input type="password" class="form-control" id="usr" name="password"/></td>
				</tr>				
				</table>				
				</div>
				<button type="button" class="btn btn-primary btn-block" data-dismiss="modal">Cancel</button>
				<input type="submit" class="btn btn-primary btn-block" value = "Login"></button>
				 <input type="hidden" name="action" value="login">
				</form>
	     		</div>
				<div class="modal-footer">					
				</div>
			</div>
		</div>
	</div>
</body>
</html>
