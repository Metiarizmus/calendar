package servlets.adminService;

import entity.Role;
import entity.User;
import serviceJDBC.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "listUsersAdmin", value = "/listUsersAdmin")
public class ListUsersAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JDBCServiceUser serviceUser = new JDBCServiceUser();
        List<User> userList = serviceUser.getAllUsers();

        for (int i = 0; i < userList.size(); i++) {
            if((userList.get(i).getRole().equals(Role.MANAGER)) ){
                userList.set(i,null);
            }else
            if ((userList.get(i).getRole().equals(Role.ADMIN))){
                userList.set(i,null);
            }
        }


        userList.removeAll(Collections.singleton(null));

        request.setAttribute("users", userList);

        getServletContext().getRequestDispatcher("/listUsersForAdmin.jsp").forward(request,response);

    }
}
