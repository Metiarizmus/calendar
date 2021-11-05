package servlets.adminService;

import entity.Action;
import entity.User;
import serviceJDBC.JDBCServiceAction;
import serviceJDBC.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "deletedUsersAdmin", value = "/deletedUsersAdmin")
public class DeletedUsersAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceUser serviceUser = new JDBCServiceUser();
        response.setContentType("application/json;charset=UTF-8");

        StringBuffer sb = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }


        String[] s = sb.toString().split("=");
        int id = Integer.parseInt(s[1]);

       // log.info("get id which we get when click on accepted, id= " + id_invited);

        if (s[0].equals("id_users_deleted")){

            System.out.println("deleted");
            serviceUser.deletedUsers(id);

            response.getWriter();
        }else if(s[0].equals("id_users_suspend")){
            System.out.println("suspend");
            serviceUser.addSuspend(id);

        }
    }
}
