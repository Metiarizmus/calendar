package servlets.adminService;

import entity.Action;
import entity.Role;
import helperData.HelperOnlyUser;
import serviceJDBC.JDBCServiceAction;
import serviceJDBC.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "actionUsersAdmin", value = "/actionUsersAdmin")
public class ActionUsersAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HelperOnlyUser helperOnlyUser = new HelperOnlyUser();
        JDBCServiceAction serviceAction = new JDBCServiceAction();

        List<Action> actionList = helperOnlyUser.getActionForUser(serviceAction.getAllActionUserForManager());

        request.setAttribute("action", actionList);

        getServletContext().getRequestDispatcher("/actionUsersForAdmin.jsp").forward(request, response);


    }
}
