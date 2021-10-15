package servlets;

import entity.Role;
import org.apache.log4j.Logger;
import serviceJDBC.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LogIn extends HttpServlet {

    private static final Logger log = Logger.getLogger(LogIn.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceUser serviceUser = new JDBCServiceUser();

        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if(serviceUser.getByEmailAndPassword(email,password).equals(Role.USER)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("id_user", email);
            log.info("create session with user by email = " + email);
            response.sendRedirect("userMode");
        }


    }
}
