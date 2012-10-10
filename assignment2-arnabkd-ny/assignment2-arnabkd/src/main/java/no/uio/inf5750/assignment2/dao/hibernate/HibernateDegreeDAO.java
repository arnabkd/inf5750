package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
	public void delDegree(Degree degree) {
		Session session = sessionFactory.getCurrentSession();
		if(session.get(Course.class, degree.getId()) != null)
			session.delete(degree);

	}

	@Override
	public Collection<Degree> getAllDegrees() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Degree.class);
		return (Collection<Degree>) criteria.list();
	}

	@Override
	public Degree getDegree(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Degree.class);
		criteria.add(Restrictions.eq("id", id));
		
		return (Degree) criteria.uniqueResult();
	}

	@Override
	public Degree getDegreeByType(String type) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Degree.class);
		criteria.add(Restrictions.eq("type", type));
		
		return (Degree) criteria.uniqueResult();
	}

	@Override
	public int saveDegree(Degree degree) {
        Session session = sessionFactory.getCurrentSession();        
        return (Integer) session.save(degree);      
	}

}
