package no.uio.inf5750.assignment2.gui.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import no.uio.inf5750.assignment2.gui.UserInterface;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.service.StudentSystem;
import no.uio.inf5750.assignment2.util.comparator.CourseCodeComparator;
import no.uio.inf5750.assignment2.util.comparator.DegreeTypeComparator;
import no.uio.inf5750.assignment2.util.comparator.StudentNameComparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: DefaultDialogManager.java 28 2007-08-23 11:06:31Z torgeilo $
 */
public class DefaultDialogManager
    implements DialogManager
{
    private static final Log LOG = LogFactory.getLog( DefaultDialogManager.class );

    // -------------------------------------------------------------------------
    // Dependencies
    // -------------------------------------------------------------------------

    private UserInterface userInterface;

    public void setUserInterface( UserInterface userInterface )
    {
        this.userInterface = userInterface;
    }

    private StudentSystem service;

    public void setService( StudentSystem service )
    {
        this.service = service;
    }

    // -------------------------------------------------------------------------
    // DialogManager implementation
    // -------------------------------------------------------------------------

    public void displayErrorMessage( String message )
    {
        JOptionPane.showMessageDialog( userInterface.getMainFrame(), message, "Error", JOptionPane.ERROR_MESSAGE );
    }

    public SelectDialog createSelectCourseDialog()
    {
        return createSelectCourseDialog( new HashSet<Course>( 0 ) );
    }

    public SelectDialog createSelectCourseDialog( Set<Course> excludedCourses )
    {
        List<Course> courses = new ArrayList<Course>();

        try
        {
            courses = new ArrayList<Course>( service.getAllCourses() );
        }
        catch ( RuntimeException e )
        {
            LOG.error( "Failed to get all courses", e );

            displayErrorMessage( "Failed to get all courses: " + e.getMessage() );
        }

        courses.removeAll( excludedCourses );

        Collections.sort( courses, new CourseCodeComparator() );

        return new SelectDialog( userInterface.getMainFrame(), "Select course", courses );
    }

    public SelectDialog createSelectDegreeDialog()
    {
        return createSelectDegreeDialog( new HashSet<Degree>( 0 ) );
    }

    public SelectDialog createSelectDegreeDialog( Set<Degree> excludedDegrees )
    {
        List<Degree> degrees = new ArrayList<Degree>();

        try
        {
            degrees = new ArrayList<Degree>( service.getAllDegrees() );
        }
        catch ( RuntimeException e )
        {
            LOG.error( "Failed to get all degrees", e );

            displayErrorMessage( "Failed to get all degrees: " + e.getMessage() );
        }

        degrees.removeAll( excludedDegrees );

        Collections.sort( degrees, new DegreeTypeComparator() );

        return new SelectDialog( userInterface.getMainFrame(), "Select degree", degrees );
    }

    public SelectDialog createSelectStudentDialog()
    {
        return createSelectStudentDialog( new HashSet<Student>( 0 ) );
    }

    public SelectDialog createSelectStudentDialog( Set<Student> excludedStudents )
    {
        List<Student> students = new ArrayList<Student>();

        try
        {
            students = new ArrayList<Student>( service.getAllStudents() );
        }
        catch ( RuntimeException e )
        {
            LOG.error( "Failed to get all students", e );

            displayErrorMessage( "Failed to get all students: " + e.getMessage() );
        }

        students.removeAll( excludedStudents );

        Collections.sort( students, new StudentNameComparator() );

        return new SelectDialog( userInterface.getMainFrame(), "Select student", students );
    }
}
