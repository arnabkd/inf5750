package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.dao.hibernate.util.HibernateUtil;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;

public class HibernateDegreeDAO implements DegreeDAO {

	SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void delDegree(Degree arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Degree> getAllDegrees() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Degree.class);
		return (Collection<Degree>) criteria.list();
	}

	@Override
	public Degree getDegree(int arg0) {
		Collection<Degree> allDegrees = getAllDegrees();
		Degree match = null;

		for (Degree degree: allDegrees) {
			if (degree.getId() == arg0) {
				match = degree;
				break; // we found the student we were looking for
			}
		}

		return match;
	}

	@Override
	public Degree getDegreeByType(String arg0) {
		Collection<Degree> allDegrees = getAllDegrees();
		Degree match = null;

		for (Degree degree: allDegrees) {
			if (degree.getType().equals(arg0)) {
				match = degree;
				break; // we found the student we were looking for
			}
		}

		return match;
	}

	@Override
	public int saveDegree(Degree degree) {
        Session session = sessionFactory.getCurrentSession();        
        //Transaction transaction = session.beginTransaction();        
        int id = (Integer) session.save(degree.getId());        
        //transaction.commit(); // No error handling considered
        
        return id;
	}

}
