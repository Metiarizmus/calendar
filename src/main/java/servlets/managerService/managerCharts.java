package servlets.managerService;

import entity.Action;
import helperData.HelperGetFullDate;
import helperData.HelperOnlyUser;
import helperData.SessionCounter;
import serviceJDBC.JDBCServiceAction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "managerCharts", value = "/managerCharts")
public class managerCharts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JDBCServiceAction serviceAction = new JDBCServiceAction();
        HelperGetFullDate helperGetFullDate = new HelperGetFullDate();
        String[] date = helperGetFullDate.getNowDateAndPlusMonth();

        List<Action> actionListUser = serviceAction.actionBetweenDate(date[0],date[1]);

        HelperOnlyUser helperOnlyUser = new HelperOnlyUser();

        List<Action> list = helperOnlyUser.getActionForUser(actionListUser);

        int countEventMonth = serviceAction.countAcceptedEventBetweenDate(date[0], date[1]);

        HttpSession session = request.getSession();
        session.getAttribute("id_user");

        SessionCounter counter = (SessionCounter) session.getAttribute(SessionCounter.COUNTER);

        request.setAttribute("countInSystem",counter.getActiveSessionNumber());
        request.setAttribute("countEventMonth",list.size());
        request.setAttribute("countAcceptedEventMonth",countEventMonth);

        getServletContext().getRequestDispatcher("/managerCharts.jsp").forward(request, response);

    }
}
