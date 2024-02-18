package Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ManageOfficeHours", urlPatterns = "/Staff/ManageOfficeHours")
public class ManageOfficeHours extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Staff staff = new Staff();
        String table = staff.manage_office_hours(1);//Until We Pass User id Session while sign in
        HttpSession session = request.getSession(true);
        session.setAttribute("ManageOfficeHoursTable",table);
        response.sendRedirect("manage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
