package Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CheckEmail", urlPatterns = "/Staff/CheckEmail")
public class CheckEmail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("email");
        Staff staff = new Staff();
        int id = staff.getUserIDByEmail(mail);
        HttpSession session = request.getSession(true);
        session.setAttribute("receiver_id",id);
        response.sendRedirect("messageSpecific.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
