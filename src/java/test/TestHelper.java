/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author XUZH0001
 */
public class TestHelper {

    org.hibernate.Session session = null;

    public TestHelper() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        this.session = factory.getCurrentSession();
    }

    public void addCommand(int sessionId, String commandType) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.session.beginTransaction();
        System.out.println("Adding new command to session " + sessionId);
        Command command = new Command();
        command.setTimestamp(timestamp);
        command.setCommandType(commandType);
        Session currentSession = getSessionById(sessionId);
        command.setSession(currentSession);
        this.session.save(command);
        System.out.println("Done saving command ... ");
        this.session.getTransaction().commit();
    }

    public void addUser(String username, String password) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.session.beginTransaction();
        System.out.println("Creating new user ... @ " + timestamp);
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setCreatedTime(timestamp);
        user.setType("normal");
        this.session.save(user);
        System.out.println("Done");
        this.session.getTransaction().commit();
    }

    public User getUser(String username, String password) {
        User user = null;
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("name", username));
            criteria.add(Restrictions.eq("password", password));
            user = (User) criteria.uniqueResult();
            if (user != null) {
                System.out.println("User found:");
                System.out.println(user.getId() + " - " + user.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserById(int userId) {
        User user = null;
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("id", userId));
            user = (User) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            this.session.beginTransaction();
            Query q = session.createQuery("from User as user");
            users = (List<User>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public List<Session> getAllSessions() {
        List<Session> sessions = null;
        try {
            this.session.beginTransaction();
            Query q = session.createQuery("from Session as session");
            sessions = (List<Session>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessions;
    }

    private Session getSessionById(int sessionId) {
        Session session = null;
        try {
            Criteria criteria = this.session.createCriteria(Session.class);
            criteria.add(Restrictions.eq("id", sessionId));
            session = (Session) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return session;
    }

    public int addSession(int userId) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.session.beginTransaction();
        System.out.println("Creating new session ... @ " + timestamp);
        Session session = new Session();
        session.setStartTime(timestamp);
        session.setUser(getUserById(userId));
        this.session.save(session);
        System.out.println("Done saving session ... ");
        this.session.getTransaction().commit();
        return session.getId();
    }
    
    public void updateSession(int sessionId) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Updating session ... @ " + timestamp);
        this.session.beginTransaction();
        Session session = getSessionById(sessionId);
        session.setEndTime(timestamp);
        this.session.update(session);
        System.out.println("Done updating session ... ");
        this.session.getTransaction().commit();
    }

    public List getSessionsByUserId(int userId) {
        List<Session> sessionList = null;
        try {
            this.session.beginTransaction();
            Query q = session.createQuery("from Session as session where session.user = " + userId);
            sessionList = (List<Session>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionList;
    }
    
    public List getMovementsBySessionId(int sessionId) {
        List<Command> commandList = null;
        try {
            this.session.beginTransaction();
            Query q = session.createQuery("from Command as command where command.session = " + sessionId + 
                    " order by command.timestamp ASC");
            commandList = (List<Command>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commandList;
    }
    
}
