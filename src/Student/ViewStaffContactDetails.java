package Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ViewStaffContactDetails",urlPatterns = "/Student/ViewStaffContactDetails")
public class ViewStaffContactDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            Student student = new Student();
            int staff_id = Integer.parseInt(request.getParameter("staff_id"));

            String table = student.getStaffMemberOfficeHours(staff_id);
            String contact = student.getStaffMemberContact(staff_id);

            HttpSession session = request.getSession(true);
            session.setAttribute("StaffMemberOfficeHours",table);
            session.setAttribute("StaffMemberContact" , contact);
            response.sendRedirect("staff_member.jsp");
        }
        finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
