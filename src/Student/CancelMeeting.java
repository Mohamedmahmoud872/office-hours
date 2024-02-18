package Student;

import base.DB;
import base.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CancelMeeting",urlPatterns = "/Student/CancelMeeting")
public class CancelMeeting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int meeting_id = Integer.parseInt(request.getParameter("meeting_id"));
        Student student = new Student();
        student.CancelMeeting(meeting_id);
        HttpSession session = request.getSession(true);
        DB database = new DB();
        database.newNotification("You have cancelled meeting reservation", (Integer) session.getAttribute("userID"));
        SendEmail sendEmail = new SendEmail();
        String email = database.getEmailByUserID((Integer) session.getAttribute("userID"));
        sendEmail.sendMail(email,"Meeting Cancelled" ,"You have meeting has been cancelled");
        response.sendRedirect("Dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
