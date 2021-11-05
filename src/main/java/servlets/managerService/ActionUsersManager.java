package servlets.managerService;

import entity.Action;
import entity.User;
import serviceJDBC.JDBCServiceAction;
import serviceJDBC.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "actionUsersManager", value = "/actionUsersManager")
public class ActionUsersManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceAction serviceAction = new JDBCServiceAction();

//        HttpSession session = request.getSession();
//        String email = (String) session.getAttribute("id_user");

        List<Action> userList = serviceAction.getAllActionUserForManager();

        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUser().getRole().equals("MANAGER") || userList.get(i).getUser().getRole().equals("ADMIN")){
                userList.remove(i);
            }
        }

        request.setAttribute("action", userList);

        getServletContext().getRequestDispatcher("/managerActionsUsers.jsp").forward(request,response);

    }
}
