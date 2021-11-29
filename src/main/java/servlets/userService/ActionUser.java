package servlets.userService;


import com.google.gson.Gson;
import db.PropertyInf;
import entity.Action;
import enums.TypeAction;
import entity.User;
import enums.StateProperties;
import helper.HelperGetFullDate;
import org.apache.log4j.Logger;
import service.JDBCServiceAction;
import service.JDBCServiceUser;
import tls.Sender;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "actionUser", value = "/actionUser")
public class ActionUser extends HttpServlet {

    private static final Logger log = Logger.getLogger(ActionUser.class);
    private JDBCServiceAction serviceAction = new JDBCServiceAction();
    private JDBCServiceUser serviceUser = new JDBCServiceUser();
    private PropertyInf propertyInf = new PropertyInf();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("id_user");


        int idUser;
        int idToUser = -1;

        String title = request.getParameter("title");
        String time = request.getParameter("time");
        String dateWithoutTime = request.getParameter("dateEvent");
        String descr = request.getParameter("description");
        String addGuest = request.getParameter("add_guest");


        HelperGetFullDate h = new HelperGetFullDate();
        Date date = h.getFullData(time, dateWithoutTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);

        Action action = new Action();
        action.setTitle(title);
        action.setDate(dateFormat.format(date));
        idUser = serviceUser.getUserByEmail(email);
        User user = new User();
        user.setId(idUser);

        action.setUser(user);

        int l = 0;
        if (addGuest != null) {

            List<User> userEmailList = new JDBCServiceUser().getAllUsers();

            int k = 0;
            for (int i = 0; i < userEmailList.size(); i++) {
                if (userEmailList.get(i).getEmail().equals(addGuest)) {
                    k = 1;
                    break;
                }
            }

            if (k == 1) {
                action.setTypeAction(TypeAction.EVENT);
                idToUser = serviceUser.getUserByEmail(addGuest);
                System.out.println("event add in the table");
                serviceAction.addAction(action, idToUser);
                l = 1;
            } else {
                String myEmail = new PropertyInf().getDataFromProperties(StateProperties.EMAIL).getProperty("MY_EMAIL");
                Sender sender = new Sender();

                User emailFrom = serviceUser.getUserById(action.getUser().getId());

                action.setUser(emailFrom);

                Gson gson = new Gson();
                String jsonEvent = gson.toJson(action);
                String helloInvite = propertyInf.getDataFromProperties(StateProperties.SOME_TEXT).getProperty("invite.hello");

                sender.send(helloInvite, jsonEvent, myEmail, addGuest);
                log.info("send event to email");
                System.out.println("send event to email");
                l = 0;

            }

        } else if (descr != null) {
            action.setDescription(descr);
            action.setTypeAction(TypeAction.TASK);
        } else {
            action.setTypeAction(TypeAction.REMINDER);
        }

        if (l == 0) {
            serviceAction.addAction(action, idToUser);
            System.out.println("add action in db class = ActionUser");
            log.info("add action in db");
        }

        System.out.println("l = " + l);


        if (l == 0) {//fake response
            String json = new Gson().toJson("string:1");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }

    }
}
