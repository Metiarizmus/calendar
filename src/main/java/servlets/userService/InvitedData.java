package servlets.userService;

import entity.Action;
import entity.User;
import helper.BaseInServlets;
import service.JDBCServiceAction;
import service.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "invitedData", value = "/invitedData")
public class InvitedData extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("id_user");

        BaseInServlets baseInServlets = new BaseInServlets();

        List<Action> actionList = baseInServlets.getDataForInvite(email);

        request.setAttribute("invite", actionList);

        getServletContext().getRequestDispatcher("/InvitedUsers.jsp").forward(request,response);

    }
}
