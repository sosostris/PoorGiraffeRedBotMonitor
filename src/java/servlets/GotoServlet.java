/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author XUZH0001
 */
public class GotoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        
        String username = (String) session.getAttribute("username");
        int userId = (int) session.getAttribute("userId");
        String gotoButton = request.getParameter("gotobutton");
        
        if (gotoButton.equals("Giraffe Redbot Monitor")) {
            session.setAttribute("username", username);
            session.setAttribute("userId", userId);
            RequestDispatcher rd = request.getRequestDispatcher("map.jsp");
            rd.forward(request, response);
        }
        
        if (gotoButton.equals("View my sessions")) {
            session.setAttribute("username", username);
            session.setAttribute("userId", userId);
            RequestDispatcher rd = request.getRequestDispatcher("usersessions.jsp");
            rd.forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
