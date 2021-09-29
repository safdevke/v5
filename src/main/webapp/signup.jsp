<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<title>Signup</title>
</head>
<body>
<div id='container'>
  <div class='signup'>
    <form action = "signup" method = "post">
    <input type = "text" name = "name" placeholder= "Enter Name"  required>
    <input type = "email" name = "email" placeholder= "Enter Email"  required>
    <input type = "number" name = "mobileNumber" placeholder= "Enter Phone Number"  required>
    <select name = "securityQuestion" required>
    <option value = "In what city were you born?">In what city were you born?</option>
    <option value = "What is the name of your first school?">What is the name of your first school?</option>
    <option value = "What high school did you attend?">What high school did you attend?</option>
    </select>
    <input type = "text" name = "answer" placeholder= "Enter Answer" required>
    <input type = "password" name = "password" placeholder= "Enter Password" required>
    <input type = "submit" value = "sign up">
    </form>
      <h2><a href="login.jsp">Login</a></h2>
  </div>
  <div class='whysign'>
<%
String message = "";
if (request.getParameter("msg") != null) {
    message = request.getParameter("msg");
}
if (message.equals("valid")) {
%>
<h1>Successfully Registered</h1>
<% } %>
<%
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