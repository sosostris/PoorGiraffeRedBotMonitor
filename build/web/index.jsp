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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
        <title>Poor Giraffe RedBot Monitor</title>
    </head>
    <body>
        <h1 align="center" style="margin-top:20px; margin-bottom:20px">Poor Giraffe Redbot Monitor :(</h1>
        <c:if test="${empty username}">
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
        </c:if>
        <c:if test="${not empty username}">
            <div style="width:100%">
                <p style="font-size:20px; font-weight:400; color:#ffc107; text-align:right; margin-right:270px">
                    Welcome back, ${username}!
                </p>
                <p hidden>${userId}</p>
            </div>
            <form action="GotoServlet">
                <div class="row">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-3">
                        <img src="resources/Giraffe.jpg" width="400px" height="400px" alt=""/>
                        <input name="gotobutton" type="submit" class="btn btn-warning"
                               style="margin-top:10px; position:relative; left:100px"
                               value="Giraffe Redbot Monitor">
                    </div>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-3">
                        <img src="resources/Giraffe-history.jpg" alt=""/>
                        <input name="gotobutton" type="submit" class="btn btn-warning"
                               style="margin-top:10px; position:relative; left:100px" value="View my sessions">
                    </div>
                    <div class="col-sm-1"></div>
                </div>
            </form><br>
        </c:if><br>
    </body>
</html>
