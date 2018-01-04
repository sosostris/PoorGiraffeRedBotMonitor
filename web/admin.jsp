<%-- 
    Document   : admin
    Created on : Jan 4, 2018, 11:55:48 AM
    Author     : XUZH0001
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <h1>Hello Giraffe Admin!</h1>
        <form action="AdminServlet">
            <input name="viewallusersbutton" type="submit" value="View all users">
            <input name="viewallsessionsbutton" type="submit" value="View all sessions">
        </form>
        <c:if test="${not empty users}">
            <div>
                <table border="1" cellpadding="5">
                    <caption><h2>Users</h2></caption>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Created time</th>
                        <th>Type</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><c:out value="${user.id}" /></td>
                            <td><c:out value="${user.name}" /></td>
                            <td><c:out value="${user.createdTime}" /></td>
                            <c:if test="${not empty user.type}">
                                <td><c:out value="${user.type}" /></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table><br>
                <div>
                    <form action="AdminServlet">
                        Select user (id): <input type="text" name="selectedUserId" size="20px"><br>
                        <input type="submit" name="showusersessionsbutton" value="Show user sessions">
                        <input type="submit" name="hideuserbutton" value="Hide users">
                    </form>
                </div>
            </div>
        </c:if><br><br>
        
        <c:if test="${not empty selectedSessions}">
            <div>
                <table border="1" cellpadding="5">
                    <caption><h2>${selectedUsername}'s sessions</h2></caption>
                    <tr>
                        <th>ID</th>
                        <th>Start time</th>
                        <th>End time</th>
                    </tr>
                    <c:forEach var="session" items="${selectedSessions}">
                        <tr>
                            <td><c:out value="${session.id}" /></td>
                            <td><c:out value="${session.startTime}" /></td>
                            <td><c:out value="${session.endTime}" /></td>
                        </tr>
                    </c:forEach>
                </table><br>
            </div>
        </c:if><br><br>
        
        <c:if test="${not empty sessions}">
            <div>
                <table border="1" cellpadding="5">
                    <caption><h2>Sessions</h2></caption>
                    <tr>
                        <th>ID</th>
                        <th>Start time</th>
                        <th>End time</th>
                        <th>User</th>
                    </tr>
                    <c:forEach var="session" items="${sessions}">
                        <tr>
                            <td><c:out value="${session.id}" /></td>
                            <td><c:out value="${session.startTime}" /></td>
                            <td><c:out value="${session.endTime}" /></td>
                            <td><c:out value="${session.user.name}" /></td>
                        </tr>
                    </c:forEach>
                </table><br>
                <div>
                    <form action="AdminServlet">
                        Select session (id): <input type="text" name="selectedSessionId" size="20px"><br>
                        <input type="submit" name="showmovementsbutton" value="Show movements">
                        <input type="submit" name="hidesessionbutton" value="Hide sessions">
                    </form>
                </div>
            </div>
        </c:if><br>
    </body>
</html>
