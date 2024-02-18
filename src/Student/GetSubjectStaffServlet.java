package Student;

import Student.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetSubjectStaffServlet",urlPatterns = "/Student/GetSubjectStaffServlet")
public class GetSubjectStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            Student student =new Student();
            String output = student.getSubjectsStaff();
            HttpSession session = request.getSession(true);
            session.setAttribute("Subject_Staff_Data",output);
            response.sendRedirect("staff.jsp");
        }finally {
            out.close();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
