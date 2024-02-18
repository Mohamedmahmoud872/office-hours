package Staff;

import base.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddSlot", urlPatterns = "/Staff/AddSlot")
public class AddSlot extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String day = request.getParameter("day");
        String time = request.getParameter("time");
        String type = request.getParameter("type");
        String location = request.getParameter("location");
        Staff staff = new Staff();
        staff.add_slot(day, time, type, location);
        DB database = new DB();
        HttpSession session = request.getSession(true);
        database.newNotification("You Added New Slot to Your Office Hours", (Integer) session.getAttribute("userID"));
        response.sendRedirect("Dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
