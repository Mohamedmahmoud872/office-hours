package Staff;

import base.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteSlot", urlPatterns = "/Staff/DeleteSlot")
public class DeleteSlot extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("slot_id"));
        Staff staff = new Staff();
        staff.delete_slot(id);
        DB database = new DB();
        HttpSession session = request.getSession(true);
        database.newNotification("You delete Slot from Your Office Hours", (Integer) session.getAttribute("userID"));
        response.sendRedirect("Dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
