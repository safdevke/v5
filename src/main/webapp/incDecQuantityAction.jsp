<%@ page import="java.sql.*"%>
<%@ page import="dao.ProductDao" %>
<%@ page import="dao.CartDao" %>
<%@ page import="model.Cart" %>
<%@ page import="java.util.*" %>
<%
String email = session.getAttribute("email").toString();
String id = request.getParameter("id");
String incdec = request.getParameter("quantity");
double price = 0;
int total = 0;
int quantity = 0;
int final_total = 0;
try {
ResultSet rs = new CartDao().checkProductInCart(id, email);
while (rs.next()) {
price = rs.getDouble(4);
total = rs.getInt(5);
quantity = rs.getInt(3);
}
rs.close();
Cart cart = new Cart();
if (quantity == 1 && incdec.equals("dec")) {
response.sendRedirect("myCart.jsp?msg=notPossible");
} else if (quantity != 1 && incdec.equals("dec")) {
total = (int)(total - price);
quantity-= 1;
cart.setTotal(total);
cart.setQuantity(quantity);
cart.setEmail(email);
cart.setProductId(Integer.parseInt(id));
new CartDao().updateProductInCart(cart);
response.sendRedirect("myCart.jsp?msg=dec");
} else {
cart.setTotal((int)(total + price));
cart.setQuantity(quantity+=1);
new CartDao().updateProductInCart(cart);
response.sendRedirect("myCart.jsp?msg=inc");
}
} catch (Exception e) {}
%>