package Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SendStaffMessageServlet",urlPatterns = "/Student/SendStaffMessageServlet")
public class SendStaffMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int toID = Integer.parseInt(request.getParameter("to"));
        String title = request.getParameter("title");
        String message = request.getParameter("message");

        HttpSession session = request.getSession(true);
        int from = (int) session.getAttribute("userID");
        Student student = new Student();
        student.newMessage(title,toID,message,from);

        response.sendRedirect("Dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
