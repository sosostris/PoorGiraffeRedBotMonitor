/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import test.HibernateUtil;
import test.Session;

/**
 *
 * @author XUZH0001
 */
public class GotoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        SessionFactory factory = HibernateUtil.getSessionFactory();
        org.hibernate.Session hibernateSession = factory.openSession();
        test.TestHelper th = new test.TestHelper(hibernateSession);
        
        String username = (String) session.getAttribute("username");
        int userId = (int) session.getAttribute("userId");
        String gotoButton = request.getParameter("gotobutton");
        
        if (gotoButton.equals("Giraffe Redbot Monitor")) {
            session.setAttribute("username", username);
            session.setAttribute("userId", userId);
            RequestDispatcher rd = request.getRequestDispatcher("map.jsp");
            rd.forward(request, response);
        } else if (gotoButton.equals("View my sessions")) {
            session.setAttribute("username", username);
            session.setAttribute("userId", userId);
            List<Session> mySessions = th.getSessionsByUserId(userId);
            session.setAttribute("mySessions", mySessions);
            RequestDispatcher rd = request.getRequestDispatcher("usersessions.jsp");
            rd.forward(request, response);
        } else if (gotoButton.equals("Log out")) {
            session.setAttribute("username", null);
            session.setAttribute("newUser", false);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else if (gotoButton.equals("Admin console")) {
            session.setAttribute("userId", userId);
            session.setAttribute("users", null);
            session.setAttribute("sessions", null);
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
        }
        
        hibernateSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
