<%@include file = "header.jsp" %>
<%@include file = "footer.jsp" %>
<%@ page import="dao.ProductDao" %>
<%@ page import="dao.CartDao" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Cart</title>
<style>
h3
{
	color: yellow;
	text-align: center;
}
</style>
</head>
<body>
<div style="color: white; text-align: center; font-size: 30px;">My Cart <i class='fas fa-cart-arrow-down'></i></div>

<%
  String message = "";
  if (request.getParameter("msg") != null) {
    message = request.getParameter("msg");
  }
  if (message.equals("notPossible")) {
%>
<h3 class="alert">There is only one Quantity! So click on remove!</h3>
<% } %>
<%
if (message.equals("inc")) {
%>
<h3 class="alert">Quantity  Increased Successfully!</h3>
<% } %>
<%
if (message.equals("dec")) {
%>
<h3 class="alert">Quantity  Decreased Successfully!</h3>
<% } %>
<%
if (message.equals("removed")) {
%>
<h3 class="alert">Product Successfully Removed!</h3>
<% } %>
<table>
<thead>
<%
int total = 0;
int sno = 0;
try {
ResultSet rs = new CartDao().cartTotal(email);
while(rs.next()) {
total = rs.getInt(1);
}
rs.close();
%>
          <tr>
            <th scope="col" style="background-color: yellow;">Total: <%out.println(total);%> <i class="fa fa-inr"></i> </th>
            <%if (total > -1) { %><th scope="col"><a href="addressPaymentForOrder.jsp">Proceed to order</a></th><% } %>
          </tr>
        </thead>
        <thead>
          <tr>
          <th scope="col">S.No</th>
            <th scope="col">Product Name</th>
            <th scope="col">Category</th>
            <th scope="col"><i class="fa fa-inr"></i> price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Sub Total</th>
            <th scope="col">Remove <i class='fas fa-trash-alt'></i></th>
          </tr>
        </thead>
        <tbody>
        <%
        rs = new CartDao().innerJoin(email);
        while (rs.next()) {
        %>
          <tr>
           <td><%out.println(sno+=1);%></td>
            <td><%=rs.getString(2) %></td>
            <td><%= rs.getString(3) %></td>
            <td><i class="fa fa-inr"></i> <%= rs.getString(4) %> </td>
            <td><a href="incDecQuantityAction.jsp?id=<%= rs.getString(1)%>&quantity=inc"><i class='fas fa-plus-circle'></i></a> <%= rs.getString(8) %> <a href="incDecQuantityAction.jsp?id=<%= rs.getString(1)%>&quantity=dec"><i class='fas fa-minus-circle'></i></a></td>
            <td><i class="fa fa-inr"></i> <%= rs.getString(10) %> </td>
            <td><a href="removeFromCart.jsp?id=<%= rs.getString(1)%>">Remove <i class='fas fa-trash-alt'></i></a></td>
          </tr>
        <%
        }
        } catch (Exception e) {}
        %>
        </tbody>
      </table>
      <br>
      <br>
      <br>
</body>
</html>