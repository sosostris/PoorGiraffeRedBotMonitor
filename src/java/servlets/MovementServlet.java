/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.LEDBean;
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

/**
 *
 * @author XUZH0001
 */
public class MovementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        
        SessionFactory factory = HibernateUtil.getSessionFactory();
        org.hibernate.Session hibernateSession = factory.openSession();
        test.TestHelper th = new test.TestHelper(hibernateSession);
        
        String movementButton = request.getParameter("movementbutton");
        String replayButton = request.getParameter("replaybutton");
        
        if (movementButton != null) {
            int newSessionId = (int) session.getAttribute("newSessionId");
            if (movementButton.equals("UP")) {
                th.addCommand(newSessionId, "UP");
            } else if (movementButton.equals("RIGHT")) {
                th.addCommand(newSessionId, "RIGHT");
            } else if (movementButton.equals("DOWN")) {
                th.addCommand(newSessionId, "DOWN");
            } else if (movementButton.equals("LEFT")) {
                th.addCommand(newSessionId, "LEFT");
            }
            session.setAttribute("newSessionId", newSessionId);
            RequestDispatcher rd = request.getRequestDispatcher("usersessions.jsp");
                rd.forward(request, response);
        }
        if (replayButton != null) {
            int mySelectedSessionId = (int) session.getAttribute("mySelectedSessionId");
            List<Command> commandList = (List<Command>) session.getAttribute("commandList");
            for (Command command : commandList) {
                if (command.getCommandType().equals("UP") || command.getCommandType().equals("RIGHT")) {
                    LEDBean.turnOnLED();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    LEDBean.turnOffLED();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }
            session.setAttribute("mySelectedSessionId", mySelectedSessionId);
            session.setAttribute("commandList", commandList);
            RequestDispatcher rd = request.getRequestDispatcher("usersessions.jsp");
                rd.forward(request, response);
        }
        hibernateSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
