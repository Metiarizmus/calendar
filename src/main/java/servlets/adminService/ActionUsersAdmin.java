package servlets.adminService;

import entity.Action;
import serviceJDBC.JDBCServiceAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "actionUsersAdmin", value = "/actionUsersAdmin")
public class ActionUsersAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceAction serviceAction = new JDBCServiceAction();

//        HttpSession session = request.getSession();
//        String email = (String) session.getAttribute("id_user");

        List<Action> actionList = serviceAction.getAllActionUserForManager();


        request.setAttribute("action", actionList);

        getServletContext().getRequestDispatcher("/actionUsersForAdmin.jsp").forward(request, response);


    }
}
