<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Login</title>
<link rel="stylesheet" href="./style/login.css">
<link rel="stylesheet" href="./scripts/mdl/material.min.css">
<script src="./scrypts/mdl/material.min.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
	<div id=div_login>
		<form id=loginform method="post" action="login">
			<input class="mdl-textfield__input tbox" id="ltbox" type="text" name="username"
				placeholder="username" />
			<br><input class="mdl-textfield__input tbox" id="lpbox"
				type="password" name="password" placeholder="password" /> 
			<br><input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
				type="submit" value="login" />
				<br>${messages}
		</form>
	</div>
</body>
</html>