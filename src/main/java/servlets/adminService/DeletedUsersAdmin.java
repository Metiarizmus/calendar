package servlets.adminService;

import enums.State;
import helper.BaseInServlets;
import service.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deletedUsersAdmin", value = "/deletedUsersAdmin")
public class DeletedUsersAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        JDBCServiceUser serviceUser = new JDBCServiceUser();
        response.setContentType("application/json;charset=UTF-8");

        BaseInServlets baseInServlets = new BaseInServlets();


        StringBuilder sb = null;
        try {
            sb = baseInServlets.getRequest(request.getReader());
        } catch (IOException e) {

        }

        String[] s = sb.toString().split("=");

        int id = Integer.parseInt(s[1]);

        if (State.DELETED.name().equals(s[0])) {

            System.out.println("deleted");
            serviceUser.deletedUsers(id);

        } else if (State.SUSPEND.name().equals(s[0])) {

            System.out.println("suspend");
            serviceUser.suspendUser(id);

        } else if (State.UNSUSPEND.name().equals(s[0])) {

            System.out.println("unSuspend");
            serviceUser.anSuspendUser(id);

        }

    }
}
