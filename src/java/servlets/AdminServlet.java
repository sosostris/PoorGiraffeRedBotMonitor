/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.MovementBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import test.Command;
import test.HibernateUtil;
import test.Session;
import test.User;

/**
 *
 * @author XUZH0001
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int userId = (int) session.getAttribute("userId");
        String veiwAllUsersButton = request.getParameter("viewallusersbutton");
        String viewAllSessionsButton = request.getParameter("viewallsessionsbutton");
        String hideUserButton = request.getParameter("hideuserbutton");
        String hideSessionButton = request.getParameter("hidesessionbutton");
        String showUserSessionsButton = request.getParameter("showusersessionsbutton");
        String showMovementsButton = request.getParameter("showmovementsbutton");
        String replayButton = request.getParameter("replaybutton");
        
        // let all requests use its own session and close the session at the end of the request
        SessionFactory factory = HibernateUtil.getSessionFactory();
        org.hibernate.Session hibernateSession = factory.openSession();
        test.TestHelper th = new test.TestHelper(factory.openSession());
        
        if (hideSessionButton != null) {
            session.setAttribute("sessions", null);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        if (hideUserButton != null) {
            session.setAttribute("users", null);
            session.setAttribute("selectedSessions", null);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        if (veiwAllUsersButton != null) {
            List<User> users = th.getAllUsers();
            session.setAttribute("users", users);
            session.setAttribute("commandList", null);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        if (viewAllSessionsButton != null) {
            List<Session> sessions = th.getAllSessions();
            session.setAttribute("sessions", sessions);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        if (showUserSessionsButton != null) {
            int selectedUserId = Integer.parseInt(request.getParameter("selectedUserId"));
            List<Session> selectedSessions = th.getSessionsByUserId(selectedUserId);
            session.setAttribute("selectedSessions", selectedSessions);
            session.setAttribute("selectedUsername", th.getUserById(selectedUserId).getName());
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        if (showMovementsButton != null) {
            int selectedSessionId = Integer.parseInt(request.getParameter("selectedSessionId"));
            List<Command> commandList = th.getMovementsBySessionId(selectedSessionId);
            session.setAttribute("commandList", commandList);
            session.setAttribute("mySelectedSessionId", selectedSessionId);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        if (replayButton != null) {
            MovementBean.reset();
            int selectedSessionId = (int) session.getAttribute("mySelectedSessionId");
            List<Command> commandList = (List<Command>) session.getAttribute("commandList");
            for (Command command : commandList) {
                if (command.getCommandType().equals("UP")) {
                    MovementBean.moveByUDP("U");
                } else if (command.getCommandType().equals("DOWN")) {
                    MovementBean.moveByUDP("D");
                } else if (command.getCommandType().equals("LEFT")) {
                    MovementBean.moveByUDP("L");
                } else if (command.getCommandType().equals("RIGHT")) {
                    MovementBean.moveByUDP("R");
                }
                try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            MovementBean.moveByUDP("ST");
            session.setAttribute("mySelectedSessionId", selectedSessionId);
            session.setAttribute("commandList", commandList);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        
        hibernateSession.close();
    }

}
