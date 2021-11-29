package servlets;

import enums.Role;
import entity.User;
import exceptions.NullDataUser;
import helper.EmailValidator;
import helper.SessionCounter;
import org.apache.log4j.Logger;
import service.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "registration", value = "/registration")
public class Registration extends HttpServlet {

    private static final Logger log = Logger.getLogger(Registration.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmailValidator validator = new EmailValidator();
        JDBCServiceUser serviceUser = new JDBCServiceUser();

        String addUserFromAdmin = request.getParameter("parametr");
        System.out.println("addUserFromAdmin = " + addUserFromAdmin);

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (firstName != "" && lastName != "" && password != "") {

            if (validator.validate(email)) {

                if (serviceUser.getUserByEmail(email) != 0) {
                    response.setContentType("text/xml");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("this email yet exist in the system");

                } else {
                    User user = new User(firstName, lastName, email, password);
                    log.info("add user to db");

                    serviceUser.addUser(user, Role.USER);

                    if (addUserFromAdmin == null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("id_user", email);
                        SessionCounter counter = (SessionCounter) session.getAttribute(SessionCounter.COUNTER);
                        response.sendRedirect("userMode");
                    } else {
                        log.info("add user from admin mode");
                    }
                }


            }
        } else throw new NullDataUser("something null");


    }
}
