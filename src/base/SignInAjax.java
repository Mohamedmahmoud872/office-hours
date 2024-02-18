package base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignInAjax",urlPatterns = "/SignInAjax")
public class SignInAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);
        String Name=request.getParameter("name");
        String password=request.getParameter("pass");
        DB database=new DB();
        int userID=database.checkDBforSigninAjax(Name, password);
        if(userID != 0){
            out.print("");
        }else {
            out.print("The Credentials are wrong");
        }
    }
}
