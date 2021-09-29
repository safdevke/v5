package action;

import dao.ProductDao;
import model.Product;
import org.apache.commons.beanutils.BeanUtils;
import util.Dbutil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddProduct" , urlPatterns = "/addproduct")
public class AddNewProduct extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Product product = new Product();

        try {
            BeanUtils.populate(product,req.getParameterMap());
            new ProductDao().addProduct(product);
            resp.sendRedirect("admin/addNewProduct.jsp?msg=done");
        } catch (Exception e) {
            resp.sendRedirect("admin/addNewProduct.jsp?msg=error");
        }
    }
}
