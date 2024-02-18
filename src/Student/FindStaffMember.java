package Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FindStaffMember",urlPatterns = "/Student/FindStaffMember")
public class FindStaffMember extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String name = request.getParameter("member_name");
            Student student = new Student();
            int staff_id = student.getStaffIDByUsername(name);
            if(staff_id ==-1){
                out.println("The username is invalid");
                out.println("<a href=\"Dashboard.jsp\">Back To Dashboard</a>");
            }
            else{
                String memberContact = student.getStaffMemberContact(staff_id);
                String memberOfficeHours = student.getStaffMemberOfficeHours(staff_id);
                HttpSession session = request.getSession(true);
                session.setAttribute("StaffMemberContact",memberContact);
                session.setAttribute("StaffMemberOfficeHours",memberOfficeHours);
                response.sendRedirect("staff_member.jsp");
            }
        }finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
