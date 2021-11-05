package servlets.managerService;

import entity.Action;
import entity.User;
import org.apache.log4j.Logger;
import serviceJDBC.JDBCServiceAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
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

        StringBuffer sb = new StringBuffer();
        String line = null;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JDBCServiceAction jdbcServiceAction = new JDBCServiceAction();

        String[] s = sb.toString().split("=");
        int id_invited = Integer.parseInt(s[1]);

        log.info("get id which we get when click on accepted, id= " + id_invited);

        if (s[0].equals("id_invited_accepted")){
            int[] k = jdbcServiceAction.getDataFromInvited(id_invited);

            Action action = jdbcServiceAction.getActionById(k[0]);
            User user = new User();
            user.setId(k[1]);
            action.setUser(user);

            jdbcServiceAction.addAction(action, -1);

            System.out.println("add action from invited accepted");
            response.getWriter();
        }else if(s[0].equals("id_invited_reject")){
            jdbcServiceAction.deletedInvite(id_invited);
        }


    }
}
