<%-- 
    Document   : usersessions
    Created on : Jan 4, 2018, 2:11:50 AM
    Author     : XUZH0001
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
        <title>My sessions</title>
    </head>
    <body>
    <center>
        <h1 style="margin:20px 0 20px 0">My poor sessions :(</h1>
    </center>
        <div class="row" style="width:100%">
            <div style="width:5%"></div>
            <div class="col-sm-9">
                <canvas id="giraffeMap" width="1000" height="800" style="background-color:#d9d9d9">    
                </canvas>
            </div>
            <div class="col-sm-2" style="margin-left:30px">
                <div class="row">
                    <div style="text-align:right; margin-bottom:20px">
                        <form action="GotoServlet">
                            <input name="gotobutton" type="submit" class="btn btn-outline-warning" value="Log out">
                        </form>
                    </div>
                </div>
                <div class="row">
                    <c:if test="${not empty username}">
                        <div>
                            <p style="font-size:16px; font-weight:400; color:#ffc107; text-align:right">
                                User: ${username}<br>
                                ID: ${userId}<br>
                                <c:if test="${not empty selectedSessionId}">
                                    Session ${selectedSessionId}
                                </c:if>
                            </p>
                        </div>
                    </c:if>
                </div>
                    
                </div>
            </div>
        </div>
            <img src="resources/giraffe-banner1.jpg" alt=""/>
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th>ID</th>
                        <th>Start time</th>
                        <th>End time</th>
                    </tr>
                </thead>
                <c:forEach var="session" items="${mySessions}">
                    <tr>
                        <td><c:out value="${session.id}" /></td>
                        <td><c:out value="${session.startTime}" /></td>
                        <c:if test="${not empty session.endTime}">
                            <td><c:out value="${session.endTime}" /></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table><br>
            <div>
                <form action="SessionServlet">
                    Select session (id): <input type="text" name="selectedSessionId" size="20px"><br>
                    <button type="submit">Show movements</button>
                </form>
            </div>
        </div>

        <c:if test="${not empty mySelectedSessionId}">
            Selected session ID: ${mySelectedSessionId}
        </c:if><br><br>
        <c:if test="${not empty commandList}">
            <c:forEach var="command" items="${commandList}">
                ${command.commandType}
            </c:forEach>
            <form action="MovementServlet">
                <button type="submit" name="replaybutton">Replay</button>
            </form>
        </c:if><br>
        </body>
        </html>
