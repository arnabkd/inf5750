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
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public void delCourse(Course course) {
		System.out.println("delete course");
		Session session = sessionFactory.getCurrentSession();
		session.delete(course);
	}

	@Override
	public Collection<Course> getAllCourses() {
		System.out.println("get all courses");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Course.class);
		return (Collection<Course>) criteria.list();		
	}

	@Override
	public Course getCourse(int id) {
		System.out.println("get course");
		Session session = sessionFactory.getCurrentSession();
		return (Course) session.get(Course.class, id);	
	}

	@Override
	public Course getCourseByCourseCode(String courseCode) {
		System.out.println("get course by coursecode");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("courseCode", courseCode));
		
		return (Course) criteria.uniqueResult();
	}

	@Override
	public Course getCourseByName(String courseName) {
		System.out.println("get course by name");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Course.class);
		criteria.add(Restrictions.eq("courseName", courseName));
		
		return (Course) criteria.uniqueResult();
	}

	@Override
	public int saveCourse(Course course) {
		System.out.println("saveCourseaisdnasoidnaosidnaoisndasiodn");
        //Session session = sessionFactory.getCurrentSession();
        //return ((Integer) session.save(course));
		return 1;
	}

}
