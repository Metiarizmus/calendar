package servlets;

import entity.Role;
import entity.User;
import helperData.EmailValidator;
import helperData.OnlineUsersCounter;
import org.apache.log4j.Logger;
import serviceJDBC.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (firstName != "" && lastName != "" && password != ""){
            if (validator.validate(email)) {

                User user = new User(firstName,lastName,email,password);
                log.info("add user to db");
                HttpSession session = request.getSession(true);
                session.setAttribute("id_user", email);
                serviceUser.addUser(user, Role.USER);
                System.out.println("online reg: " + OnlineUsersCounter.getNumberOfUsersOnline());
                response.sendRedirect("userMode");

            }
        }


    }
}
