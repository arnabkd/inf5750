package no.uio.inf5750.assignment2.util.comparator;

import java.util.Comparator;

import no.uio.inf5750.assignment2.model.Course;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: CourseCodeComparator.java 4 2007-08-22 09:44:11Z torgeilo $
 */
public class CourseCodeComparator
    implements Comparator<Course>
{
    public int compare( Course courseA, Course courseB )
    {
        return courseA.getCourseCode().compareTo( courseB.getCourseCode() );
    }
}
