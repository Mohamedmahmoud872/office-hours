package Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GetOfficehourseTable", urlPatterns = "/Staff/GetOfficehourseTable")
public class GetOfficehourseTable extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Staff staff = new Staff();
        String officehours_table = staff.view_slots(1);//Until We Pass User id Session while sign in
        HttpSession session = request.getSession(true);
        session.setAttribute("OfficeHoursTable",officehours_table);
        response.sendRedirect("officehours.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
