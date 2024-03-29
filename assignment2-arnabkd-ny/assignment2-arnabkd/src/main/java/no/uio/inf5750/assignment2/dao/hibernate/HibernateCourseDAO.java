package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.hibernate.util.HibernateUtil;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;


public class HibernateCourseDAO implements CourseDAO{

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public void delCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Course> getAllCourses() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Course.class);
		return (Collection<Course>) criteria.list();		
	}

	@Override
	public Course getCourse(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Course) session.get(Course.class, id);	
	}

	@Override
	public Course getCourseByCourseCode(String courseCode) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("courseCode", courseCode));
		return (Course) criteria.uniqueResult();
	}

	@Override
	public Course getCourseByName(String courseName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("name", courseName));
		
		return (Course) criteria.uniqueResult();
	}

	@Override
	public int saveCourse(Course course) {		
	    Session session = sessionFactory.getCurrentSession();
	    session.saveOrUpdate(course);
	    return course.getId();
	}

}
