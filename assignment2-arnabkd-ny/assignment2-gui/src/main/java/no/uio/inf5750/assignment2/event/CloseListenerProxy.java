package no.uio.inf5750.assignment2.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: CloseListenerProxy.java 76 2007-09-12 11:29:10Z torgeilo $
 */
public class CloseListenerProxy
    implements CloseListener
{
    private static final Log LOG = LogFactory.getLog( CloseListenerProxy.class );

    private Object subject;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    public CloseListenerProxy( Object subject )
    {
        this.subject = subject;
    }

    // -------------------------------------------------------------------------
    // CloseListener implementation
    // -------------------------------------------------------------------------

    public void close()
    {
        Method closeMethod;

        try
        {
            closeMethod = subject.getClass().getMethod( "close", new Class[] {} );
        }
        catch ( NoSuchMethodException e )
        {
            LOG.error( "Subject doesn't have a close() method. Ignoring: " + subject );

            return;
        }

        try
        {
            closeMethod.invoke( subject, new Object[] {} );
        }
        catch ( IllegalAccessException e )
        {
            LOG.error( "Unable to close subject: " + subject, e );
        }
        catch ( InvocationTargetException e )
        {
            LOG.error( "Unable to close subject: " + subject, e );
        }
    }
}
