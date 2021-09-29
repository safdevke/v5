<%@include file = "header.jsp" %>
<%@include file = "footer.jsp" %>
<%@ page import="dao.ProductDao" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>
h3
{
	color: yellow;
	text-align: center;
}
</style>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">Home <i class="fa fa-institution"></i></div>
<%
  String message = "";
  if (request.getParameter("msg") != null) {
    message = request.getParameter("msg");
  }
  if (message.equals("added")) {
%>
<h3 class="alert">Product added successfully!</h3>
<% } %>
<%
   if (message.equals("exist")) {
%>
<h3 class="alert">Product already exist in you cart! Quantity  increased!</h3>
<% } %>
<%
   if (message.equals("invalid")) {
%>
<h3 class="alert">Something went wrong. Try Again!</h3>
<% } %>
<table>
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-btc"></i> Price</th>
            <th scope="col">Add to cart <i class='fas fa-cart-plus'></i></th>
          </tr>
        </thead>
        <tbody>
        <% ProductDao productDao = new ProductDao();
           for(Product product:new ProductDao().fetchAllActiveProducts()) {
        %>
          <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getCategory() %></td>
            <td><i class="fa fa-inr"></i> <%= product.getPrice() %></i></td>
            <td><a href="addtocart?id=<%= product.getId() %>">Add to cart <i class='fas fa-cart-plus'></i></a></td>
          </tr>
         <% } %>
        </tbody>
      </table>
      <br>
      <br>
      <br>

</body>
</html>