<%--
  Created by IntelliJ IDEA.
  User: mohamed
  Date: 1/15/21
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogOut</title>
</head>
<body>
    <%
        session.setAttribute("userID",-1);
        response.sendRedirect("SignIn.jsp");
    %>
</body>
</html>
