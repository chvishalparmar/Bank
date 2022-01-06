<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUP</title>
<link rel="stylesheet" href="Userdetails.css">
</head>
<body>
<header>
<nav>
<a href="HOME.jsp" >Home</a>
<a href="SignUp.jsp"class="active">SignUP</a>
<a href="SignIn.jsp" >SignIN</a>
<a href="Adminlogin.jsp">Admin Login</a>
</nav>
</header>
<h1>Create New Account</h1>
<form action ="SignUp" method ="post">
   <div class="c">
  <label for="fname">First name:</label><br>
  <input type="text" placeholder="Enter First Name" id="fname" name="fname" required><br>
  
  <label for="lname">Last name:</label><br>
  <input type="text" placeholder="Enter Last Name"id="lname" name="lname" required><br>
  
  <label for="address">Address Line 1 :</label><br>
  <input type="text" placeholder="Enter Your Address line 2"id="addressline1" name="addressline1" required><br>
  
  <label for="address">Address Line 2:</label><br>
  <input type="text" placeholder="Enter Your Address Line 1"id="addressline2" name="addressline2" required><br>
  
  
  <label for="state">State :</label><br>
  <input type="text" placeholder="Enter Your State"id="state" name="state" required><br>
  </div>
  <div class="d">
  <label for="city">City :</label><br>
  <input type="text" placeholder="Enter Your City"id="city" name="city" required><br>
  
  <label for="pincode">Pincode :</label><br>
  <input type="text" placeholder="Enter Your Pincode" id="pincode" name="pincode" required maxlength="6"><br>
  
  <label for="phone">Phone:</label><br>
  <input type="text" placeholder="Enter Your Phone Number" id="phone" name="phone" required><br>
  
  <label for="email">Email:</label><br>
  <input type="email" placeholder="Enter Your Email Address"id="email" name="email" required><br>
  
  
  <label for="Password">Password:</label><br>
  <input type="password" placeholder="Enter Password For Your Account" id="password" name="password" required minlength="8"><br>
  </div>
 <div class="e">
  <h4>Choose Account Type</h4>
  <label for="saving">Saving Account:</label>
  <input type="radio" id="saving" name="type" value ="saving" required>
  
  <label for="current">Current Account:</label>
  <input type="radio" id="current" name="type" value="current" required><br>
  
  <button type="submit" value="submit">Create New Account</button>
</div>
</form>
</body>
</html>