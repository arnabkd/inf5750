package no.uio.inf5750.assignment2.util.comparator;

import java.util.Comparator;

import no.uio.inf5750.assignment2.model.Degree;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: DegreeTypeComparator.java 4 2007-08-22 09:44:11Z torgeilo $
 */
public class DegreeTypeComparator
    implements Comparator<Degree>
{
    public int compare( Degree degreeA, Degree degreeB )
    {
        return degreeA.getType().compareTo( degreeB.getType() );
    }
}
