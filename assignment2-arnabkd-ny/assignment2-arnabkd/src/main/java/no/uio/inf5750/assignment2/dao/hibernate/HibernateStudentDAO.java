package no.uio.inf5750.assignment2.dao.hibernate;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.dao.hibernate.util.HibernateUtil;
import no.uio.inf5750.assignment2.model.Student;

public class HibernateStudentDAO implements StudentDAO{


	SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Collection<Student> getAllStudents() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Student.class);
		return (Collection<Student>) criteria.list();		
	}

	@Override
	public Student getStudent(int id) {
        Session session = sessionFactory.getCurrentSession();        
        Criteria criteria = session.createCriteria( Student.class );        
        criteria.add( Restrictions.eq("id", id));
        return (Student) criteria.uniqueResult();
	}

	@Override
	public Student getStudentByName(String name) {
        Session session = sessionFactory.getCurrentSession();        
        Criteria criteria = session.createCriteria( Student.class );        
        criteria.add( Restrictions.eq("name", name));
        return (Student) criteria.uniqueResult();
	}

	@Override
	public int saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();        
        return (Integer) session.save(student);
	}

	@Override
	public void delStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(student);
	}

}
