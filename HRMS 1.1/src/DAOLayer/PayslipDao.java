
package DAOLayer;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.ArrayList;

	import javax.swing.JOptionPane;

	import EntityLayer.Payslip;
import HelperLayer.LoginHelper;

	public class PayslipDao {
		
		private Connection myConnection;
		private Statement myStatement;
		private ResultSet myResult;
		
		public PayslipDao()
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
		
		public ArrayList<Payslip> getAll(String key)
		{
			ArrayList<Payslip> list = new ArrayList<Payslip>(); 
			try
			{
				String sql = "select * from payslip";
				
				if(!key.equals("")) {
					
					sql = "select * from payslip where ID like '%"+key+"%'";
				}
					
				myResult = myStatement.executeQuery(sql);
				
				while(myResult.next())
				{
					Payslip p = new Payslip();
					p.ID = myResult.getInt("ID");
					p.Employee_ID = myResult.getInt("Employee_ID");
					p.Payroll_ID = myResult.getInt("Payroll_ID");
					p.Basic_Salary = myResult.getDouble("Basic_Salary");
					p.House_Allowence = myResult.getDouble("House_Allowence");
					p.Conveyance = myResult.getDouble("Conveyance");
					p.Addition = myResult.getDouble("Addition");
					p.Deduction = myResult.getDouble("Deduction");
					
					
					list.add(p);
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			return list;
		}

		public boolean Insert(Payslip p)
		{
			try
			{
				
				String sql = "insert into payslip values("+p.ID+",'"+p.Employee_ID+"','"+p.Payroll_ID+"','"+p.Basic_Salary+"',"+p.House_Allowence+","+p.Conveyance+","+p.Addition+","+p.Deduction+")";
				int row = myStatement.executeUpdate(sql);
				return row>0;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		}
		
		public boolean Update(Payslip p)
		{
			try
			{
				String sql = "update payslip set ID="+p.ID+",Employee_id='"+p.Employee_ID+"',Payroll_ID='"+p.Payroll_ID+"',Basic_Salary="+p.Basic_Salary+",House_Allowence="+p.House_Allowence+",Conveyance="+p.Conveyance+",Addition="+p.Addition+",Deduction="+p.Deduction+" where ID="+p.ID;
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
				String sql = "delete from payslip where ID="+id;
				int row = myStatement.executeUpdate(sql);
				return row>0;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		}

		
		public Payslip getByID(int id)
		{
			try
			{
				String sql = "select * from payslip where ID = "+id;
				myResult = myStatement.executeQuery(sql);
				
				if(myResult.next())
				{
					Payslip p = new Payslip();
					p.ID = myResult.getInt("ID");
					p.Employee_ID = myResult.getInt("Employee_ID");
					p.Payroll_ID = myResult.getInt("Payroll_ID");
					p.Basic_Salary = myResult.getDouble("Basic_Salary");
					p.House_Allowence = myResult.getDouble("House_Allowence");
					p.Conveyance = myResult.getDouble("Conveyance");
					p.Addition = myResult.getDouble("Addition");
					p.Deduction=myResult.getDouble("Deduction");
					
					return p;
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
		
		public Payslip getByEmployee_ID(int id)
		{
			try
			{
				String sql = "select * from payslip where Employee_ID = "+id;
				myResult = myStatement.executeQuery(sql);
				
				if(myResult.next())
				{
					Payslip p = new Payslip();
					p.ID = myResult.getInt("ID");
					p.Employee_ID = myResult.getInt("Employee_ID");
					p.Payroll_ID = myResult.getInt("Payroll_ID");
					p.Basic_Salary = myResult.getDouble("Basic_Salary");
					p.House_Allowence = myResult.getDouble("House_Allowence");
					p.Conveyance = myResult.getDouble("Conveyance");
					p.Addition = myResult.getDouble("Addition");
					p.Deduction=myResult.getDouble("Deduction");
					
					return p;
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
		
		public ArrayList<Payslip> getAllByEmployee_ID(String key)
		{
			ArrayList<Payslip> list = new ArrayList<Payslip>(); 
			try
			{
				String sql = "select * from payslip where Employee_ID = "+getByEmployee_ID(LoginHelper.CurrentUser.ID).Employee_ID;
				
				if(!key.equals("")) {
					
					sql = "select * from payslip where ID like '%"+key+"%'";
				}
					
				myResult = myStatement.executeQuery(sql);
				
				while(myResult.next())
				{
					Payslip p = new Payslip();
					p.ID = myResult.getInt("ID");
					p.Employee_ID = myResult.getInt("Employee_ID");
					p.Payroll_ID = myResult.getInt("Payroll_ID");
					p.Basic_Salary = myResult.getDouble("Basic_Salary");
					p.House_Allowence = myResult.getDouble("House_Allowence");
					p.Conveyance = myResult.getDouble("Conveyance");
					p.Addition = myResult.getDouble("Addition");
					p.Deduction = myResult.getDouble("Deduction");
					
					
					list.add(p);
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			return list;
		}
}
