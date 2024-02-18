package Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

@WebServlet(name = "GetIDsOfStaff", urlPatterns = "/Staff/GetIDsOfStaff")
public class GetIDsOfStaff extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Vector<Integer> staff_ids = new Vector<>();
        String name = request.getParameter("subject");
        String message = request.getParameter("message");
        String title = request.getParameter("title");
        Staff staff = new Staff();
        staff_ids = staff.getSubjectTeamIDs(name);
        System.out.println(staff_ids);
        session.setAttribute("IDs",staff_ids);
        for (int i=0; i<staff_ids.size(); i++) {
            staff.message_specific(message, title, staff_ids.elementAt(i), 2);
        }
        response.sendRedirect("messages.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
