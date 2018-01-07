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

/**
 *
 * @author XUZH0001
 */
public class MovementServlet extends HttpServlet {
    
    private int x = 500;
    private int y = 400;
    
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
            getPositions(th, newSessionId);
            session.setAttribute("x", x);
            session.setAttribute("y", y);
            session.setAttribute("sessionFinished", false);
            RequestDispatcher rd = request.getRequestDispatcher("map.jsp");
            rd.forward(request, response);
        }
        if (replayButton != null) {
            int mySelectedSessionId = (int) session.getAttribute("mySelectedSessionId");
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
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovementServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            MovementBean.moveByUDP("ST");
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
                } else if (command.getCommandType().equals("DOWN")) {
                    MovementBean.moveByUDP("D");
                } else if (command.getCommandType().equals("LEFT")) {
                    MovementBean.moveByUDP("L");
                } else if (command.getCommandType().equals("RIGHT")) {
                    MovementBean.moveByUDP("R");
                }
                for (int i=0; i<20; i++) {
                    if (!MovementBean.queryDistance()) {
                        MovementBean.stop();
                        break;
                    }
                    try {
                        Thread.sleep(50);
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
    
    private void getPositions(test.TestHelper th, int newSessionId) {
        x = 500;
        y = 400;
        List<Command> newCommandList = th.getMovementsBySessionId(newSessionId);
        for (int i=0; i<newCommandList.size(); i++) {
            String commandType = newCommandList.get(i).getCommandType();
            if (commandType.equals("UP")) {
                y = y - 25;
            } else if (commandType.equals("DOWN")) {
                y = y + 25;
            } else if (commandType.equals("LEFT")) {
                x = x - 25;
            } else if (commandType.equals("RIGHT")) {
                x = x + 25;
            }
        }
    }

}
