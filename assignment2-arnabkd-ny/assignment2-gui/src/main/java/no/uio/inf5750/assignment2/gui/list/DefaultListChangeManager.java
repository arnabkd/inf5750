package no.uio.inf5750.assignment2.gui.list;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: DefaultListChangeManager.java 4 2007-08-22 09:44:11Z torgeilo $
 */
public class DefaultListChangeManager
    implements ListChangeManager
{
    private Set<ListChangeListener> listeners = new HashSet<ListChangeListener>();

    // -------------------------------------------------------------------------
    // ListChangeManager implementation
    // -------------------------------------------------------------------------

    public void addListChangeListener( ListChangeListener listener )
    {
        listeners.add( listener );
    }

    public void removeListChangeListener( ListChangeListener listener )
    {
        listeners.remove( listener );
    }

    public void fireListChanged()
    {
        for ( ListChangeListener listener : listeners )
        {
            listener.fireListChanged();
        }
    }
}
