package no.uio.inf5750.assignment2.service.impl;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.DegreeDAO;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.dao.hibernate.HibernateCourseDAO;
import no.uio.inf5750.assignment2.dao.hibernate.HibernateDegreeDAO;
import no.uio.inf5750.assignment2.dao.hibernate.HibernateStudentDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;

@Transactional
public class DefaultStudentSystem implements StudentSystem {
	
	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	StudentDAO studentDAO;
	
	@Autowired
	DegreeDAO degreeDAO;
	
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public void setDegreeDAO(DegreeDAO degreeDAO) {
		this.degreeDAO = degreeDAO;
	}

	@Override
	public void addAttendantToCourse(int courseID, int studentID) {
		//courseDAO.getCourse(courseID).getAttendants().add(studentDAO.getStudent(studentID));
		//og andre veien
	}

	@Override
	public int addCourse(String courseCode, String courseName) {
		return courseDAO.saveCourse(new Course(courseCode, courseName));
	}

	@Override
	public int addDegree(String type) {
		return 0;
	}

	@Override
	public void addDegreeToStudent(int studentId, int degreeId) {
	}

	@Override
	public void addRequiredCourseToDegree(int degreeId, int courseId) {
	}

	@Override
	public int addStudent(String name) {
		return 0;
	}

	@Override
	public void delCourse(int courseId) {
		
	}

	@Override
	public void delDegree(int degreeId) {
		
	}

	@Override
	public void delStudent(int studentId) {
			
	}

	@Override
	public Collection<Course> getAllCourses() {
		return courseDAO.getAllCourses();
	}

	@Override
	public Collection<Degree> getAllDegrees() {
		return null;
	}

	@Override
	public Collection<Student> getAllStudents() {
		return null;
	}

	@Override
	public Course getCourse(int courseId) {
		return courseDAO.getCourse(courseId);
	}

	@Override
	public Course getCourseByCourseCode(String courseCode) {
		return courseDAO.getCourseByCourseCode(courseCode);
	}

	@Override
	public Course getCourseByName(String courseName) {
		return courseDAO.getCourseByName(courseName);
	}

	@Override
	public Degree getDegree(int id) {
		return null;
	}

	@Override
	public Degree getDegreeByType(String type) {
		return null;
	}

	@Override
	public Student getStudent(int id) {
		return null;
	}

	@Override
	public Student getStudentByName(String name) {
		return null;
	}

	@Override
	public void removeAttendantFromCourse(int courseId, int studentId) {		
	}

	@Override
	public void removeDegreeFromStudent(int studentId, int degreeId) {

	}

	@Override
	public void removeRequiredCourseFromDegree(int degreeId, int courseId) {

	}

	@Override
	public boolean studentFulfillsDegreeRequirements(int studentId, int degreeId) {
		return false;
	}

	@Override
	public void updateCourse(int courseId, String courseCode, String courseName) {
		Course course = getCourse(courseId);
		
		if (course == null) {
			System.out.println("Course does not exist");
			return;
		}

	}

	@Override
	public void updateDegree(int degreeId, String type) {

	}

	@Override
	public void updateStudent(int studentId, String name) {

	}

}
