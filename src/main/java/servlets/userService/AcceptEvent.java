package servlets.userService;

import entity.Action;
import entity.User;
import enums.State;
import helper.BaseInServlets;
import org.apache.log4j.Logger;
import service.JDBCServiceAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "acceptEvent", value = "/acceptEvent")
public class AcceptEvent extends HttpServlet {
    private static final Logger log = Logger.getLogger(AcceptEvent.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        JDBCServiceAction jdbcServiceAction = new JDBCServiceAction();

        BaseInServlets baseInServlets = new BaseInServlets();
        StringBuilder sb = null;
        try {
            sb = baseInServlets.getRequest(request.getReader());
        } catch (IOException e) {

        }

        String[] s = sb.toString().split("=");
        int id_invited = Integer.parseInt(s[1]);

        log.info("get id which we get when click on accepted, id= " + id_invited);

        if (State.ACCEPTED.name().equals(s[0])) {
            int[] k = jdbcServiceAction.getDataFromInvited(id_invited);

            Action action = jdbcServiceAction.getActionById(k[0]);
            User user = new User();
            user.setId(k[1]);
            action.setUser(user);

            int idActionInvite = jdbcServiceAction.addAction(action, -1);
            System.out.println("accepted idAction invite = " + idActionInvite);

            jdbcServiceAction.changeAcceptedEvent(idActionInvite);

            jdbcServiceAction.deletedInvite(id_invited);
            System.out.println("add action from invited accepted");
            response.getWriter();
        } else if (State.REJECT.name().equals(s[0])) {
            jdbcServiceAction.deletedInvite(id_invited);
        }


    }
}
