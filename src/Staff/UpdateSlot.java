package Staff;

import base.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;

@WebServlet(name = "UpdateSlot", urlPatterns = "/Staff/UpdateSlot")
public class UpdateSlot extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("slotid_update");
        String day = request.getParameter("day");
        String time = request.getParameter("time");
        String type = request.getParameter("type");
        String location = request.getParameter("location");
        Staff staff = new Staff();
        System.out.println(id);
        staff.update_slot(id, day, time, type, location);
        DB database = new DB();
        database.newNotification("You Update your office hours Slot", (Integer) session.getAttribute("userID"));
        response.sendRedirect("Dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
