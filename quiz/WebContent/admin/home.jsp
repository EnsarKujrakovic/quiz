<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../style/login.css">
<link rel="stylesheet" href="../scripts/mdl/material.min.css">
<script src="../scripts/mdl/material.min.js"></script>
<script src="../scripts/adminHome.js"></script>
<script src="../scripts/dialog-polyfill.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<title>Admin Homepage</title>
	
</head>
<body id=adminHomepageBody onload="adminHome.onLoad()">
	${username} ${userId}
	<div id="adminHomepage">
		<div id="adminHomepageButtons">
			<div><input style="width:100%" id="adminLogout" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"  onclick="location.href='./logout';"type="submit" value="Logout" /></div>
			<div><input style="width:100%" id="adminChangePassword" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"  onclick="adminHome.contentPassword()"type="submit" value="Change Password" /></div>
			<div><input style="width:100%" id="adminEditQuizes" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" onclick="adminHome.contentQuizes()" type="submit" value="Quizes" /></div>
			<div><input style="width:100%" id="adminEditUsers" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" onclick="adminHome.contentEditors()" type="submit" value="Editors" /></div>
			<div><input style="width:100%" id="adminInbox" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" onclick="adminHome.contentInbox()" type="submit" value="Inbox" /></div>
		</div>
		<div id="adminHomepageContent">
			<div id="adminHomepagePassword"></div>
			<div id="adminHomepageEditors"></div>
			<div id="adminHomepageQuizes"></div>
			<div id="adminHomepageInbox"></div>
		</div>
	</div>
</body>
</html>