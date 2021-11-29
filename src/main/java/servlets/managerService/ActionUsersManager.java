package servlets.managerService;

import entity.Action;
import helper.HelperOnlyUser;
import service.JDBCServiceAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "actionUsersManager", value = "/actionUsersManager")
public class ActionUsersManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HelperOnlyUser helperOnlyUser = new HelperOnlyUser();
        JDBCServiceAction serviceAction = new JDBCServiceAction();

        List<Action> actionList = helperOnlyUser.getActionForUser(serviceAction.getAllActionUserForManager());

        request.setAttribute("action", actionList);
        getServletContext().getRequestDispatcher("/managerActionsUsers.jsp").forward(request,response);

    }
}
