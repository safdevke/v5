package action;

import dao.UserDao;
import model.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(name = "ForgotPassword", value = "/forgotpass")
public class ForgotPassword extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("forgotPassword.jsp");
        requestDispatcher.forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();

        try {
            BeanUtils.populate(user, req.getParameterMap());
            if (new UserDao().updateUserCredentials(user)) {
                resp.sendRedirect("forgotPassword.jsp?msg=done");
            } else {
                resp.sendRedirect("forgotPassword.jsp?msg=invalid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage( ));
        }
    }
}
