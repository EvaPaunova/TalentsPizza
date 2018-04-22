<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css.css">
<title>DominosPizza | My profile</title>
</head>
<body>
<div class="w3-top" style = "font-family:Arial">
  <div class="w3-bar w3-white w3-padding w3-card">
    <a href="logged.html" ><img border="0"src="home-icon.png" width="40" height="40"></a>
    <!-- Right-sided navbar links. Hide them on small screens -->
    <div class="w3-right w3-hide-small" style = "font-family:Arial">
      <a href="profile.html"><img src = "user-icon.png" width = "25" height = "25" style = "margin-top:0px; margin-bottom:0;"></a>
      <a href="#menu" class="w3-bar-item w3-button">Menu</a>
      <form action = "logout" method = "post"><input type= "submit" class="w3-bar-item w3-button" value = "Logout"></form>
    </div>
  </div> 
</div>
<div style = " margin: auto; text-align:center;">
	<form action = "profile" method = "get">
	<img src="user-icon.png" width = "80" style = "margin-top:5px;opacity:1;possition:center;display: block;margin-top:150px;margin-left: auto; margin-right: auto;"><h3 style= "border: none;color: black;padding: 10px 150px;display: inline-block;font-size: 18px;  border-radius: 10px;">Profile</h3>
	<%  User user  = (User) session.getAttribute("user"); %>
	<table style = "margin: auto;">
	<tr>
					<td>First name:<input type="text" placeholder="<%= user.getFirstName()%>"></td>
	</tr>
	<tr>
					<td>Lastname:<input type="text" placeholder="<%= user.getLastName()%>"></td>
	</tr>
	<tr>
					<td>Username:<input type="text" placeholder="<%= user.getUsername()%>"></td>
	</tr>
	<tr>
					<td>Password:<input type="password" placeholder="**********"></td>
	</tr>
	<tr>
					<td>Confirm password:<input type="password" placeholder="**********"></td>
	</tr>
	<tr>
					<td>Email:<input type="email" placeholder="<%= user.getEmail()%>"></td>
	</tr>
	<tr>
					<td>Phone:<input type="text" placeholder="<%= user.getPhoneNumber()%>"></td>
	</tr>		
	<tr>
					<td><input type="submit" value = "Save changes"></td>
	</tr>		
	</table>
	</form>
</div>
</body>
</html>