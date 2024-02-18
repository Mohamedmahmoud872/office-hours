package Student;

import java.sql.*;
import java.util.Vector;

public class Student {
    //Get The Name Of User By ID
    String getNamebyUserID(int id){
        String name = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select display_name from users Where ID=" + id);
            while (resultSet.next()) {
                name = resultSet.getString("display_name");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }
    //Get The User ID By Staff ID
    int getUserIDByStaffID(int id){
        int userID = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select userid from staff Where ID=" + id);
            while (resultSet.next()) {
                userID = resultSet.getInt("userid");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return userID;
    }
    //Get The Name By StaffID
    String getNameByStaffID(int staffID){
        int user_id = getUserIDByStaffID(staffID);
        String name = getNamebyUserID(user_id);
        return name;
    }
    //Get Possible user id for the name given
    Vector<Integer> getUserIDVectorByName(String name){
        Vector<Integer> ids = new Vector<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select ID from users Where display_name like %" + name + "%");
            while (resultSet.next()) {
                ids.add(resultSet.getInt("ID"));
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ids;
    }
    //Get Subject Name By ID
    String getSubjectNameByID(int id){
        String name = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select name from subjects Where ID=" + id);
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return name;
    }
    private int getSubjectStaffCount(int sub_id){
        int count=0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(ID) from staff_subjects where subject_id=" + sub_id);
            while (resultSet.next()) {
                count = resultSet.getInt(0);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    Vector<Integer> getSubjectStaffIDs(int sub_id){
        Vector<Integer> ids = new Vector<Integer>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select staff_id from staff_subjects where subject_id=" + sub_id);
            while (resultSet.next()) {
                ids.add(resultSet.getInt("staff_id"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ids;
    }
    String getSubjectsStaff(){
        String SubjectStaff="";
        Vector<Integer> subjects_ID= new Vector<Integer>();
        Vector<String> subjects_name = new Vector<>();
        Vector<Integer> staff_ids = new Vector<Integer>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select ID,name from subjects");
            while (resultSet.next()) {
                subjects_ID.add(resultSet.getInt("ID"));
                subjects_name.add(resultSet.getString("name"));
            }
            int subject_staff_count=0;
            String staff_table="";
            ResultSet result;
            for (int i=0;i<subjects_ID.size();i++){
                SubjectStaff += "<h3 style=\"font-weight:bold;\">" + subjects_name.elementAt(i) + "</h3>";
                //subject_staff_count = getSubjectStaffCount(subjects_ID.elementAt(i));
                staff_table = "<table class=\"table table-hover\">\n";
                staff_table += "<thead class=\"table-dark\">" +
                        "<tr>" +
                        "<th>ID</th>" +
                        "<th>Name</th>" +
                        "<th>Role</th>" +
                        "<th>Action</th>" +
                        "</tr>" +
                        "</thead>" +
                        "<tbody style=\"background-color:#d0d0d0;\">";
                staff_ids = getSubjectStaffIDs(subjects_ID.elementAt(i));
                for (int j=0;j<staff_ids.size();j++){
                    staff_table += "<tr>";
                    staff_table += "<td>" + (j+1) + "</td>";
                    result = statement.executeQuery("SELECT users.display_name,staff.type FROM `staff` INNER JOIN users ON users.ID = staff.userid where staff.ID=" + staff_ids.elementAt(j));
                    while(result.next()){
                        staff_table += "<td>" + result.getString("display_name")  + "</td>";
                        staff_table += "<td>" +  result.getString("type")  + "</td>";
                        staff_table += "<td>" + "<form style=\"display:inline;\" action=\"ViewStaffContactDetails\" method=\"post\">" +
                                "<input type=\"submit\" class=\"btn btn-success sub-btn\" value=\"Member Details\">" +
                                "<input type=\"hidden\" name=\"staff_id\" value=\"" + staff_ids.elementAt(j) + "\">" +
                                "</form>"  + "</td>";
                    }
                    staff_table += "</tr>";
                }
                staff_table += "</table>";
                staff_table += "<br/>";
                SubjectStaff += staff_table;
                staff_table ="";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return SubjectStaff;
    }
    String getStaffMemberContact(int staff_id){
        String staff_details ="";
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT users.ID,users.display_name , users.Email , users.mobile from users INNER JOIN staff on users.ID = staff.userid WHERE staff.ID =" + staff_id);
            while (resultSet.next()) {
                staff_details += "<h4 class=\"contact_details\"><strong>Name:</strong> " + resultSet.getString("display_name") + "</h4>";
                staff_details += "<h4 class=\"contact_details\"><strong>Email:</strong> " + resultSet.getString("Email") + "</h4>";
                staff_details += "<h4 class=\"contact_details\"><strong>Mobile:</strong> " + resultSet.getString("mobile") + "</h4>";
                staff_details += "<div class=\"container\"><div class=\"row\"><div class=\"col-md-6 center_div\"><form action=\"SendStaffMessage.jsp\" method=\"post\"><input type=\"hidden\" name=\"to_ID\" value=\"" + resultSet.getString("ID") + "\">" +
                        "<input type=\"submit\" value=\"Send Message\" class=\"btn btn-primary\"></form></div></div></div>";
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return staff_details;
    }
    String getStaffMemberOfficeHours(int staff_id){
        String staffOfficeHours="<table class=\"table table-hover\">\n";
        staffOfficeHours += "<thead class=\"table-dark\">" +
                "<tr>" +
                "<th>ID</th>" +
                "<th>Day</th>" +
                "<th>Slot</th>" +
                "<th>Type</th>" +
                "<th>Location</th>" +
                "<th>Action</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody style=\"background-color:#d0d0d0;\">";
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT officehours.id,officehours.day , officehours.slot , officehours.type , officehours.location FROM officehours WHERE officehours.staff_id =" + staff_id);
            int i=1;
            while (resultSet.next()) {
                staffOfficeHours += "<tr>";
                staffOfficeHours += "<td>" + i + "</td>";
                staffOfficeHours += "<td>" + resultSet.getString("day") + "</td>";
                staffOfficeHours += "<td>" + resultSet.getString("slot") + "</td>";
                staffOfficeHours += "<td>" + resultSet.getString("type") + "</td>";
                staffOfficeHours += "<td>" + resultSet.getString("location") + "</td>";
                staffOfficeHours += "<td>" + "<form style=\"display:inline;\" action=\"bookMeeting.jsp\" method=\"post\">" +
                        "<input type=\"submit\" class=\"btn btn-success sub-btn\" value=\"Book Meeting\">" +
                        "<input type=\"hidden\" name=\"slot_id\" value=\"" + resultSet.getString("id") + "\">" +
                        "</form>"  + "</td>";
                staffOfficeHours += "</tr>";
                i++;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        staffOfficeHours += "</table>";
        return staffOfficeHours;
    }
    int getStaffIDByUsername(String username){
        int staff_id=-1;
        int userID=0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select ID from users Where username='" + username + "'");
            while (resultSet.next()) {
                userID = resultSet.getInt("ID");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        staff_id = getStaffIDByUserID(userID);
        return staff_id;
    }
    private int getStaffIDByUserID(int userID) {
        int staff_id=0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select ID from staff Where userid=" + userID);
            while (resultSet.next()) {
                staff_id = resultSet.getInt("ID");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return staff_id;
    }
    String getMeetingsTable(int student_id){
        String table="<table class=\"table table-hover\">\n";
        table += "<thead class=\"table-dark\">" +
                "<tr>" +
                "<th>ID</th>" +
                "<th>Staff Member</th>" +
                "<th>Day</th>" +
                "<th>Slot</th>" +
                "<th>Type</th>" +
                "<th>Location</th>" +
                "<th>Date</th>" +
                "<th>Action</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody style=\"background-color:#d0d0d0;\">";
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT meetings.date,meetings.id,officehours.staff_id,officehours.day , officehours.slot , officehours.type , officehours.location FROM officehours INNER JOIN meetings on officehours.ID = meetings.slot_id WHERE student_id = " + student_id);
            int i=1;
            while (resultSet.next()) {
                table += "<tr>";
                table += "<td>" + resultSet.getInt("id") + "</td>";
                table += "<td>" + getNameByStaffID(resultSet.getInt("staff_id")) + "</td>";
                table += "<td>" + resultSet.getString("day") + "</td>";
                table += "<td>" + resultSet.getString("slot") + "</td>";
                table += "<td>" + resultSet.getString("type") + "</td>";
                table += "<td>" + resultSet.getString("location") + "</td>";
                table += "<td>" + resultSet.getString("date") + "</td>";
                table += "<td>" + "<form style=\"display:inline;\" action=\"CancelMeeting\" method=\"post\">" +
                        "<input type=\"submit\" class=\"btn btn-danger sub-btn\" value=\"Cancel Meeting\">" +
                        "<input type=\"hidden\" name=\"meeting_id\" value=\"" + resultSet.getString("id") + "\">" +
                        "</form>"  + "</td>";
                table += "</tr>";
                i++;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        table += "</table>";
        return table;
    }
    void CancelMeeting(int meeting_id){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM meetings where ID=?");
            statement.setInt(1,meeting_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void newMeeting(int slot_id,int student_id,String date){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO meetings(slot_id,student_id,date) values(?,?,?)");
            statement.setInt(1,slot_id);
            statement.setInt(2,student_id);
            statement.setDate(3, Date.valueOf(date));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void newMessage(String title,int to,String message,int from){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO messages(message,from_id,to_id,title) values(?,?,?,?)");
            statement.setString(1,message);
            statement.setString(4,title);
            statement.setInt(2,from);
            statement.setInt(3, to);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String view_messages(int id) {
        String sql = "SELECT message,from_id,title FROM messages WHERE to_id = ?";
        String messages = "";String name="";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/office_hours", "root", "");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet r = statement.executeQuery();
            messages = "<table class=\"table table-striped table-hover\">\n" +
                    "<thead class=\"table-dark\">" +
                    "<tr>" +
                    "<th>Title</th>" +
                    "<th>Messages</th>" +
                    "<th>From</th>" +
                    "</tr>" +
                    "</thead>" +
                    "<tbody style=\"background-color: #d0d0d0;" + "\">";
            while(r.next()) {
                messages += "<tr>";
                messages += "<td>" + r.getString("title") + "</td>";
                messages += "<td>" + r.getString("message") + "</td>";
                name = getNameByUserID(r.getInt("from_id"));
                messages += "<td>" + name  + "</td>";
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
}
