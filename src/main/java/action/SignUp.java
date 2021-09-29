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

@WebServlet(name = "SignUp", value = "/signup")
public class SignUp extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("signup.jsp");
        requestDispatcher.forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();

        try {
            BeanUtils.populate(user,req.getParameterMap());
            new UserDao().addUser(user);
        } catch (Exception e) {
            resp.sendRedirect("signup.jsp?msg=invalid");
        }
        resp.sendRedirect("signup.jsp?msg=valid");
    }
}
