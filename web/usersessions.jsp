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
        <h1 style="position:relative; left:650px; width:440px; margin:20px 0 20px 0">Poor Giraffe Sessions :(</h1>
        <div class="row" style="width:100%">
            <div class="col-sm-3" style="margin-left:40px;padding-right:0">
                <div class="row">
                    <div style="margin-bottom:10px">
                        <form action="GotoServlet">
                            <input name="gotobutton" type="submit" class="btn btn-outline-warning" value="Log out">
                        </form>
                    </div>
                </div>
                <div class="row">
                    <c:if test="${not empty username}">
                        <div style="margin-bottom:10px;">
                            <p style="font-size:16px; font-weight:400; color:#ffc107">
                                User: ${username}<br>
                                ID: ${userId}<br>
                                <c:if test="${not empty selectedSessionId}">
                                    Session ${selectedSessionId}
                                </c:if>
                            </p>
                        </div>
                    </c:if>
                </div>
                <div class="row">
                    <img src="resources/giraffe-banner1.jpg" alt=""/>
                </div>
                <div class="row">
                    <table class="table" style="width:272px; font-size:13px">
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
                    </table>
                </div>
                <div class="row">
                    <form action="SessionServlet">
                        <div class="form-group">
                            <label style="color:#ffc107">Select session (id):</label>
                            <input style="margin-bottom:10px; width:272px" type="text" name="selectedSessionId" class="form-control" size="20px">
                            <input style="margin-left:142px; font-size:13px" type="submit" 
                                   class="btn btn-warning" value="Show movements">
                        </div>
                    </form>
                </div>
                <c:if test="${not empty mySelectedSessionId}">
                    <div class="row">
                        <div style="width:272px; border:1px solid #ffc107; margin-top:20px; margin-bottom:10px;
                             border-radius:5px; padding:10px 5px 10px 5px; color:#007bff">
                            <c:if test="${not empty mySelectedSessionId}">
                                <span style="font-size:14px; color:gray">Session ${mySelectedSessionId}:</span><br>
                            </c:if>
                            <c:if test="${not empty commandList}">
                                <c:forEach var="command" items="${commandList}">
                                    ${command.commandType}
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="row" style="margin-bottom:80px">
                        <form action="MovementServlet">
                            <input style="margin-left:208px; font-size:13px" type="submit" 
                                   class="btn btn-warning" name="replaybutton" value="Replay"
                                   onclick="moveDot()">
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="col-sm-8" style="padding-left:0">
                <canvas id="giraffeMap" width="950" height="800" style="background-color:#d9d9d9">    
                </canvas>
            </div>
        </div>
    </body>
    <script>
        var x = 475;
        var y = 400;
        var c = document.getElementById("giraffeMap");
        var ctx = c.getContext("2d");
        ctx.lineWidth = 1;
        ctx.strokeStyle = '#ffffff';
        for (var i = 1; i < 33; i++) {
            ctx.moveTo(0, 25 * i);
            ctx.lineTo(950, 25 * i);
            ctx.stroke();
        }
        for (var i = 1; i < 39; i++) {
            ctx.moveTo(i * 25, 0);
            ctx.lineTo(i * 25, 800);
            ctx.stroke();
        }
    </script>
    <c:if test="${empty commandArray}">
        <script>
            var c = document.getElementById("giraffeMap");
            var ctx = c.getContext("2d");
            var radius = 10;
            ctx.beginPath();
            ctx.arc(500, 400, radius, 0, 2 * Math.PI, false);
            ctx.fillStyle = 'red';
            ctx.fill();
        </script>
    </c:if>
</html>
