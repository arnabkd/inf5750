package no.uio.inf5750.assignment2.gui.dialog;

import java.util.Set;

import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Degree;
import no.uio.inf5750.assignment2.model.Student;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: DialogManager.java 26 2007-08-22 21:25:21Z torgeilo $
 */
public interface DialogManager
{
    String ID = DialogManager.class.getName();

    void displayErrorMessage( String message );

    SelectDialog createSelectCourseDialog();

    SelectDialog createSelectCourseDialog( Set<Course> excludedCourses );

    SelectDialog createSelectDegreeDialog();

    SelectDialog createSelectDegreeDialog( Set<Degree> excludedDegrees );

    SelectDialog createSelectStudentDialog();

    SelectDialog createSelectStudentDialog( Set<Student> excludedStudents );
}
