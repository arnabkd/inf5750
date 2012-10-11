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
	public void addAttendantToCourse(int courseId, int studentId) {
		Course course = courseDAO.getCourse(courseId);
		Student student = studentDAO.getStudent(studentId);
		
		course.getAttendants().add(student);
		student.getCourses().add(course);
		
		courseDAO.saveCourse(course);
		studentDAO.saveStudent(student);
	}

	@Override
	public int addCourse(String courseCode, String courseName) {
		return courseDAO.saveCourse(new Course(courseCode, courseName));
	}

	@Override
	public int addDegree(String type) {
		return degreeDAO.saveDegree(new Degree(type));
	}

	@Override
	public void addDegreeToStudent(int studentId, int degreeId) {
		Degree degree = degreeDAO.getDegree(degreeId);
		Student student = studentDAO.getStudent(studentId);
		
		student.getDegrees().add(degree);
		studentDAO.saveStudent(student);
	}

	@Override
	public void addRequiredCourseToDegree(int degreeId, int courseId) {
		Degree degree = degreeDAO.getDegree(degreeId);
		Course course = courseDAO.getCourse(courseId);
		
		degree.getRequiredCourses().add(course);
		degreeDAO.saveDegree(degree);
	}

	@Override
	public int addStudent(String name) {
		return studentDAO.saveStudent(new Student(name));
	}

	@Override
	public void delCourse(int courseId) {
		courseDAO.delCourse(courseDAO.getCourse(courseId));
	}

	@Override
	public void delDegree(int degreeId) {
		degreeDAO.delDegree(degreeDAO.getDegree(degreeId));
	}

	@Override
	public void delStudent(int studentId) {
		studentDAO.delStudent(studentDAO.getStudent(studentId));
	}

	@Override
	public Collection<Course> getAllCourses() {
		return courseDAO.getAllCourses();
	}

	@Override
	public Collection<Degree> getAllDegrees() {
		return degreeDAO.getAllDegrees();
	}

	@Override
	public Collection<Student> getAllStudents() {
		return studentDAO.getAllStudents();
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
		return degreeDAO.getDegree(id);
	}

	@Override
	public Degree getDegreeByType(String type) {
		return degreeDAO.getDegreeByType(type);
	}

	@Override
	public Student getStudent(int id) {
		return studentDAO.getStudent(id);
	}

	@Override
	public Student getStudentByName(String name) {
		return studentDAO.getStudentByName(name);
	}

	@Override
	public void removeAttendantFromCourse(int courseId, int studentId) {
		Course course = courseDAO.getCourse(courseId);
		Student student = studentDAO.getStudent(studentId);
		
		course.getAttendants().remove(student);
		student.getCourses().remove(course);
		
		courseDAO.saveCourse(course);
		studentDAO.saveStudent(student);
	}

	@Override
	public void removeDegreeFromStudent(int studentId, int degreeId) {
		studentDAO.getStudent(studentId).getDegrees().remove(degreeDAO.getDegree(degreeId));
	}

	@Override
	public void removeRequiredCourseFromDegree(int degreeId, int courseId) {
		degreeDAO.getDegree(degreeId).getRequiredCourses().remove(courseDAO.getCourse(courseId));
	}

	@Override
	public boolean studentFulfillsDegreeRequirements(int studentId, int degreeId) {
		return studentDAO.getStudent(studentId).getCourses().containsAll(degreeDAO.getDegree(degreeId).getRequiredCourses());
	}

	@Override
	public void updateCourse(int courseId, String courseCode, String courseName) {
		Course course = courseDAO.getCourse(courseId);
		course.setCourseCode(courseCode);
		course.setName(courseName);
		
		courseDAO.saveCourse(course);
	}

	@Override
	public void updateDegree(int degreeId, String type) {
		Degree degree = degreeDAO.getDegree(degreeId);
		degree.setType(type);
		
		degreeDAO.saveDegree(degree);
	}

	@Override
	public void updateStudent(int studentId, String name) {
		Student student = studentDAO.getStudent(studentId);
		student.setName(name);
		
		studentDAO.saveStudent(student);
	}

}
