<%--
  Created by IntelliJ IDEA.
  User: mohamed
  Date: 1/11/21
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <!-- Required meta tags -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>Staff Dashboard</title>

  <link rel="stylesheet" href="../bootstrap.min.css">
  <link rel="stylesheet" href="../style.css">
</head>
<body id="staff_dashboard">
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Staff Dashboard</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="Dashboard.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="notifications.jsp">Notifications</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="messages.jsp">Messages</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="officehours.jsp">Meetings</a>
        </li>
      </ul>
      <ul class="nav navbar-nav dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
           aria-expanded="false"><%=session.getAttribute("Username")%></a>
        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
          <li><a class="dropdown-item" href="profile.jsp">Edit Profile</a></li>
          <li>
            <hr class="dropdown-divider">
          </li>
          <li><a class="dropdown-item" href="../LogOut.jsp">Log out</a></li>
        </ul>
      </ul>
    </div>
  </nav>
  <div class="container" style="margin-top:50px;">
    <h2 id="dashboard_title">Staff Control Panel</h2>
    <div class="row" style="margin: 0px auto;">
      <div class="col-md-3 card text-white bg-primary mb-3 dash_card">
        <div class="card-body">
          <img src="../staff.svg" class="img-thumbnail card-img"/>
          <h2 class="text-center">Students</h2>
          <form method="post" action="search.jsp">
            <input type="submit" class="text-center dash_link" value="Search For Student" style="color:white;">
          </form>
        </div>
      </div>
      <div class="card text-white col-md-3 mb-3 bg-warning dash_card">
        <div class="card-body">
          <img src="../meeting.svg" class="img-thumbnail card-img"/>
          <h2 class="text-center">Messages</h2>
          <form method="post" action="GetMessageTable">
            <input type="submit" class="text-center dash_link" value="View/Reply Message" style="color:white;">
          </form>
        </div>
      </div>
      <div class="card text-white col-md-3 bg-success mb-3 dash_card">
        <div class="card-body">
          <img src="../hours.svg" class="img-thumbnail card-img"/>
          <h2 class="text-center">Office Hours</h2>
          <form method="post" action="ManageOfficeHours">
            <input type="submit" class="text-center dash_link" value="Manage Office Hours" style="color:white;">
          </form>
<%--          <a href="ManageOfficeHours" class="text-center" style="color:white;">Manage Office Hours</a>--%>
        </div>
      </div>
      <div class="card text-white col-md-3 bg-danger mb-3 dash_card">
        <div class="card-body">
          <img src="../meeting.svg" class="img-thumbnail card-img"/>
          <h2 class="text-center">Meetings</h2>
          <form method="post" action="GetOfficehourseTable">
            <input type="submit" class="text-center dash_link" value="View/Cancel Reservations" style="color:white;">
          </form>
<%--          <a href="meetings.jsp" class="text-center" style="color:white;">View/Cancel Meeting</a>--%>
        </div>
      </div>
    </div>
  </div>
  <script src="../jquery.js"></script>
  <script src="../bootstrap.min.js"></script>
  </body>
</html>
