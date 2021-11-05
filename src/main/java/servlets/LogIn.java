package servlets;

import entity.Role;
import helperData.OnlineUsersCounter;
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

        if(Role.MANAGER.equals(serviceUser.getByEmailAndPassword(email,password))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("id_user", email);
            Cookie cookie = new Cookie("user_email", email);
            response.addCookie(cookie);
            log.info("create session with manager by email = " + email);

            response.sendRedirect("managerMode");
        }else
        if(serviceUser.getByEmailAndPassword(email,password).equals(Role.USER)) {
            HttpSession session = request.getSession(true);

            session.setAttribute("id_user", email);

            //System.out.println("online login: " + OnlineUsersCounter.getNumberOfUsersOnline());
            Cookie cookie = new Cookie("user_email", email);
            response.addCookie(cookie);
            log.info("create session with user by email = " + email);
            response.sendRedirect("userMode");
        }else
        if(serviceUser.getByEmailAndPassword(email,password).equals(Role.ADMIN)) {
            HttpSession session = request.getSession(true);

            session.setAttribute("id_user", email);

            //System.out.println("online login: " + OnlineUsersCounter.getNumberOfUsersOnline());
            Cookie cookie = new Cookie("user_email", email);
            response.addCookie(cookie);
            log.info("create session with admin by email = " + email);
            response.sendRedirect("adminMode");
        }





    }
}
