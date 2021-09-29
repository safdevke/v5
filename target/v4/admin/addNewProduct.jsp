<%@include file = "adminHeader.jsp" %>
<%@include file = "../footer.jsp" %>
<%@page import="java.util.*" %>
<html>
<head>
<link rel="stylesheet" href="../css/addNewProduct-style.css">
<title>Add New Product</title>
</head>
<body>
<%
  String message = "";
  if (request.getParameter("msg") != null) {
    message = request.getParameter("msg");
  }
  if (message.equals("done")) {
  %>
<h3 class="alert">Product Added Successfully!</h3>
<% } %>
 <%
   if (message.equals("error")) {
   %>
<h3 class="alert">Some thing went wrong! Try Again!</h3>
<% } %>
<%
Random rand = new Random();
int n = rand.nextInt(1000000);
%>
<form action = "../addproduct" method = "post">
<h3 style="color: yellow;">Product ID: <%out.println(n);%></h3>

<input type="hidden" name="id" value="<%=n%>">
<div class="left-div">
 <h3>Enter Name</h3>
<input class= "input-style" input type="text" name="name" placeholder="Enter Name" required>
<hr>
</div>

<div class="right-div">
<h3>Enter Category</h3>
<input class= "input-style" input type="text" name="category" placeholder="Enter Category" required>
<hr>
</div>

<div class="left-div">
<h3>Enter Price</h3>
<input class= "input-style" input type="text" name="price" placeholder="Enter Price" required>
<hr>
</div>

<div class="right-div">
<h3>Active</h3>
<select class= "input-style" name="active">
<option value = "Yes"> Yes</option>
<option value = "No"> No</option>
</select>
<hr>
</div>
<button class = "button">Save <i class='far fa-arrow-alt-circle-right'></i></button>
</form>
</body>
<br><br><br>
</body>
</html>