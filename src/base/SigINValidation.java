package base;

import java.io.IOException;
import java.io.PrintWriter;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SigINValidation
 */
@WebServlet("/SigINValidation")
public class SigINValidation extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
    public SigINValidation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(true);
		String Name=request.getParameter("name");
		String password=request.getParameter("pass");
		String role = request.getParameter("role");
		DB database=new DB();
		int userID=database.checkDB(Name, password,role);
		if(userID != 0)
		{
			session.setAttribute("Username",Name);
			session.setAttribute("password",password);
			session.setAttribute("userID",userID);
			session.setAttribute("userRole",role);
			out.println(role);
			if (role.equals("Staff"))
				response.sendRedirect("Staff/Dashboard.jsp");
			else
				response.sendRedirect("Student/Dashboard.jsp");
		}
		else
			response.sendRedirect("SignIn.jsp");
		//out.println(status);
	}

}
