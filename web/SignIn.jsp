<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Sign In</title>

	<link rel="stylesheet" href="bootstrap.min.css">
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="container">
		<h1 class="page_title">Sign In</h1>
		<div class="row">
			<div class="col-md-7 center_div">
				<form id="SignInForm" action="SigINValidation" method="Post" onsubmit="return CheckCredentials(event);">
					<input type="text" class="form-control" id="name" name="name" placeholder="Username" required="required">
					<input type="password" class="form-control mt-2" id="pass" name="pass" placeholder="Password" required="required">

					<input class="mt-2" type="radio" id="Student" name="role" value="Student" required>
					<label for="Student" class="mr-2 mb-1 mt-1 font-weight-bold">Student</label>
					<input type="radio" id="Staff" name="role" value="Staff" required>
					<label for="Staff" class="font-weight-bold mt-1 mb-1">Staff</label><br>
					<input type="submit" class="btn btn-primary mt-2" value="Sign In">
					<a href="SignUp.jsp" class="btn btn-primary mt-2">Sign Up</a>
					<div id="show_response" style="color: red;" class="mt-3"></div>
				</form>
			</div>
		</div>
	</div>
	<script src="jquery.js"></script>
	<script src="validation.js"></script>
</body>
</html>