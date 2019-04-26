
package DAOLayer;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import EntityLayer.Designation;

public class DesignationDao {
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResult;
	
	public DesignationDao()
	{
		try
		{
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","");
			myStatement = (Statement)myConnection.createStatement();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public ArrayList<Designation> getAll(String key)
	{
		ArrayList<Designation> list = new ArrayList<Designation>(); 
		try
		{
			String sql = "select * from designation";
			
			if(!key.equals("")) {
				
				sql = "select * from designation where ID like '%"+key+"%' or Designation_Name like '%"+key+"%' ";
			}
				
			myResult = myStatement.executeQuery(sql);
			
			while(myResult.next())
			{
				Designation d = new Designation ();
				d.ID = myResult.getInt("ID");
				d.Designation_Name = myResult.getString("Designation_Name");
				
				list.add(d); 
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return list;
	}
	
	public boolean Insert(Designation d)
	{
		try
		{
			String sql = "insert into designation values('"+d.ID+"','"+d.Designation_Name+"')";
			int row = myStatement.executeUpdate(sql);
			return row>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
	
	public boolean Update(Designation d)
	{
		try
		{
			String sql = "update designation set ID='"+d.ID+"',Designation_Name='"+d.Designation_Name+"' where ID="+d.ID;
			int row = myStatement.executeUpdate(sql);
			return row>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
	
	public boolean Delete(int id)
	{
		try
		{
			String sql = "delete from designation where ID="+id;
			int row = myStatement.executeUpdate(sql);
			return row>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}

	
	public Designation getByID(int id)
	{
		try
		{
			String sql = "select * from designation where ID = "+id;
			myResult = myStatement.executeQuery(sql);
			
			if(myResult.next())
			{
				Designation d = new Designation();
				d.ID = myResult.getInt("ID");
				d.Designation_Name = myResult.getString("Designation_Name");
				
				
				return d;
			}
			else
			{
				return null;
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
}

