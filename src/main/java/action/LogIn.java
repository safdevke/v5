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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(name = "LogIn" ,value = "/login")
public class LogIn extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = new User();

        try {
            BeanUtils.populate(user,req.getParameterMap());
            if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin")) {
                session.setAttribute("email", user.getEmail());
                resp.sendRedirect("admin/adminHome.jsp");
            }

            if (new UserDao().validateUserCredentials(user)) {
                session.setAttribute("email", user.getEmail());
                resp.sendRedirect("home.jsp");
            } else {
                resp.sendRedirect("login.jsp?msg=unknown");
            }
        } catch (Exception e) {
            resp.sendRedirect("login.jsp?msg=invalid");
        }
    }
}
