package servlets;

import exceptions.NotEmailInSystem;
import enums.Role;
import helper.SessionCounter;
import org.apache.log4j.Logger;
import service.JDBCServiceUser;

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

        HttpSession session;
        if (serviceUser.getByEmailAndPassword(email, password) == null) {
            throw new NotEmailInSystem("there is no such email in the system or your password not correct");
        } else {
            session = request.getSession(true);
            session.setAttribute("id_user", email);
            Cookie cookie = new Cookie("user_email", email);
            response.addCookie(cookie);
        }
        if (Role.MANAGER.equals(serviceUser.getByEmailAndPassword(email, password))) {

            log.info("create session with manager by email = " + email);

            response.sendRedirect("managerMode");
        } else if (serviceUser.getByEmailAndPassword(email, password).equals(Role.USER)) {

            log.info("create session with user by email = " + email);

            SessionCounter counter = (SessionCounter) session.getAttribute(SessionCounter.COUNTER);

            response.sendRedirect("userMode");
        } else if (serviceUser.getByEmailAndPassword(email, password).equals(Role.ADMIN)) {

            log.info("create session with admin by email = " + email);
            response.sendRedirect("adminMode");
        }


    }
}
