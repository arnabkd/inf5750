package no.uio.inf5750.assignment2.gui.list;

import java.awt.Component;
import java.lang.reflect.Field;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * @author Torgeir Lorange Ostby
 * @version $Id: CellRenderer.java 18 2007-08-22 14:31:27Z torgeilo $
 */
public class CellRenderer
    extends JLabel
    implements ListCellRenderer
{
    private static final String SEP = " - ";

    public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected,
        boolean hasFocus )
    {
        setOpaque( true );

        if ( isSelected )
        {
            setForeground( list.getSelectionForeground() );
            setBackground( list.getSelectionBackground() );
        }
        else
        {
            setForeground( list.getForeground() );
            setBackground( list.getBackground() );
        }

        setFont( list.getFont() );

        String text = "";

        Field[] fields = value.getClass().getDeclaredFields();

        for ( int i = 0; i < fields.length; ++i )
        {
            if ( fields[i].getType().equals( String.class ) )
            {
                fields[i].setAccessible( true );

                try
                {
                    text += SEP + (String) fields[i].get( value );
                }
                catch ( IllegalAccessException e )
                {
                    text = SEP + "ERROR (Torgeir's fault)";
                }

                fields[i].setAccessible( false );
            }
        }

        if ( text.length() != 0 )
        {
            setText( text.substring( SEP.length() ) );
        }
        else
        {
            setText( "ERROR (Empty String value)" );
        }

        return this;
    }
}
