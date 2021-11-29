package servlets.adminService;

import enums.Role;
import entity.User;
import service.JDBCServiceUser;

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
        List<User> managerList = serviceUser.getUsersByRole(Role.MANAGER);

        request.setAttribute("manager", managerList);

        getServletContext().getRequestDispatcher("/listManagerForAdmin.jsp").forward(request,response);
    }
}
