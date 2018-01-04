<%-- 
    Document   : index
    Created on : Jan 2, 2018, 8:14:46 PM
    Author     : XUZH0001
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Poor Giraffe RedBot</title>
    </head>
    <body>
        <h1>Poor Giraffe :(</h1>
        <form action="RegisterServlet">
            User name: <input type="text" name="username" size="20px"><br>
            Password: <input type="password" name="password" size="20px"><br>
            <button type="submit">Register</button>
        </form><br><br>
        <form action="LoginServlet">
            User name: <input type="text" name="username" size="20px"><br>
            Password: <input type="password" name="password" size="20px"><br>
            <button type="submit">Login</button>
        </form>
    </body>
</html>
