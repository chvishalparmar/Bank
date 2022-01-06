<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="Userdetails.css">
</head>
<body>
<header>
<nav>
<a href="HOME.jsp">Home</a>
<a href="SignUp.jsp">SignUP</a>
<a href="SignIn.jsp">SignIN</a>
<a href="Adminlogin.jsp"  class="active">Admin Login</a>
</nav>
</header>
<div class="a">
<span Style="color:yellow;font-weight: bold;font-size: 20px;">${adminresult}</span>
<span style="color:red;">${id}</span>
<h1>Admin login</h1>
<form method ="post" action="Adminlogin">

 <label for="adminid">Admin id:</label><br>
  <input type="text" placeholder="Enter Admin I'D" id="adminid" name="adminid" required><br>
  
  <label for="password">Password:</label><br>
  <input type="password" placeholder="Enter Password" id="password" name="password" required><br>
  
  <button type="submit" value="submit">Login</button>

</form></div>
</body>
</html>