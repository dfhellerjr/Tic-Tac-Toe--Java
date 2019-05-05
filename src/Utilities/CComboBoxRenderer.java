package Utilities;

//----------------------------------------------------------------------
//Name:  Douglas Heller 
//Class: CComboBoxRenderer 
//----------------------------------------------------------------------


//----------------------------------------------------------------------
//Imports
//----------------------------------------------------------------------
import java.awt.*;
import javax.swing.*;


//----------------------------------------------------------------------
// Name: CComboBoxRenderer
// Abstract: Create a custom ComboBoxRenderer which can be used to 
// identify specified components to be used to modify the
// display attributes of list items in a combo box.
//
// This is a very simple version which was about all I could muster
// given my current level of knowledge in Java. Note: Implementing this 
// required a minor modification to the CComboBox class file
// References:
// http://www.java2s.com/Tutorial/Java/0240__Swing/RenderingJListElements.htm
// docs.oracle.com/javase/7/docs/api/javax/swing/ListCellRenderer.html?
// www-inf.int-evry.fr › ... › Using Swing Components?
// http://www.codejava.net/java-se/swing/create-custom-gui-for-jcombobox
// http://stackoverflow.com/questions/16461454/custom-font-for-jcombobox
//----------------------------------------------------------------------
@SuppressWarnings("serial")
class CComboBoxRenderer extends DefaultListCellRenderer
{
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Properties( never make public )
	// ----------------------------------------------------------------------
	// ---------------------------------------------------------------------- 
	
	
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Methods
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	
	
	// ---------------------------------------------------------------------------------
	// Name:	 CComboBoxRenderer     
	// Abstract: Create a custom ComboBoxRenderer 
	// ---------------------------------------------------------------------------------
	public CComboBoxRenderer( )
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
	
	
	// ---------------------------------------------------------------------------------
	// Name:	 getListCellRendererComponent     
	// Abstract: Get the list component in the combo box to be rendered  
	// ---------------------------------------------------------------------------------
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
			if ( isSelected == true && intIndex != 0 ) 
			{
				setForeground( Color.BLUE );
			}
			
			// Change the font face, style, size & color for the list item @ intIndex(0) if selected
			if( intIndex == 0 && isSelected == true )
			{
				setFont( new Font( "Verdana", Font.ITALIC, 10 ) );
				setForeground( Color.RED );
			}
			
			// Change the font face, style, size, color & background color for the list item @ intIndex(0) 
			// if not selected
			if( intIndex == 0 && isSelected == false )
			{
				setFont( new Font( "Verdana", Font.ITALIC, 10 ) );
				setForeground( Color.RED );
				setBackground( Color.WHITE );
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

