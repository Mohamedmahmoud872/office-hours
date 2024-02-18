package Staff;
import java.sql.*;
import java.util.Vector;
import java.time.*;
public class Staff {
	private Connection con;
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours","root",""); 
		
		}
		catch (SQLException | ClassNotFoundException e) {
		
		System.out.println(e);
		}
	}
	PreparedStatement statement;
	ResultSet r;
	public String view_student_messages(int id) {
		String sql = "SELECT message,from_id,title FROM messages WHERE to_id = ?";
		String messages = "";String name="";
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			r = statement.executeQuery();
			messages = "<table class=\"table mt-2 table-striped table-hover\">\n" +
							"<thead class=\"table-dark\">" +
							"<tr>" +
							"<th>Title</th>" +
							"<th>Messages</th>" +
							"<th>From</th>" +
							"<th>Action</th>" +
							"</tr>" +
							"</thead>" +
							"<tbody style=\"background-color: #d0d0d0;" + "\">";
			while(r.next()) {
				messages += "<tr>";
				messages += "<td>" + r.getString("title") + "</td>";
				messages += "<td>" + r.getString("message") + "</td>";
				name = getNameByUserID(r.getInt("from_id"));
				messages += "<td>" + name  + "</td>";
				messages += "<td>" +
                        "<form style=\"display:inline;\" action=\"reply.jsp\" method=\"post\">" +
                        "<input type=\"submit\" class=\"btn btn-success\" value=\"reply\">" +
                        "<input type=\"hidden\" name=\"to\" value=\"" + r.getString("from_id") + "\">" +
                        "<input type=\"hidden\" name=\"reply\" value=\"" + r.getString("title") + "\">" +
                        "<input type=\"hidden\" name=\"from\" value=\"" + id + "\">" +
                        "</form>" +
                        "</td>";
				messages += "</tr>";
			}
			messages += "</tbody>";
			messages += "</table>";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return messages;
	}
	public String getNameByUserID(int id){
		String name="";
		String sql = "SELECT users.display_name FROM users WHERE ID=?";
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours","root","");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				name = result.getString("display_name");
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return name;
	}
	public void reply_message(String message,int from,int to ,String title) {
		try {
			statement = con.prepareStatement("INSERT INTO messages(message, from_id, to_id, title) VALUES (?, ?, ?, ?)");
			statement.setString(1, message);
			statement.setInt(2, from);
			statement.setInt(3, to);
			statement.setString(4, title);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void message_specific(String message, String title, int to,int from) {
		try {
			statement = con.prepareStatement("INSERT INTO messages(message, from_id, to_id, title) VALUES (?, ?, ?, ?)");
			statement.setString(1, message);
			statement.setInt(2, from);
			statement.setInt(3, to);
			statement.setString(4, title);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getUserIDByEmail(String email){
		int userID=0;
		String sql = "SELECT users.ID FROM users WHERE Email=?";
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours","root","");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				userID = result.getInt("ID");
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return userID;
	}
	public int getSubjectIDByName(String subject_name){
		int sub_id = 0;
		String sql = "SELECT ID FROM subjects WHERE name=?";
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours","root","");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, subject_name);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				sub_id = result.getInt("ID");
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return sub_id;
	}
	public Vector<Integer> getSubjectTeamIDs(String subject){
		Vector<Integer> staff_ids = new Vector<>();
		int sub_id = getSubjectIDByName(subject);
		String sql = "SELECT staff_id FROM staff_subjects WHERE subject_id=?";
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours","root","");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, sub_id);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				staff_ids.add(result.getInt("staff_id"));
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		//Get Vector of UserID by Staff ID
		/*Vector<Integer> ids = new Vector<>();
		sql = "SELECT userid FROM staff WHERE ID=?";
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours","root","");
			PreparedStatement statement;
			ResultSet result;
			for(int i=0;i<staff_ids.size();i++){
				statement = connection.prepareStatement(sql);
				statement.setInt(1, staff_ids.elementAt(i));
				result = statement.executeQuery();
				while(result.next()){
					ids.add(result.getInt("ID"));
				}
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}*/
		return staff_ids;
	}
	public String search_student(int id) {
		String details="";
		String sql = "SELECT users.display_name, users.Email, users.mobile FROM users WHERE ID=? And role=?";
		try{
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours","root","");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, "student");
			ResultSet result = statement.executeQuery();
			while(result.next()){

				details += "<h4 class=\"contact_details\"><strong>Name:</strong> " +result.getString("display_name") + "</h4>";
				details += "<h4 class=\"contact_details\"><strong>Email:</strong> " + result.getString("Email") + "</h4>";
				details += "<h4 class=\"contact_details\"><strong>Mobile:</strong> " + result.getString("mobile") + "</h4>";
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return details;
	}
	public String view_slots(int staff_id) {
		String sql = "SELECT officehours.ID, officehours.day, officehours.slot, officehours.type, officehours.location FROM officehours WHERE staff_id = ?";
		String slots = "";
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, staff_id);
			r = statement.executeQuery();
			slots = "<table class=\"table table-striped table-hover\">\n" +
					"<thead class=\"table-dark\">" +
					"<tr>" +
					"<th>Day</th>" +
					"<th>Time</th>" +
					"<th>Type</th>" +
					"<th>Location</th>" +
					"<th>View Reservations</th>" +
					"</tr>" +
					"</thead>" +
					"<tbody style=\"background-color: #d0d0d0;" + "\">";
			while(r.next()) {
				slots += "<tr>";
				slots += "<td>" + r.getString("day") + "</td>";
				slots += "<td>" + r.getString("slot") + "</td>";
				slots += "<td>" + r.getString("type") + "</td>";
				slots += "<td>" + r.getString("location") + "</td>";
				slots += "<td>" +
						"<form style=\"display:inline;\" action=\"GetReservations\" method=\"post\">" +
						"<input type=\"submit\" class=\"btn btn-success\" value=\"view\">" +
						"<input type=\"hidden\" name=\"slot_id\" value=\"" + r.getString("ID") + "\">" +
						"</form>" +
						"</td>";
				slots += "</tr>";
			}
			slots += "</tbody>";
			slots += "</table>";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return slots;
	}
	public String view_reservations(int slot_id) {

		String sql = "SELECT meetings.ID, meetings.student_id FROM meetings WHERE slot_id = ?";
		String res = "";
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, slot_id);
			r = statement.executeQuery();
			res = "<table class=\"table table-striped table-hover\">\n" +
					"<thead class=\"table-dark\">" +
					"<tr>" +
					"<th>ID</th>" +
					"<th>Student ID</th>" +
					"<th>Action</th>" +
					"</tr>" +
					"</thead>" +
					"<tbody style=\"background-color: #d0d0d0;" + "\">";
			while(r.next()) {
				res += "<tr>";
				res += "<td>" + r.getString("ID") + "</td>";
				res += "<td>" + r.getString("student_id") + "</td>";
				res += "<td>" +
						"<form style=\"display:inline;\" action=\"CancelReservation\" method=\"post\">" +
						"<input type=\"submit\" class=\"btn btn-success\" value=\"Cancel\">" +
						"<input type=\"hidden\" name=\"id\" value=\"" + r.getString("ID") + "\">" +
						"</form>" +
						"</td>";
				res += "</tr>";
			}
			res += "</tbody>";
			res += "</table>";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	public void cancel_reservation(int id) {
		try {
			statement = con.prepareStatement("DELETE FROM meetings WHERE ID=?");
			statement.setInt(1,id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String manage_office_hours(int staff_id) {
		String sql = "SELECT officehours.ID, officehours.day, officehours.slot, officehours.type, officehours.location FROM officehours WHERE staff_id = ?";
		String slots = "";
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, staff_id);
			r = statement.executeQuery();
			slots = "<table class=\"table table-striped table-hover\">\n" +
					"<thead class=\"table-dark\">" +
					"<tr>" +
					"<th>Day</th>" +
					"<th>Time</th>" +
					"<th>Type</th>" +
					"<th>Location</th>" +
					"<th>Action</th>" +
					"</tr>" +
					"</thead>" +
					"<tbody style=\"background-color: #d0d0d0;" + "\">";
			while(r.next()) {
				slots += "<tr>";
				slots += "<td>" + r.getString("day") + "</td>";
				slots += "<td>" + r.getString("slot") + "</td>";
				slots += "<td>" + r.getString("type") + "</td>";
				slots += "<td>" + r.getString("location") + "</td>";
				slots += "<td>" +
						"<form style=\"display:inline;\" action=\"update.jsp\" method=\"post\">" +
						"<input type=\"submit\" class=\"btn btn-warning\" value=\"Update\">" +
						"<input type=\"hidden\" name=\"slot_id\" value=\"" + r.getString("ID") + "\">" +
						"<input type=\"hidden\" name=\"slot_day\" value=\"" + r.getString("day") + "\">" +
						"<input type=\"hidden\" name=\"slot_time\" value=\"" + r.getString("slot") + "\">" +
						"<input type=\"hidden\" name=\"slot_type\" value=\"" + r.getString("type") + "\">" +
						"<input type=\"hidden\" name=\"slot_location\" value=\"" + r.getString("location") + "\">" +
						"</form>" +
						"<form style=\"display:inline;\" action=\"DeleteSlot\" method=\"post\">" +
						"<input type=\"submit\" class=\"ml-1 btn btn-danger\" value=\"Delete\">" +
						"<input type=\"hidden\" name=\"slot_id\" value=\"" + r.getString("ID") + "\">" +
						"</form>" +
						"</td>";
				slots += "</tr>";
			}
			slots += "</tbody>";
			slots += "</table>";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return slots;
	}
	public void delete_slot(int id) {
		try {
			statement = con.prepareStatement("DELETE FROM officehours WHERE ID=?");
			statement.setInt(1,id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update_slot(int id, String d, String t, String new_type, String location) {

		try {
			statement = con.prepareStatement("UPDATE officehours SET  officehours.day=?, officehours.slot=?, officehours.type=?, officehours.location=?  WHERE ID=?");
			statement.setString(1,d);
			statement.setString(2, t);
			statement.setString(3,new_type);
			statement.setString(4,location);
			statement.setInt(5,id);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void add_slot(String d, String t, String new_type, String location) {

		try {
			statement = con.prepareStatement("INSERT INTO officehours (officehours.day, officehours.slot, officehours.type, officehours.location, officehours.staff_id) VALUES (?,?,?,?,?)");
			statement.setString(1,d);
			statement.setString(2, t);
			statement.setString(3,new_type);
			statement.setString(4,location);
			statement.setInt(5,1); // wait for session of staff id
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public String view_history(int staff_id, String from, String to) {
		String sql = "SELECT meetings.ID, meetings.student_id, meetings.date, officehours.day , officehours.slot,officehours.type,officehours.location FROM officehours " +
				"INNER JOIN meetings on officehours.ID = meetings.slot_id WHERE (meetings.date BETWEEN ? AND ? ) AND officehours.staff_id = ?";
		String res = "";
		try {
			statement = con.prepareStatement(sql);
			statement.setDate(1, Date.valueOf(from));
			statement.setDate(2, Date.valueOf(to));
			statement.setInt(3, staff_id);
			r = statement.executeQuery();
			res = "<table class=\"table table-striped table-hover\">\n" +
					"<thead class=\"table-dark\">" +
					"<tr>" +
					"<th>Date</th>"+
					"<th>Day</th>" +
					"<th>Time</th>" +
					"<th>Student ID</th>" +
					"<th>Type</th>" +
					"<th>Location</th>" +
					"<th>Action</th>" +
					"</tr>" +
					"</thead>" +
					"<tbody style=\"background-color: #d0d0d0;" + "\">";
			while(r.next()) {
				res += "<tr>";
				res += "<td>" + r.getString("meetings.date") + "</td>";
				res += "<td>" + r.getString("day") + "</td>";
				res += "<td>" + r.getString("slot") + "</td>";
				res += "<td>" + r.getString("student_id") + "</td>";
				res += "<td>" + r.getString("type") + "</td>";
				res += "<td>" + r.getString("location") + "</td>";
				res += "<td>" +
						"<form style=\"display:inline;\" action=\"CancelReservation\" method=\"post\">" +
						"<input type=\"submit\" class=\"btn btn-success\" value=\"Cancel\">" +
						"<input type=\"hidden\" name=\"id\" value=\"" + r.getString("meetings.ID") + "\">" +
						"</form>" +
						"</td>";
				res += "</tr>";
			}
			res += "</tbody>";
			res += "</table>";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
}




