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
        <title>My sessions</title>
    </head>
    <body>
        <h1>Poor Giraffe :(</h1>
        <c:if test="${isAdmin}">
            Welcome back, Giraffe admin :)
            <form action="SessionServlet">
                <input name="adminbutton" type="submit" value="Go to admin page">
            </form>
        </c:if><br><br>
        <c:if test="${not empty username}">
            My name: ${username}
        </c:if><br>
        <c:if test="${not empty newSessionId}">
            New session ID: ${newSessionId}
            <form action="MovementServlet">
                <input name="movementbutton" type="submit" value="UP">			
                <input name="movementbutton" type="submit" value="RIGHT">
                <input name="movementbutton" type="submit" value="DOWN">			
                <input name="movementbutton" type="submit" value="LEFT">
            </form>
        </c:if><br><br>
        <form action="SessionServlet">
            <input name="sessionbutton" type="submit" value="Start session">			
            <input name="sessionbutton" type="submit" value="End session">
            <input name="sessionbutton" type="submit" value="Get my sessions">
        </form><br><br>
        <c:if test="${not empty mySessions}">
            <div>
                <table border="1" cellpadding="5">
                    <caption><h2>My sessions</h2></caption>
                    <tr>
                        <th>ID</th>
                        <th>Start time</th>
                        <th>End time</th>
                    </tr>
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
        </c:if><br>
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
