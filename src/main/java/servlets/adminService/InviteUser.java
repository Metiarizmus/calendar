package servlets.adminService;

import DBConnection.PropertyInf;
import com.google.gson.Gson;
import entity.User;
import tls.Sender;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "inviteUser", value = "/inviteUser")
public class InviteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addressInvite = request.getParameter("address");
        System.out.println(addressInvite);

        String myEmail = new PropertyInf().getDataForEmail().getProperty("MY_EMAIL");
        Sender sender = new Sender();

        String invite = "The admin invited you to the applications!!!";
        sender.send("Вас приглосили на мероприятие в приложении Nikolai Calendar: ", invite, myEmail, addressInvite);

        response.sendRedirect("listUsersForAdmin.jsp");
    }
}
