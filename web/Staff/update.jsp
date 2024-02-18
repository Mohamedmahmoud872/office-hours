<%--
  Created by IntelliJ IDEA.
  User: Mohamed
  Date: 1/13/2021
  Time: 7:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Staff Dashboard</title>

    <link rel="stylesheet" href="../bootstrap.min.css">
    <link rel="stylesheet" href="../style.css">
</head>
<body>
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
<% int id= Integer.parseInt(request.getParameter("slot_id"));
    session.setAttribute("slotid_update",id);
%>
<div class="container">
    <h1 class="page_title">Update Slot</h1>
    <div class="row">
        <div class="col-md-7 center_div">
            <form action="UpdateSlot" method="post">
                <label>Choose the day:</label>
                <select name="day" class="form-control" required>
                    <option value="sat">Sat</option>
                    <option value="sun">Sun</option>
                    <option value="mon">Mon</option>
                    <option value="tues">Tues</option>
                    <option value="wed">Wed</option>
                    <option value="thur">Thur</option>
                    <option value="fri">Fri</option>
                </select>
                <input class="form-control" placeholder="Select the time" type="time" name="time" required>
                <label>Choose the type</label>
                <select class="form-control" name="type" required>
                    <option value="online">Online</option>
                    <option value="offline">Offline</option>
                </select>
                <label>Choose the location:</label>
                <input type="text" class="form-control" name="location" required>
                <br><br>
                <input type="submit" class="btn btn-success" value="Update">
                <input type="hidden" name="slot_id" value="id" >
            </form>
        </div>
    </div>
</div>
<script src="../jquery.js"></script>
<script src="../bootstrap.min.js"></script>
</body>
</html>
