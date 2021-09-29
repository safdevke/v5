package action;

import dao.CartDao;
import dao.ProductDao;
import model.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AddToCart", value = "/addtocart")
public class AddToCart extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = session.getAttribute("email").toString();
        String productId = req.getParameter("id");

        int quantity = 1;
        double product_price = 0;
        int product_total = 0;
        int cart_total = 0;

        Cart cart = new Cart();
        cart.setEmail(email);
        cart.setProductId(Integer.parseInt(productId));
        cart.setQuantity(quantity);
        cart.setTotal(cart_total);
        cart.setPrice(product_price);

        int flag = 0;
        try {

            ResultSet rs = new ProductDao().listById(productId);
            while (rs.next()) {
                product_price = rs.getDouble(4);
                product_total = (int) product_price;
            }
            rs.close();

            rs = new CartDao().checkProductInCart(productId,email);
            while (rs.next()) {
                cart_total = rs.getInt(5);
                cart_total = cart_total+product_total;
                quantity = rs.getInt(3);
                quantity += 1;
                flag = 1;
            }

            if (flag == 1) {
                cart.setQuantity(quantity);
                cart.setTotal(cart_total);
                cart.setPrice(product_price);
                new CartDao().updateProductInCart(cart);
                resp.sendRedirect("home.jsp?msg=exist");
            }
            if (flag == 0) {
                cart.setQuantity(quantity);
                cart.setTotal(cart_total);
                cart.setPrice(product_price);
                new CartDao().addToCart(cart);
                resp.sendRedirect("home.jsp?msg=added");
            }
        } catch (SQLException e) {
            resp.sendRedirect("home.jsp?msg=invalid");
        }
    }
}
