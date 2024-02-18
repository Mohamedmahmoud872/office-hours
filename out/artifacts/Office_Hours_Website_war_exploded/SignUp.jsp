<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Sign Up</title>

	<link rel="stylesheet" href="bootstrap.min.css">
	<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
	<h1 class="page_title">Sign Up</h1>
	<div class="row">
		<div class="col-md-7 center_div">
			<form id="signupForm" action="SignupValidation" method="Post" onsubmit="return (submitUserForm(event));">
				<input type="text" class="mb-1 form-control" id="name" name="name" placeholder="Username" required="required">
				<input type="email" class="mb-1 form-control" id="email" value="" name="email" placeholder="Email" required="required" onchange="checkk(this)">
				<input type="text" class="mb-1 form-control" id="display" name="display" placeholder="displayName" required="required">
				<input type="text" class="mb-1 form-control" id="mobile" name="mobile" placeholder="Mobile" required="required">
				<input type="text" class="mb-1 form-control" id="role" name="role" placeholder="role" required="required">
				<div class="g-recaptcha myCaptcha" data-sitekey="6LeRlSkaAAAAAJ__ZVc-Gyt8usZgk9OnPpHDvCTZ" data-callback="verifyCaptcha"></div>
  			    <div id="g-recaptcha-error" style="display: none;" class="alert alert-danger"></div>
  			    <div id="show_response" style="display: none;" class="alert-danger alert"></div>
				<button type="submit" class="mt-1 btn btn-success">SignUp</button>
			</form>
		</div>
	</div>
</div>
	<script src='https://www.google.com/recaptcha/api.js'></script>
	<script src='validation.js'></script>
</body>
</html>