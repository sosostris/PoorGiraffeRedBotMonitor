<%-- 
    Document   : map
    Created on : Jan 6, 2018, 6:01:25 PM
    Author     : XUZH0001
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
        <title>Map</title>
    </head>
    <body>
        <h1 style="position:relative; left:360px; width:400px; margin:20px 0 20px 0">Giraffe RedBot Map</h1>

        <!-- Canvas -->
        <div class="row" style="width:100%">
            <div style="width:5%"></div>
            <div class="col-sm-9">
                <canvas id="giraffeMap" width="1000" height="800" style="background-color:#d9d9d9">    
                </canvas>
            </div>
            <div class="col-sm-2" style="margin-left:30px">
                <div class="row">
                    <c:if test="${not empty username}">
                        <div style="width:100%">
                            <p style="font-size:16px; font-weight:400; color:#ffc107; text-align:right">
                                User: ${username}<br>
                                ID: ${userId}<br>
                                <c:if test="${not empty newSessionId}">
                                    Session ${newSessionId}
                                </c:if>
                            </p>
                        </div>
                    </c:if>
                </div>
                <div class="row">
                    <c:if test="${empty newSessionId || sessionFinished}">
                        <form action="SessionServlet">
                            <input name="sessionbutton" type="submit" class="btn btn-warning" 
                                   style="margin-left: 60px" value="Start session">			
                        </form>
                    </c:if>
                </div>
                <c:if test="${not empty newSessionId && (not sessionFinished || empty sessionFinished)}">

                    <!-- Coordinates -->
                    <div class="coordinates" style="margin-left:24px; margin-bottom:20px">
                        Coordinates: (
                        <span>
                            <c:if test="${not empty coorX}">
                                ${coorX} 
                            </c:if>
                            <c:if test="${empty coorX}">
                                0
                            </c:if>
                        </span>
                        <span>, </span>
                        <span>
                            <c:if test="${not empty coorY}">
                                ${coorY}
                            </c:if>
                            <c:if test="${empty coorY}">
                                0
                            </c:if>
                        </span>
                        )
                    </div>

                    <!--movement buttons-->
                    <form action="MovementServlet">
                        <div class="row">
                            <input name="movementbutton" type="submit" 
                                   class="btn btn-outline-primary" 
                                   style="width:60px; height:60px; position:relative; left:80px"
                                   value="U">
                        </div><br>
                        <div class="row">
                            <input name="movementbutton" type="submit" 
                                   class="btn btn-outline-primary" 
                                   style="width:60px; height:60px"
                                   value="L">
                            <input name="movementbutton" type="submit" 
                                   class="btn btn-outline-primary" 
                                   style="width:60px; height:60px; position:relative; left:100px"
                                   value="R">
                        </div><br>
                        <div class="row">
                            <input name="movementbutton" type="submit" 
                                   class="btn btn-outline-primary" 
                                   style="width:60px; height:60px; position:relative; left:80px"
                                   value="D">
                        </div><br>

                        <div class="row">
                            <input name="movementbutton" type="submit" 
                                   class="btn btn-danger" 
                                   style="border-radius:50px; width:120px; height:40px; position:relative; left:52px"
                                   value="STOP">
                        </div><br>
                    </form>

                    <form action="SessionServlet">
                        <input name="sessionbutton" type="submit" class="btn btn-warning" 
                               style="margin-left:44px; margin-top:20px" value="End session">
                    </form>
                </c:if>

                <c:if test="${not empty newSessionId && sessionFinished}">
                    <div style="border:1px solid #ffc107; margin-top:20px; margin-bottom:20px;
                         border-radius:5px; width:200px; padding:10px 5px 10px 5px; color:#007bff">
                        <c:if test="${not empty commandList}">
                            <span style="font-size:14px; color:gray">Movement list:</span><br>
                            <c:forEach var="command" items="${commandList}">
                                ${command.commandType}
                            </c:forEach>
                        </c:if><br>
                    </div>
                    <form action="MovementServlet">
                        <button class="btn btn-primary" type="submit" name="replayNesSessionButton"
                                style="margin-left:60px; border-radius:50px; width:80px">Replay</button>
                    </form>
                </c:if>
            </div>
        </div>
    </body>
    <script>
        var c = document.getElementById("giraffeMap");
        var x = 500;
        var y = 400;
        var ctx = c.getContext("2d");
        ctx.lineWidth = 1;
        ctx.strokeStyle = '#ffffff';
        for (var i = 1; i < 32; i++) {
            ctx.moveTo(0, 25 * i);
            ctx.lineTo(1000, 25 * i);
            ctx.stroke();
        }
        for (var i = 1; i < 41; i++) {
            ctx.moveTo(i * 25, 0);
            ctx.lineTo(i * 25, 800);
            ctx.stroke();
        }
    </script>
    <c:if test="${not empty x}">
        <script>
            var c = document.getElementById("giraffeMap");
            var ctx = c.getContext("2d");
            var radius = 10;
            ctx.beginPath();
            ctx.arc(${x}, ${y}, radius, 0, 2 * Math.PI, false);
            ctx.fillStyle = 'red';
            ctx.fill();
        </script>
    </c:if>
    <c:if test="${empty x}">
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
