package Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SendSprcific", urlPatterns = "/Staff/SendSprcific")
public class SendSprcific extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("receiver_id");
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        Staff staff = new Staff();
        staff.message_specific(message, title, id, (Integer) session.getAttribute("userID"));
        response.sendRedirect("messages.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
