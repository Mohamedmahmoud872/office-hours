package Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetMeetingTable",urlPatterns = "/Student/GetMeetingTable")
public class GetMeetingTable extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        HttpSession session = request.getSession(true);
        String meetingTable = student.getMeetingsTable((Integer) session.getAttribute("userID"));
        session.setAttribute("MeetingsTable",meetingTable);
        response.sendRedirect("meetings.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
