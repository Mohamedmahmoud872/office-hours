package base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SignupValidation",urlPatterns = "/SignupValidation")
public class SignupValidation extends HttpServlet 
{
	Connection con;
	PreparedStatement pre;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String role=request.getParameter("role");
		String display=request.getParameter("display");
		String mobile = request.getParameter("mobile");
		String subject="Your Account Password";

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"+ "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(9);
		for (int i = 0; i < 9; i++)
		{
			int index = (int)(AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString .charAt(index));
		}

		String content="Your password is: "+sb.toString();
		SendEmail sm=new SendEmail();
		sm.sendMail(email, subject, content);
		out.println("please check your email...!");
		users user=new users(name,email,role,display,sb.toString(),mobile);
		DB database=new DB();
		String result=database.insertIntoDB(user);
		out.println(result);
		response.sendRedirect("SignIn.jsp");
	}

}
