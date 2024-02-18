package Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetReservations",urlPatterns = "/Staff/GetReservations")
public class GetReservations extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("slot_id"));
        Staff staff = new Staff();
        String res = staff.view_reservations(id);
        HttpSession session = request.getSession(true);
        session.setAttribute("ReservationTable",res);
        response.sendRedirect("reservations.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
