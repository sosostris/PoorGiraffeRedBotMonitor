/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import test.Command;
import test.Session;

/**
 *
 * @author XUZH0001
 */
public class SessionServlet extends HttpServlet {

    List<Session> mySessions;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int userId = (int) session.getAttribute("userId");
        String sessionbutton = request.getParameter("sessionbutton");
        String selectedSessionId = request.getParameter("selectedSessionId");
        String adminButton = request.getParameter("adminbutton");
        if (adminButton != null) {
            session.setAttribute("userId", userId);
            session.setAttribute("users", null);
            session.setAttribute("sessions", null);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        if (sessionbutton != null) {
            if (sessionbutton.equals("Start session")) {
                test.TestHelper th = new test.TestHelper();
                int newSessionId = th.addSession(userId);
                session.setAttribute("newSessionId", newSessionId);
                RequestDispatcher rd = request.getRequestDispatcher("usersessions.jsp");
                rd.forward(request, response);
            } else if (sessionbutton.equals("End session")) {
                int newSessionId = (int) session.getAttribute("newSessionId");
                test.TestHelper th = new test.TestHelper();
                th.updateSession(newSessionId);
                session.setAttribute("newSessionId", newSessionId);
                RequestDispatcher rd = request.getRequestDispatcher("usersessions.jsp");
                rd.forward(request, response);
            } else if (sessionbutton.equals("Get my sessions")) {
                test.TestHelper th = new test.TestHelper();
                mySessions = th.getSessionsByUserId(userId);
                session.setAttribute("mySessions", mySessions);
                RequestDispatcher rd = request.getRequestDispatcher("usersessions.jsp");
                rd.forward(request, response);
            }
        }
        if (selectedSessionId != null) {
            if (selectedSessionId.matches("\\d+")) {
                test.TestHelper th = new test.TestHelper();
                int mySelectedSessionId = Integer.parseInt(selectedSessionId);
                List<Command> commandList = th.getMovementsBySessionId(mySelectedSessionId);
                session.setAttribute("mySessions", mySessions);
                session.setAttribute("mySelectedSessionId", mySelectedSessionId);
                session.setAttribute("commandList", commandList);
                RequestDispatcher rd = request.getRequestDispatcher("usersessions.jsp");
                rd.forward(request, response);
            }
        }
    }

}
