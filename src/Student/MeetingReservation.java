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

@WebServlet(name = "MeetingReservation",urlPatterns = "/Student/MeetingReservation")
public class MeetingReservation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int slot_id = Integer.parseInt(request.getParameter("slot_id"));
        String meeting_date= request.getParameter("date");
        HttpSession session = request.getSession(true);
        Student student = new Student();
        student.newMeeting(slot_id, (Integer) session.getAttribute("userID"),meeting_date);
        DB database = new DB();
        database.newNotification("You have Arrange new meeting at" + meeting_date.toString(), (Integer) session.getAttribute("userID"));
        SendEmail sendEmail = new SendEmail();
        String email = database.getEmailByUserID((Integer) session.getAttribute("userID"));
        sendEmail.sendMail(email,"Meeting Creating" ,"You Create New meeting at" + meeting_date.toString());
        response.sendRedirect("Dashboard.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
