
package DAOLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import EntityLayer.Users;

public class UserDao {
	
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResult;
	
	public UserDao()
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
	
	public ArrayList<Users> getAll(String key)
	{
		ArrayList<Users> list = new ArrayList<Users>(); 
		try
		{
			String sql = "select * from users";
			
			if(!key.equals("")) {
				
				sql = "select * from users where ID like '%"+key+"%' or Name like '%"+key+"%' or UserName like '%"+key+"%' ";
			}
				
			myResult = myStatement.executeQuery(sql);
			
			while(myResult.next())
			{
				Users u = new Users();
				u.ID = myResult.getInt("ID");
				u.Name = myResult.getString("Name");
				u.UserName = myResult.getString("UserName");
				u.Pass = myResult.getString("Pass");
				u.UserType = myResult.getString("UserType");
				
				list.add(u); 
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return list;
	}

	
	public Users Login(String userName,String passWord)
	{
		try
		{
			String sql = "select * from users where Username='"+userName+"' and Pass='"+passWord+"'";
			myResult = myStatement.executeQuery(sql);
			
			if(myResult.next())
			{
				Users u = new Users();
				u.ID = myResult.getInt("ID");
				u.Name = myResult.getString("Name");
				u.UserName = myResult.getString("UserName");
				u.Pass = myResult.getString("Pass");
				u.UserType = myResult.getString("UserType");
				
				return u;
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
	
	public boolean Insert(Users u)
	{
		try
		{
			String sql = "insert into users values('"+u.ID+"','"+u.Name+"','"+u.UserName+"','"+u.Pass+"','"+u.UserType+"')";
			int row = myStatement.executeUpdate(sql);
			return row>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}
	
	public boolean Update(Users u)
	{
		try
		{
			String sql = "update users set Name='"+u.Name+"',UserName='"+u.UserName+"',Pass='"+u.Pass+"',UserType='"+u.UserType+"' where ID="+u.ID;
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
			String sql = "delete from users where ID="+id;
			int row = myStatement.executeUpdate(sql);
			return row>0;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return false;
		}
	}

	
	public Users getByID(int id)
	{
		try
		{
			String sql = "select * from users where ID = "+id;
			myResult = myStatement.executeQuery(sql);
			
			if(myResult.next())
			{
				Users u = new Users();
				u.ID = myResult.getInt("ID");
				u.Name = myResult.getString("Name");
				u.UserName = myResult.getString("UserName");
				u.Pass = myResult.getString("Pass");
				u.UserType = myResult.getString("UserType");
				
				return u;
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
	
	public String getByID(String id)
	{
		try
		{
			String sql = "select * from users where ID = "+id;
			myResult = myStatement.executeQuery(sql);
			
			if(myResult.next())
			{
				Users u = new Users();
				u.ID = myResult.getInt("ID");
				u.Name = myResult.getString("Name");
				u.UserName = myResult.getString("UserName");
				u.Pass = myResult.getString("Pass");
				u.UserType = myResult.getString("UserType");
				
				return u.UserType;
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
