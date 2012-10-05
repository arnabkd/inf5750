package no.uio.inf5750.assignment2.util.comparator;

import java.util.Comparator;

import no.uio.inf5750.assignment2.model.Student;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: StudentNameComparator.java 4 2007-08-22 09:44:11Z torgeilo $
 */
public class StudentNameComparator
    implements Comparator<Student>
{
    public int compare( Student studentA, Student studentB )
    {
        return studentA.getName().compareTo( studentB.getName() );
    }
}
