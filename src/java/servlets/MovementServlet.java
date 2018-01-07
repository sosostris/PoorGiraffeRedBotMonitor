/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.LEDBean;
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

/**
 *
 * @author XUZH0001
 */
public class MovementServlet extends HttpServlet {
    
    private int currentX = 500;
    private int currentY = 400;
    private int newX;
    private int newY;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        SessionFactory factory = HibernateUtil.getSessionFactory();
        org.hibernate.Session hibernateSession = factory.openSession();
        test.TestHelper th = new test.TestHelper(hibernateSession);

        String movementButton = request.getParameter("movementbutton");
        String replayButton = request.getParameter("replaybutton");
        String replayNewSessionButton = request.getParameter("replayNesSessionButton");

        if (movementButton != null) {
            int newSessionId = (int) session.getAttribute("newSessionId");
            if (movementButton.equals("U")) {
                th.addCommand(newSessionId, "UP");
                MovementBean.moveByUDP("U");
                newY += 25;
                newX = currentX;
            } else if (movementButton.equals("R")) {
                th.addCommand(newSessionId, "RIGHT");
                MovementBean.moveByUDP("R");
            } else if (movementButton.equals("D")) {
                th.addCommand(newSessionId, "DOWN");
                MovementBean.moveByUDP("D");
            } else if (movementButton.equals("L")) {
                th.addCommand(newSessionId, "LEFT");
                MovementBean.moveByUDP("L");
            } else if (movementButton.equals("STOP")) {
                MovementBean.moveByUDP("ST");
            }
            session.setAttribute("newSessionId", newSessionId);
            session.setAttribute("sessionFinished", false);
            session.setAttribute("newX", newX);
            session.setAttribute("newY", newY);
            session.setAttribute("oldX", currentX);
            session.setAttribute("oldY", currentY);
            currentX = newX;
            currentY = newY;
            RequestDispatcher rd = request.getRequestDispatcher("map.jsp");
            rd.forward(request, response);
        }
        if (replayButton != null) {
            int mySelectedSessionId = (int) session.getAttribute("mySelectedSessionId");
            List<Command> commandList = (List<Command>) session.getAttribute("commandList");
            for (Command command : commandList) {
                if (command.getCommandType().equals("UP")) {
                    MovementBean.moveUp();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (command.getCommandType().equals("DOWN")) {
                    MovementBean.moveDown();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (command.getCommandType().equals("LEFT")) {
                    MovementBean.moveLeft();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (command.getCommandType().equals("RIGHT")) {
                    MovementBean.moveRight();
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

        if (replayNewSessionButton != null) {
            int newSessionId = (int) session.getAttribute("newSessionId");
            List<Command> commandList = (List<Command>) session.getAttribute("commandList");
            for (Command command : commandList) {
                if (command.getCommandType().equals("UP")) {
                    MovementBean.moveByUDP("U");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (command.getCommandType().equals("DOWN")) {
                    MovementBean.moveByUDP("D");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (command.getCommandType().equals("LEFT")) {
                    MovementBean.moveByUDP("L");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (command.getCommandType().equals("RIGHT")) {
                    MovementBean.moveByUDP("R");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            MovementBean.moveByUDP("ST");
            session.setAttribute("newSessionId", newSessionId);
            session.setAttribute("sessionFinished", true);
            session.setAttribute("commandList", commandList);
            RequestDispatcher rd = request.getRequestDispatcher("map.jsp");
            rd.forward(request, response);
        }

        hibernateSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
