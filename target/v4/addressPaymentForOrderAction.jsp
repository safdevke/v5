<%@ page import="java.util.*" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.sql.*"%>
<%@ page import="model.Cart" %>
<%@ page import="dao.CartDao" %>

<%
String email = session.getAttribute("email").toString();
String address = request.getParameter("address");
String city = request.getParameter("city");
String state = request.getParameter("state");
String country = request.getParameter("country");
String mobileNumber = request.getParameter("mobileNumber");
String paymentMethod = request.getParameter("paymentMethod");
String transactionId = "";
transactionId = request.getParameter("transactionId");
String status = "bill";

Cart cart = new Cart();
cart.setEmail(email);
cart.setAddress(address);
cart.setCity(city);
cart.setCountry(country);
cart.setState(state);
cart.setMobileNumber(new BigInteger(mobileNumber));
cart.setPaymentMethod(paymentMethod);
cart.setTransactionId(transactionId);
cart.setStatus(status);

try {

new CartDao().updateCartDetails(cart);
new CartDao().updateCartPayment(cart);
response.sendRedirect("bill.jsp");

} catch(Exception e) {}

%>