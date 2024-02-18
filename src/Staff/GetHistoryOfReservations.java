package Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetHistoryOfReservations", urlPatterns = "/Staff/GetHistoryOfReservations")
public class GetHistoryOfReservations extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Staff staff = new Staff();
        String res = staff.view_history(1, request.getParameter("from"), request.getParameter("to"));
        HttpSession session = request.getSession(true);
        session.setAttribute("HistoryReservation",res);
        response.sendRedirect("showHistory.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
