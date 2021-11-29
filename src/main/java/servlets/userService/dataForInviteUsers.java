package servlets.userService;

import com.google.gson.Gson;
import entity.Action;
import entity.User;
import helper.BaseInServlets;
import service.JDBCServiceAction;
import service.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InvitedEvent", value = "/InvitedEvent")
public class dataForInviteUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");


        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("id_user");

        BaseInServlets baseInServlets = new BaseInServlets();

        List<Action> actionList = baseInServlets.getDataForInvite(email);

        String json = new Gson().toJson(actionList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
