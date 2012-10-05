package no.uio.inf5750.assignment2.gui.list;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: ListChangeManager.java 4 2007-08-22 09:44:11Z torgeilo $
 */
public interface ListChangeManager
{
    String ID = ListChangeManager.class.getName();

    void addListChangeListener( ListChangeListener listener );

    void removeListChangeListener( ListChangeListener listener );

    void fireListChanged();
}
