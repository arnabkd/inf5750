package no.uio.inf5750.assignment2.event;

import java.util.EventListener;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: CloseListener.java 76 2007-09-12 11:29:10Z torgeilo $
 */
public interface CloseListener
    extends EventListener
{
    void close();
}
