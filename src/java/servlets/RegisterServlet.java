/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import test.HibernateUtil;
import test.TestHelper;

/**
 *
 * @author XUZH0001
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        SessionFactory factory = HibernateUtil.getSessionFactory();
        org.hibernate.Session hibernateSession = factory.openSession();
        test.TestHelper th = new test.TestHelper(hibernateSession);
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (!username.equals("") && !password.equals("")) {
            th.addUser(username, password);
            out.println("<script type=\"text/javascript\">");
            out.println("location='index.jsp';");
            out.println("</script>");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please input correct user name and password');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }
        
        hibernateSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
