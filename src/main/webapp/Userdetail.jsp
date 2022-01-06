<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
<link rel="stylesheet" href="Userdetails.css">
</head>
<body>

<%   
     request.getSession(false);  
     String userid = (String) session.getAttribute("Userid");
     if(userid == null)
     {out.println("<h2 align=center><span style=color:red>Please Login!</span></h2>");
     
     }
     else
     { 
    	 out.println("<div class=\"result\"><h1>"+"Welcome, You are Successfully Login. Your User I'd is "+userid+"</h1></div>");
     }
%>
<aside class="left">
   <form style="width: 100%;" >
   <button value="checkbal" id="checkbal" name="checkbal" formaction="Useractivity" formmethod="post">Check Balance</button><br>
  <button value="Addresschange" id="addresschange" name="addresschange" formaction="Updateaddress.html" formmethod="post">Address Change</button><br>
  <button value="Moneydeposit" id="moneydeposit" name="moneydeposit" formaction="moneydeposit.html" formmethod="post">Money Deposit</button><br>
  <button value="Moneywithdrawal" id="moneywithdrawal" name="moneywithdrawal" formaction="moneywithdrawal.html" formmethod="post">Money Withdrawal</button><br>
  </form>
</aside>
<aside class="right">
   <form style="width: 100%;" >
    <button style="background-color:red;" value="Customer Logout" id="lg" name="lg" formaction="Useractivity" formmethod="post" >Customer Logout</button>
  <button value="printstatement" id="ptst" name="ptst" formaction="ptst.html" formmethod="post" >PrintStatement</button><br>
  <button value="transfermoney" id="transfermoney" name="transfermoney"  formaction="transfermoney.html" formmethod="post">Transfer Money</button> <br>
  <button value="accountclosure" id="close" name="close" formaction="Useractivity" formmethod="post">Account Closure</button> <br>
  </form>
  
</aside>
<div class="centerforuser">
<span style="color:yellow; font-weight: bold; font-size: 20px;">${result}</span>
</div>
</body>
</html>