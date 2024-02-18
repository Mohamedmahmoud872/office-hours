package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class DB 
{
	private String url="jdbc:mysql://localhost:3306/office_hours";
	private String name="root";
	private String pass="";
	private String driver="com.mysql.jdbc.Driver";
	public void load(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection Getcon()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection(url, name, pass);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public String insertIntoDB(users user)
	{
		load(driver);
		String result="Sign up successfully";
		Connection con =Getcon();
		String sql="INSERT INTO `users` (`ID`, `username`, `display_name`, `password`, `role`, `Email`, `mobile`) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1, user.getName());
			pre.setString(2, user.getDiplayName());
			pre.setString(3, user.getPassword());
			pre.setString(4, user.getRole());
			pre.setString(5, user.getMail());
			pre.setString(6, user.getMobile());
			pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="failed...!";
		}
		return result;
		
	}


	public int checkDB(String name,String password,String role)
	{
		int userID=0;
		//String result="";
		load(driver);
		Connection con =Getcon();
		try
		{
			PreparedStatement  stat=con.prepareStatement("select ID from users where users.role=? and users.username=? and users.password=?");
			stat.setString(1,role);
			stat.setString(2,name);
			stat.setString(3,password);
			ResultSet resu=stat.executeQuery();
			while(resu.next())
			{
				userID = resu.getInt("ID");
			}
		}
		catch (Exception e)
		{
			e.getStackTrace();
		}
		return userID;
	}
	
	public String checkDBForajax(String email)
	{
		String result="";
		load(driver);
		Connection con =Getcon();
		try 
		{
            PreparedStatement stat=con.prepareStatement("select * from users where Email=?");
            stat.setString(1,email);
            ResultSet resu=stat.executeQuery();
            if(resu.next())
            {
            	result="You are already have account";
            }
		} 
		catch (Exception e) 
		{
			e.getStackTrace();
		}
		return result;
	}
	public int checkDBforSigninAjax(String name,String password)
	{
		int userID=0;
		//String result="";
		load(driver);
		Connection con =Getcon();
		try
		{
			PreparedStatement  stat=con.prepareStatement("select ID from users where users.username=? and users.password=?");
			stat.setString(1,name);
			stat.setString(2,password);
			ResultSet resu=stat.executeQuery();
			while(resu.next())
			{
				userID = resu.getInt("ID");
			}
		}
		catch (Exception e)
		{
			e.getStackTrace();
		}
		return userID;
	}

	public String checkSignInAjax(String username,String password)
	{
		int id=-1;
		String result="";
		load(driver);
		Connection con =Getcon();
		try
		{
			PreparedStatement stat=con.prepareStatement("SELECT ID FROM users WHERE username=? AND password=?");
			stat.setString(1,username);
			stat.setString(1,password);
			ResultSet resu=stat.executeQuery();
			while (resu.next()){
				id=resu.getInt("ID");
			}
			if (id == -1){
				result = "The Credentials are wrong";
			}else{
				result = "";
			}
		}
		catch (Exception e)
		{
			e.getStackTrace();
		}
		return result;
	}
	
	public String changePass(String newpassword,String oldpassword)
	{
		String result="Password changed successfully";
		load(driver);
		Connection con =Getcon();
		String sql="update users set password=? where password=?";
		try 
		{
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1,newpassword);
			pre.setString(2,oldpassword);
			pre.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			result="failed...!";
		}
		return result;
	}
	public void newNotification(String notification,int userID)
	{
		load(driver);
		String result="Sign up successfully";
		Connection con =Getcon();
		String sql="insert into office_hours.notifications(notification,userid) values(?,?)";

		try {
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setInt(2, userID);
			pre.setString(2, notification);

			pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getEmailByUserID(int userID)
	{
		String email="";
		load(driver);
		Connection con =Getcon();
		try
		{
			PreparedStatement  stat=con.prepareStatement("select Email from users where users.ID=?");
			stat.setInt(1,userID);

			ResultSet resu=stat.executeQuery();
			while(resu.next())
			{
				email = resu.getString("Email");
			}
		}
		catch (Exception e)
		{
			e.getStackTrace();
		}
		return email;
	}
	public String getNotification(int userID){
		String notifications ="";
		try{
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT notification from notifications WHERE userid=" + userID);
			while (resultSet.next()) {
				notifications += "<div class=\"alert alert-warning\">" + resultSet.getString("notification") + "</div>";
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return notifications;
	}
	public String getProfileForm(int userID){
		String form ="";

		try{
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * from users WHERE ID=" + userID);
			while (resultSet.next()) {
				form += "<input type=\"text\" class=\"mb-1 form-control\" placeholder=\"Username\" name=\"username\" value=\"" +resultSet.getString("username") + "\">";
				form += "<input type=\"text\" class=\"mb-1 form-control\" placeholder=\"Display Name\" name=\"display_name\" value=\"" + resultSet.getString("display_name") + "\">";
				form += "<input type=\"text\" class=\"mb-1 form-control\" placeholder=\"Password\" name=\"password\" value=\"" + resultSet.getString("password") + "\">";
				form += "<input type=\"Email\" class=\"mb-1 form-control\" placeholder=\"Email\" name=\"email\" value=\"" + resultSet.getString("Email") + "\">";
				form += "<input type=\"text\" class=\"mb-1 form-control\" placeholder=\"Mobile\" name=\"mobile\" value=\"" + resultSet.getString("mobile") + "\">";
				form += "<input type=\"hidden\" value=\"" + userID + "\">";
				form += "<input type=\"submit\" class=\"mt-1 btn btn-warning\" value=\"Update Profile Data\">";
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return form;
	}
	public void UpdateProfile(int userID , String username,String display_name,String password,String email,String mobile)
	{
		load(driver);
		Connection con =Getcon();
		String sql="update users set username=?,display_name=?,password=?,Email=?,mobile=? where ID=?";
		try
		{
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1,username);
			pre.setString(2,display_name);
			pre.setString(3,password);
			pre.setString(4,email);
			pre.setString(5,mobile);
			pre.setInt(6,userID);
			pre.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
