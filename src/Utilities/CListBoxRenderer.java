package Utilities;

//----------------------------------------------------------------------------------
//Name:  Douglas Heller 
//Class: CListBoxRenderer 
//----------------------------------------------------------------------------------


//----------------------------------------------------------------------------------
//Imports
//----------------------------------------------------------------------------------
import java.awt.*;
import javax.swing.*;


@SuppressWarnings("serial")
//----------------------------------------------------------------------------------
//Name:     CListBoxRenderer
//Abstract: Create a custom ListBoxRenderer which can be used to identify 
// specified components to be used in modifying the display attributes of items
// in a list box.
//
// This is a very simple version.
// Note: Implementing this required a minor modification to the CListBox class file   
// References: (see CComboBoxRenderer)
// ---------------------------------------------------------------------------------
class CListBoxRenderer extends DefaultListCellRenderer
{
	// -----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------
	// Properties( never make public )
	// -----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------- 
	
	
	// -----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------
	
	
	// -----------------------------------------------------------------------------
	// Name:	 CListBoxRenderer     
	// Abstract: Create a custom ListBoxRenderer 
	// -----------------------------------------------------------------------------
	public CListBoxRenderer( )
	{
		try
		{
		
		}
		catch( Exception expError )
		{
			// Log Error
			CUtilities.WriteLog( expError );
		}
	
	}
	
	
	// -----------------------------------------------------------------------------
	// Name:	 getListCellRendererComponent     
	// Abstract: Get the list component in the combo box to be rendered  
	// -----------------------------------------------------------------------------
	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent( JList list,            // The list 
												   Object value,          // value to display
												   int intIndex,          // cell index
												   boolean isSelected,    // is the cell selected
												   boolean cellHasFocus ) // the list and the cell have the focus
	{
		try
		{
			super.getListCellRendererComponent( list, value, intIndex, isSelected, cellHasFocus );
			
			// Set the text color to blue if the list item is selected
			if ( isSelected == true ) 
			{
				setForeground( Color.BLUE );
			}
		}
		catch( Exception expError )
		{
			// Log Error 
			CUtilities.WriteLog( expError );
		}
		
		return this;
	}
}

