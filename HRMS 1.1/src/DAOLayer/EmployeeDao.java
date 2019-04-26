
package DAOLayer;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.ArrayList;

	import javax.swing.JOptionPane;

	import EntityLayer.Employee;

	public class EmployeeDao {
		
		private Connection myConnection;
		private Statement myStatement;
		private ResultSet myResult;
		
		public EmployeeDao()
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
		
		public ArrayList<Employee> getAll(String key)
		{
			ArrayList<Employee> list = new ArrayList<Employee>(); 
			try
			{
				String sql = "select * from employee";
				
				if(!key.equals("")) {
					
					sql = "select * from employee where ID like '%"+key+"%' or Name like '%"+key+"%'";
				}
					
				myResult = myStatement.executeQuery(sql);
				
				while(myResult.next())
				{
					Employee e = new Employee();
					e.ID = myResult.getInt("ID");
					e.Name = myResult.getString("Name");
					e.JoiningDate = myResult.getString("JoiningDate");
					e.Contact = myResult.getString("Contact");
					e.Address = myResult.getString("Address");
					e.BasicSalary = myResult.getDouble("BasicSalary");
					e.HouseAllowence = myResult.getDouble("HouseAllowence");
					e.Conveyance = myResult.getDouble("Conveyance");
					e.DepartmentID = myResult.getInt("DepartmentID");
					e.DesignetionID=myResult.getInt("DesignetionID");
					
					list.add(e); 
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			return list;
		}

		public boolean Insert(Employee e)
		{
			try
			{
				String sql = "insert into employee values("+e.ID+",'"+e.Name+"','"+e.JoiningDate+"','"+e.Contact+"','"+e.Address+"',"+e.BasicSalary+","+e.HouseAllowence+","+e.Conveyance+","+e.DepartmentID+","+e.DesignetionID+")";
				int row = myStatement.executeUpdate(sql);
				return row>0;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		}
		
		public boolean Update(Employee e)
		{
			try
			{
				String sql = "update employee set ID="+e.ID+",Name='"+e.Name+"',JoiningDate='"+e.JoiningDate+"',Contact='"+e.Contact+"',Address='"+e.Address+"',BasicSalary="+e.BasicSalary+",HouseAllowence="+e.HouseAllowence+",Conveyance="+e.Conveyance+",DepartmentID="+e.DepartmentID+",DesignetionID="+e.DesignetionID+" where ID="+e.ID;
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
				String sql = "delete from employee where ID="+id;
				int row = myStatement.executeUpdate(sql);
				return row>0;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		}

		
		public Employee getByID(int id)
		{
			try
			{
				String sql = "select * from employee where ID = "+id;
				myResult = myStatement.executeQuery(sql);
				
				if(myResult.next())
				{
					Employee e = new Employee();
					e.ID = myResult.getInt("ID");
					e.Name = myResult.getString("Name");
					e.JoiningDate = myResult.getString("JoiningDate");
					e.Contact = myResult.getString("Contact");
					e.Address = myResult.getString("Address");
					e.BasicSalary = myResult.getDouble("BasicSalary");
					e.HouseAllowence = myResult.getDouble("HouseAllowence");
					e.Conveyance = myResult.getDouble("Conveyance");
					e.DepartmentID = myResult.getInt("DepartmentID");
					e.DesignetionID=myResult.getInt("DesignetionID");
					
					return e;
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
