<%@ page import="java.sql.*"%>
<%@ page import="dao.ProductDao" %>
<%@ page import="dao.CartDao" %>
<%@ page import="model.Cart" %>
<%@ page import="java.util.*" %>
<%
String email = session.getAttribute("email").toString();
String product_id = request.getParameter("id");
try {
new CartDao().removeFromCart(Integer.parseInt(product_id), email);
response.sendRedirect("myCart.jsp?msg=removed");
} catch (Exception e) {}
%>