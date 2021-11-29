package servlets.userService;

import com.google.gson.Gson;
import entity.Action;
import enums.Role;
import exceptions.NotCorrectRole;
import service.JDBCServiceAction;
import service.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "dateForEvent", value = "/dateForEvent")
public class AllEvent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");


        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("id_user");


        List<Action> actionList = new JDBCServiceAction().getAllActionForUser(email);

        boolean flag = false;

        if (Role.USER.equals(new JDBCServiceUser().getRoleUsersByEmail(email)) ||
                Role.MANAGER.equals(new JDBCServiceUser().getRoleUsersByEmail(email)) ||
                Role.ADMIN.equals(new JDBCServiceUser().getRoleUsersByEmail(email))
        ) {
            flag = true;
        }




        if (flag){
            List<Integer> idToUsers = new ArrayList<>();

            for (Action action : actionList) {
                idToUsers.add(action.getIdInviteUsers());
            }


            for (int i = 0; i < idToUsers.size(); i++) {
                actionList.get(i).setInvitedUsers(new JDBCServiceUser().getUserById(idToUsers.get(i)));
            }


            String json = new Gson().toJson(actionList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        }throw new NotCorrectRole("incorrect role");


    }
}
