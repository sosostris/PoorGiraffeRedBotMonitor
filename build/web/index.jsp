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
            <img src="resources/giraffe-monitor.jpg" width="240px" height="240px" 
                 style="display:block; margin:0 auto;" alt=""/>
        </c:if>
        <c:if test="${newUser}">
            <p style="margin-left:470px; margin-top:20px; font-size:12px; color:graytext">
                Thank you for registering on Poor Giraffe RedBot Monitor!<br>
                Please log in with your registered account :(
            </p>  
        </c:if>

        <c:if test="${empty username}">
            <form action="LoginServlet" style="width:400px; margin-left:470px">
                <div class="form-group">
                    <label for="usr">Name:</label>
                    <input type="text" name="username" class="form-control" size="20px">
                </div>
                <div class="form-group" style="margin-bottom:20px">
                    <label for="pwd">Password:</label>
                    <input type="password" name="password" class="form-control" size="20px">
                </div>
                <input style="width:45%;" type="submit" class="btn btn-outline-warning" name="loginbutton" value="Login">
                <input style="width:45%; margin-left:35px;" type="submit" class="btn btn-outline-warning" name="loginbutton" value="Register">
            </form>
        </c:if>
        <c:if test="${not empty username}">
            <div style="width:100%">
                <form action="GotoServlet" style="padding-right:268px; text-align:right; margin-bottom:20px">
                    <span style="font-size:20px; font-weight:400; color:#ffc107; margin-right:10px">
                        Welcome back, ${username}!
                    </span>
                    <span hidden>${userId}</span>
                    <span>
                        <input name="gotobutton" type="submit" class="btn btn-outline-warning" value="Log out">
                    </span>
                </form>
            </div>
            <form action="GotoServlet">
                <div class="row">
                    <div class="col-sm-2"></div>
                    <div class="col-sm-3">
                        <img src="resources/Giraffe.jpg" width="400px" height="400px" alt=""/>
                        <input name="gotobutton" type="submit" class="btn btn-outline-warning"
                               style="margin-top:10px; position:relative; left:100px"
                               value="Giraffe Redbot Monitor">
                    </div>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-3">
                        <img src="resources/Giraffe-history.jpg" alt=""/>
                        <input name="gotobutton" type="submit" class="btn btn-outline-warning"
                               style="margin-top:10px; position:relative; left:100px" value="View my sessions">
                    </div>
                    <div class="col-sm-1"></div>
                    <c:if test="${isAdmin}">
                        <div class="col-sm-2">
                            <img src="resources/giraffe-console1-200.jpg" 
                                 style="margin-top:50px" width="200px" height="290px" alt=""/>
                            <input name="gotobutton" type="submit" class="btn btn-outline-warning"
                               style="margin-top:10px; position:relative; left:40px" value="Admin console">
                            
                        </div>
                    </c:if>
                </div>
            </form><br>
        </c:if><br>
    </body>
</html>
