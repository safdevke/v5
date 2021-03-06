<%@include file = "adminHeader.jsp" %>
<%@include file = "../footer.jsp" %>
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
<div style="color: white; text-align: center; font-size: 30px;">All Products & Edit Products <i class='fab fa-elementor'></i></div>
<%
  String message = "";
  if (request.getParameter("msg") != null) {
    message = request.getParameter("msg");
  }
  if (message.equals("done")) {
  %>
<h3 class="alert">Product Successfully Updated!</h3>
<% } %>
<%
   if (message.equals("wrong")) {
   %>
<h3 class="alert">Some thing went wrong! Try again!</h3>
<% } %>
<table>
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i> Price</th>
            <th>Status</th>
            <th scope="col">Edit <i class='fas fa-pen-fancy'></i></th>
          </tr>
        </thead>
        <tbody>
        <% ProductDao productDao = new ProductDao();
           List<Product> products = new ProductDao().listAllProducts();
           for(Product product:products) {
        %>
          <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getCategory() %></td>
            <td><i class="fa fa-money"></i> <%= product.getPrice() %> </i></td>
            <td><%= product.getStatus() %></td>
            <td><a href="editProduct.jsp?id=<%=product.getId()%>">Edit <i class='fas fa-pen-fancy'></i></a></td>
          </tr>
        <% } %>
        </tbody>
      </table>
      <br>
      <br>
      <br>

</body>
</html>