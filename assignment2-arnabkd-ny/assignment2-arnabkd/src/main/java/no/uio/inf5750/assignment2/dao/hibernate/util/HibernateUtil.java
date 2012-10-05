package no.uio.inf5750.assignment2.dao.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static SessionFactory sessionFactory;
    
    private static Session session;
    
    static
    {
        Configuration configuration = new Configuration();
        
        configuration.configure(); // Loads hibernate.cfg.xml from classpath
        
        sessionFactory = configuration.buildSessionFactory();
        
        session = sessionFactory.openSession();
        
        // No multi-threading or transaction handling
    }
    
    public static Session getCurrentSession()
    {
        return session;
    }
}
