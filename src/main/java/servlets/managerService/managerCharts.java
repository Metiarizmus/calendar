package servlets.managerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "managerCharts", value = "/managerCharts")
public class managerCharts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDate todaydate = LocalDate.now();
        String date = String.valueOf(todaydate.withDayOfMonth(1));


        request.setAttribute("countInSystem",10);
        request.setAttribute("countEventMonth",123);
        request.setAttribute("countAcceptedEventMonth",54);

        getServletContext().getRequestDispatcher("/managerCharts.jsp").forward(request, response);

    }
}
