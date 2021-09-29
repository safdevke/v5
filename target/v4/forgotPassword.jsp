<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>ForgotPassword</title>
</head>
<body>
<div id='container'>
  <div class='signup'>
      <form action = "forgotpass" method = "post"/>
      <input = "email" name= "email" placeholder = "Enter Email" required>
      <input = "number" name= "mobileNumber" placeholder = "Enter Mobile Number" required>
      <select name = "securityQuestion" required>
      <option value = "In what city were you born?">In what city were you born?</option>
      <option value = "What is the name of your first school?">What is the name of your first school?</option>
      <option value = "What high school did you attend?">What high school did you attend?</option>
      </select>
      <input = "text" name= "answer" placeholder = "Enter Answer" required>
      <input = "password" name= "password" placeholder = "Enter New Password" required>
      <input type = "submit" value = "Save">
      </form>
      <h2><a href="login.jsp">Login</a></h2>
  </div>
  <div class='whyforgotPassword'>
<%
 String message = "";
  if (request.getParameter("msg") != null) {
    message = request.getParameter("msg");
  }
  if (message.equals("done")) {
%>
<h1>Password Changed Successfully!</h1>
<% } %>
<%
  if (request.getParameter("msg") != null) {
    message = request.getParameter("msg");
  }
  if (message.equals("invalid")) {
%>
<h1>Some thing Went Wrong! Try Again !</h1>
<% } %>
    <h2>Online Shopping</h2>
    <p>The Online Shopping System is the application that allows the users to shop online without going to the shops to buy them.</p>
  </div>
</div>
</body>
</html>