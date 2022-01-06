<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignIN</title>
<link rel="stylesheet" href="Userdetails.css">
</head>
<body>
<header>
<nav>
<a href="HOME.jsp">Home</a>
<a href="SignUp.jsp">SignUP</a>
<a href="SignIn.jsp" class="active">SignIN</a>
<a href="Adminlogin.jsp"  >Admin Login</a>
</nav>
</header>
<div class="a">
<span style="color:yellow; font-size:20px; font-weight: bold;">${loginresult}</span>
<h1>Login To Account</h1>
<form method ="post" action="SignIn">
 <label for="userid">User id:</label><br>
  <input type="text" placeholder="Enter UserID" id="userid" name="userid" required><br>
  <label for="password">Password:</label><br>
  <input type="password" placeholder="Enter Password" id="password" name="password" required><br>
  <button type="submit">Login</button>
  
</form> </div>
</body>
</html>