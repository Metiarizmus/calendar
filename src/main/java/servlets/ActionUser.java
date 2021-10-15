package servlets;

import DBConnection.DBConnection;
import entity.Action;
import entity.TypeAction;
import entity.User;
import helperData.HelperGetFullDate;
import org.apache.log4j.Logger;
import serviceJDBC.JDBCServiceAction;
import serviceJDBC.JDBCServiceUser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@WebServlet(name = "actionUser", value = "/actionUser")
public class ActionUser extends HttpServlet {

    private static final Logger log = Logger.getLogger(ActionUser.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCServiceAction serviceAction = new JDBCServiceAction();
        JDBCServiceUser serviceUser = new JDBCServiceUser();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("id_user");

        int idUser;
        int idToUser = -1;

        String title = request.getParameter("title");
        String time = request.getParameter("time");
        String dateWithoutTime = request.getParameter("var1");
        String descr = request.getParameter("description");
        String addGuest = request.getParameter("add_guest");


        HelperGetFullDate h = new HelperGetFullDate();
        Date date = h.getFullData(time,dateWithoutTime);

        Action action = new Action();
        action.setTitle(title);
        action.setDescription(descr);
        action.setDate(date);
        idUser = serviceUser.getUserByEmail(email);
        User user = new User();
        user.setId(idUser);
        action.setUser(user);

        if(addGuest!=null) {
            action.setTypeAction(TypeAction.EVENT);
            idToUser = serviceUser.getUserByEmail(addGuest);
        }else
        if (descr != null) {
            action.setTypeAction(TypeAction.TASK);
        }else {
            action.setTypeAction(TypeAction.REMINDER);
        }

        serviceAction.addAction(action, idToUser);
        log.info("add action in db");

    }
}
