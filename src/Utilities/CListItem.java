package Utilities;
// ----------------------------------------------------------------------
// Name: Pat Callahan
// Class: CListItem
// ----------------------------------------------------------------------


// ----------------------------------------------------------------------
// Imports
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------
// Name:     CListItem
// Abstract: Hold a name and a value
// ----------------------------------------------------------------------
public class CListItem
{
	
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Constants
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------


	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Properties( never make public )
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	private int    m_intValue = 0;
	private String m_strName  = "";
	

	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// Methods
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------


	// ----------------------------------------------------------------------
	// Name: CListItem
	// Abstract: Constructor (default)
	// ----------------------------------------------------------------------
	public CListItem( )
	{
		this( 0, "" );

		try
		{	
		
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
	}
	

	// ----------------------------------------------------------------------
	// Name:     CListItem
	// Abstract: Constructor (parameterized)
	// ----------------------------------------------------------------------
	public CListItem( int intValue, String strName )
	{
		try
		{	
			Initialize( intValue, strName );
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}
	

	// ----------------------------------------------------------------------
	// Name:     Initialize
	// Abstract: Initialize all class level properties
	// ----------------------------------------------------------------------
	public void Initialize( int intValue, String strName )
	{
		try
		{
			// Call set methods
			SetValue( intValue );
			SetName( strName );	
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}
	
	
	// ----------------------------------------------------------------------
	// Name:     SetValue
	// Abstract: Set the value
	// ----------------------------------------------------------------------
	public void SetValue( int intValue )
	{
		try
		{	
			m_intValue = intValue;
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}

	// ----------------------------------------------------------------------
	// Name:     GetValue
	// Abstract: Get the value
	// ----------------------------------------------------------------------
	public int GetValue( )
	{
		try
		{	

		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}

		return m_intValue;
	}



	// ----------------------------------------------------------------------
	// Name:     SetName
	// Abstract: Set the Name
	// ----------------------------------------------------------------------
	public void SetName( String strName )
	{
		try
		{	
			m_strName = strName;
		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
	}

	// ----------------------------------------------------------------------
	// Name:     GetName
	// Abstract: Get the Name
	// ----------------------------------------------------------------------
	public String GetName( )
	{
		try
		{	

		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}
		
		return m_strName;
	}
	
		
	// ----------------------------------------------------------------------
	// Name:     toString
	// Abstract: Get the Name
	// ----------------------------------------------------------------------
	public String toString( )
	{
		try
		{	

		}
		catch( Exception expError )
		{
			// Display Error Message
			CUtilities.WriteLog( expError );
		}

		// Optional additional output variable
		return m_strName;     // + "(" + m_intValue + ")"     
	}
}

