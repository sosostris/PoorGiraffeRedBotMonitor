/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import test.HibernateUtil;
import test.TestHelper;
import test.User;

/**
 *
 * @author XUZH0001
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        SessionFactory factory = HibernateUtil.getSessionFactory();
        org.hibernate.Session hibernateSession = factory.openSession();
        test.TestHelper th = new test.TestHelper(hibernateSession);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginButton = request.getParameter("loginbutton");

        if (loginButton != null) {
            if (loginButton.equals("Login")) {
                if (!username.equals("") && !password.equals("")) {
                    User user = th.getUser(username, password);
                    if (user != null) {
                        String myUsername = user.getName();
                        session.setAttribute("username", myUsername);
                        session.setAttribute("userId", user.getId());
                        session.setAttribute("sessionId", null);
                        session.setAttribute("newUser", false);
                        if (user.getType().equals("admin")) {
                            session.setAttribute("isAdmin", true);
                        }
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        rd.forward(request, response);
                    } else {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Please input correct user name and password');\n"
                                + "User name: " + username + "\nPassword: " + password);
                        out.println("location='index.jsp';");
                        out.println("</script>");
                    }
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User name or password wrong! Please input again!');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                }
            } else if (loginButton.equals("Register")) {
                if (!username.equals("") && !password.equals("")) {
                    th.addUser(username, password);
                    session.setAttribute("newUser", true);
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Please input correct user name and password');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                }
            }

        }

        hibernateSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
