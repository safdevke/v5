<%@include file = "footer.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="dao.CartDao" %>
<%@ page import="dao.UserDao" %>
<html>
<head>
<link rel="stylesheet" href="css/bill.css">
<title>Bill</title>
</head>
<body>
<%
String email = session.getAttribute("email").toString();
try {
int total = 0;
int sno = 0;
ResultSet rs = new CartDao().cartBillTotal(email);
while(rs.next()) {
total = rs.getInt(1);
}
rs.close();
rs = new UserDao().innerJoin(email);
while(rs.next()) {
%>
<h3>Online shopping Bill</h3>
<hr>
<div class="left-div"><h3>Name:  <%= rs.getString(1)%></h3></div>
<div class="right-div-right"><h3>Email:  <% out.println(email);%></h3></div>
<div class="right-div"><h3>Mobile Number: <%= rs.getString(20)%> </h3></div>

<div class="left-div"><h3>Order Date: <%=rs.getString(21)%></h3></div>
<div class="right-div-right"><h3>Payment Method:  <%= rs.getString(23)%></h3></div>
<div class="right-div"><h3>Expected Delivery:  <%= rs.getString(22)%></h3></div>

<div class="left-div"><h3>Transaction Id:  <%= rs.getString(24)%></h3></div>
<div class="right-div-right"><h3>City: <%= rs.getString(17)%> </h3></div>
<div class="right-div"><h3>Address:  <%= rs.getString(16)%></h3></div>

<div class="left-div"><h3>State: <%= rs.getString(18)%></h3></div>
<div class="right-div-right"><h3>Country: <%= rs.getString(19)%> </h3></div>

<hr>
<%
break;
}
rs.close();
%>

	
	<br>
	
<table id="customers">
<h3>Product Details</h3>
  <tr>
    <th>S.No</th>
    <th>Product Name</th>
    <th>category</th>
    <th>Price</th>
    <th>Quantity</th>
     <th>Sub Total</th>
  </tr>
  <%
  rs = new CartDao().innerJoinOnProduct(email);
  while(rs.next()) {
    sno += 1;
  %>
  <tr>
    <td><% out.println(sno); %></td>
    <td><%= rs.getString(17) %></td>
    <td><%= rs.getString(18) %></td>
    <td><%= rs.getString(19) %></td>
    <td><%= rs.getString(3) %></td>
     <td><%= rs.getString(5) %></td>
  </tr>
  <tr>
<% } %>
</table>
<h3>Total: <% out.println(sno); %></h3>
<a href="continueShopping.jsp"><button class="button left-button">Continue Shopping</button></a>
<a onclick="window.print();"><button class="button right-button">Print</button></a>
<br><br><br><br>
<%
}
catch(Exception e) {}
%>
</body>
</html>