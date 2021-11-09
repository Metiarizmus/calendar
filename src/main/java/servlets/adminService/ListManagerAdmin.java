package servlets.adminService;

import entity.Role;
import entity.User;
import serviceJDBC.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "listManagerAdmin", value = "/listManagerAdmin")
public class ListManagerAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCServiceUser serviceUser = new JDBCServiceUser();
        List<User> managerList = serviceUser.getAllUsers();

        for (int i = 0; i < managerList.size(); i++) {
            if((managerList.get(i).getRole().equals(Role.USER)) ){
                managerList.set(i,null);
            }else
            if ((managerList.get(i).getRole().equals(Role.ADMIN))){
                managerList.set(i,null);
            }
        }

        managerList.removeAll(Collections.singleton(null));
        request.setAttribute("manager", managerList);

        getServletContext().getRequestDispatcher("/listManagerForAdmin.jsp").forward(request,response);
    }
}
