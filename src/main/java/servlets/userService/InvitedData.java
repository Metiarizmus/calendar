package servlets.userService;

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

@WebServlet(name = "invitedData", value = "/invitedData")
public class InvitedData extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("id_user");

        List<Action> actionList = new JDBCServiceAction().getAllActionInvite(email);
        List<Integer> idInvitedUsers = new ArrayList<>();

        for (Action q : actionList) {
            idInvitedUsers.add(q.getIdInviteUsers());
        }

        for (int i = 0; i < idInvitedUsers.size(); i++) {
            User user = new JDBCServiceUser().getUserById(idInvitedUsers.get(i));

            actionList.get(i).setInvitedUsers(user);
        }

        request.setAttribute("invite", actionList);

        getServletContext().getRequestDispatcher("/InvitedUsers.jsp").forward(request,response);

    }
}
