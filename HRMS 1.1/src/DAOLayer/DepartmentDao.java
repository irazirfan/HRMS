
package DAOLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import EntityLayer.Department;

public class DepartmentDao {
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResult;
	
	public DepartmentDao()
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
	
	public ArrayList<Department> getAll(String key)
	{
		ArrayList<Department> list = new ArrayList<Department>(); 
		try
		{
			String sql = "select * from department";
			
			if(!key.equals("")) {
				
				sql = "select * from department where ID like '%"+key+"%' or Department_Name like '%"+key+"%'";
			}
				
			myResult = myStatement.executeQuery(sql);
			
			while(myResult.next())
			{
				Department de = new Department ();
				de.ID = myResult.getInt("ID");
				de.Department_Name = myResult.getString("Department_Name");
				
				list.add(de); 
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return list;
	}

	
	public boolean Insert(Department de)
	{
		try
		{
			String sql = "insert into department values("+de.ID+",'"+de.Department_Name+"')";
			int row = myStatement.executeUpdate(sql);
			return row>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
	
	public boolean Update(Department de)
	{
		try
		{
			String sql = "update department set ID="+de.ID+",Department_Name='"+de.Department_Name+"' where ID="+de.ID;
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
			String sql = "delete from department where ID="+id;
			int row = myStatement.executeUpdate(sql);
			return row>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}

	
	public Department getByID(int id)
	{
		try
		{
			String sql = "select * from department where ID = "+id;
			myResult = myStatement.executeQuery(sql);
			
			if(myResult.next())
			{
				Department de = new Department();
				de.ID = myResult.getInt("ID");
				de.Department_Name = myResult.getString("Department_Name");
				
				
				return de;
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

