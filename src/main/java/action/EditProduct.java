package action;

import dao.CartDao;
import dao.ProductDao;
import model.Product;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProduct", urlPatterns = "/editproduct")
public class EditProduct extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Product product = new Product();

        try {
            BeanUtils.populate(product,req.getParameterMap());
            new ProductDao().updateProduct(product);

            if (product.getStatus().equals("No")) { // remove all inactive products from the cart obj
                new CartDao().removeFromCart(Integer.parseInt(product.getId()));
                resp.sendRedirect("admin/allProductEditProduct.jsp?msg=done");
            }
        } catch (Exception e) {
            resp.sendRedirect("admin/allProductEditProduct.jsp?msg=wrong");
        }
    }
}
