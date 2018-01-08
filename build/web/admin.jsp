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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
        <title>Admin</title>
    </head>
    <body>
    <center>
        <h1 style="margin:20px 0 20px 0">Poor Giraffe Admin :(</h1>
    </center>
    <div style="width:100%">
        <form action="GotoServlet" style="margin-right:268px; text-align:right; margin-bottom:20px">
            <span style="font-size:20px; font-weight:400; color:#007bff; margin-right:10px">
                Admin: ${username}
            </span>
            <span hidden>${userId}</span>
            <span>
                <input name="gotobutton" type="submit" class="btn btn-outline-primary" value="Log out">
            </span>
        </form>
    </div>

    <div class="row" style="width:100%">
        <div class="col-sm-2"></div>
        <div class="col-sm-3" style="margin-bottom:80px">
            <c:if test="${empty users}">
                <img src="resources/allusers.jpg" alt=""/>
                <form action="AdminServlet">
                    <input name="viewallusersbutton" type="submit" class="btn btn-outline-primary"
                           style="margin-top:10px; position:relative; left:130px"
                           value="View all users">
                </form>
            </c:if>
            <c:if test="${not empty users}">
                <div>
                    <table class="table" style="width:400px; font-size:13px">
                        <thead class="thead-light">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Created time</th>
                                <th>Type</th>
                            </tr>
                        </thead>
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
                    </table>
                    <form action="AdminServlet">
                        <div class="form-group">
                            <label style="color:#007bff">Select user (id):</label>
                            <input style="margin-bottom:10px; width:272px" 
                                   type="text" name="selectedUserId" size="20px" class="form-control">
                            <input type="submit" name="showusersessionsbutton" 
                                   class="btn btn-outline-primary" value="Show user sessions">
                            <input type="submit" name="hideuserbutton" 
                                   style="margin-left:6px" class="btn btn-outline-primary" value="Hide users">
                        </div>
                    </form>
                    <c:if test="${not empty selectedSessions}">
                        <div>
                            <table class="table" style="width:400px; font-size:13px">
                                <caption><h6>${selectedUsername}'s sessions</h6></caption>
                                <thead class="thead-light">
                                    <tr>
                                        <th>ID</th>
                                        <th>Start time</th>
                                        <th>End time</th>
                                    </tr>
                                </thead>
                                <c:forEach var="session" items="${selectedSessions}">
                                    <tr>
                                        <td><c:out value="${session.id}" /></td>
                                        <td><c:out value="${session.startTime}" /></td>
                                        <td><c:out value="${session.endTime}" /></td>
                                    </tr>
                                </c:forEach>
                            </table><br>
                        </div>
                    </c:if>
                </div>
            </c:if>
        </div>
        <div style="width:120px"></div>
        <div class="col-sm-3" style="margin-bottom:80px">
            <c:if test="${empty sessions}">
                <img src="resources/sessions.jpg" alt=""/>
                <form action="AdminServlet">
                    <input name="viewallsessionsbutton" type="submit" class="btn btn-outline-primary"
                           style="margin-top:10px; position:relative; left:130px" value="View all sessions">
                </form>
            </c:if>
            <c:if test="${not empty sessions}">
                <div>
                    <table class="table" style="width:400px; font-size:13px">
                        <thead class="thead-light">
                            <tr>
                                <th>ID</th>
                                <th>Start time</th>
                                <th>End time</th>
                                <th>User</th>
                            </tr>
                        </thead>
                        <c:forEach var="session" items="${sessions}">
                            <tr>
                                <td><c:out value="${session.id}" /></td>
                                <td><c:out value="${session.startTime}" /></td>
                                <td><c:out value="${session.endTime}" /></td>
                                <td><c:out value="${session.user.name}" /></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div>
                        <form action="AdminServlet">
                            <div class="form-group">
                                <label style="color:#007bff">Select session (id):</label>
                                <input style="margin-bottom:10px; width:284px" 
                                       type="text" name="selectedSessionId" size="20px" class="form-control">
                                <input type="submit" name="showmovementsbutton" 
                                       class="btn btn-outline-primary" value="Show movements">
                                <input type="submit" name="hidesessionbutton" 
                                       style="margin-left:6px" class="btn btn-outline-primary" value="Hide sessions">
                            </div>
                        </form>
                    </div>
                    <c:if test="${not empty commandList}">
                        <div class="row">
                            <div style="width:282px; border:1px solid #007bff; 
                                 margin: 20px 0 10px 16px; border-radius:5px; padding:10px 5px 10px 5px; color:#ffc107">
                                <c:if test="${not empty mySelectedSessionId}">
                                    <span style="font-size:14px; color:gray">Session ${mySelectedSessionId}:</span><br>
                                </c:if>
                                <c:forEach var="command" items="${commandList}">
                                    ${command.commandType}
                                </c:forEach>
                            </div>
                        </div>
                        <div class="row" style="margin-bottom:80px" text-align="right">
                            <form action="AdminServlet">
                                <input style="margin-left:225px" type="submit" 
                                       class="btn btn-primary" name="replaybutton" value="Replay">
                            </form>
                        </div>
                    </c:if>
                </div>
            </c:if>
        </div>
        <div class="col-sm-1"></div>
    </div>
</body>
</html>
