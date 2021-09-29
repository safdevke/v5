<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.CartDao" %>
<%@ page import="model.Cart" %>

<%
String id = request.getParameter("id");
String email = request.getParameter("email");
String status = "Cancel";

Cart cart = new Cart();
cart.setProductId(Integer.parseInt(id));
cart.setEmail(email);
cart.setStatus(status);
try {
new CartDao().updateCartStatus(cart);
response.sendRedirect("ordersReceive.jsp?msg=cancel");
} catch(Exception e) {
response.sendRedirect("ordersReceive.jsp?msg=wrong");
}
%>