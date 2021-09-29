<%@ page import="java.util.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="dao.CartDao" %>
<%@ page import="dao.UserDao" %>
<%
String email = session.getAttribute("email").toString();
String status = "processing";

try {
new CartDao().updateCartStatus(email,status);
response.sendRedirect("home.jsp");
} catch (Exception e) {}
%>