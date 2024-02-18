package Staff;

import Student.Student;
import base.DB;
import base.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CancelReservation",urlPatterns = "/Staff/CancelReservation")
public class CancelReservation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = new Staff();
        staff.cancel_reservation(id);
        DB database = new DB();
        HttpSession session = request.getSession(true);
        database.newNotification("You have cancelled meeting reservation", (Integer) session.getAttribute("userID"));
        SendEmail sendEmail = new SendEmail();
        String email = database.getEmailByUserID((Integer) session.getAttribute("userID"));
        sendEmail.sendMail(email,"Meeting Cancelled" ,"You have meeting has been cancelled");
        response.sendRedirect("officehours.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
