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
		Session session = HibernateUtil.getCurrentSession();
		Criteria criteria = session.createCriteria(Student.class);
		return (Collection<Student>) criteria.list();		
	}

	@Override
	public Student getStudent(int arg0) {
		Collection<Student> allStudents = getAllStudents();
		Student match = null;
		
		for (Student student: allStudents){
			if (student.getId() == arg0) {
				match = student;
				break; //we found the student we were looking for
			}
		}
		
		return match;
	}

	@Override
	public Student getStudentByName(String name) {
		/*Collection<Student> allStudents = getAllStudents();
		Student match = null;
		
		for (Student student: allStudents){
			if (student.getName().equals(arg0)) {
				match = student;
				break; //we found the student we were looking for
			}
		}
		
		return match; */
        Session session = sessionFactory.getCurrentSession();
        
        Criteria criteria = session.createCriteria( Student.class );
        
        criteria.add( Restrictions.eq( "name", name ) );

        return (Student) criteria.uniqueResult();
	}

	@Override
	public int saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();        
        //Transaction transaction = session.beginTransaction();        
        int id = (Integer) session.save(student.getId());        
        //transaction.commit(); // No error handling considered
        
        
        return id;
	}

	@Override
	public void delStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

}
