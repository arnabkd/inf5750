package no.uio.inf5750.assignment2.gui;

import javax.swing.JFrame;

import no.uio.inf5750.assignment2.event.CloseListener;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: UserInterface.java 76 2007-09-12 11:29:10Z torgeilo $
 */
public interface UserInterface
{
    void initialize();

    JFrame getMainFrame();

    void addCloseListener( CloseListener closeListener );

    void removeCloseListener( CloseListener closeListener );
}
