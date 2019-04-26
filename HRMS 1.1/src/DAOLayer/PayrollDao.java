
package DAOLayer;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.ArrayList;

	import javax.swing.JOptionPane;

	import EntityLayer.Payroll;

	public class PayrollDao {
		
		private Connection myConnection;
		private Statement myStatement;
		private ResultSet myResult;
		
		public PayrollDao()
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
		
		public ArrayList<Payroll> getAll(String key)
		{
			ArrayList<Payroll> list = new ArrayList<Payroll>(); 
			try
			{
				String sql = "select * from Payroll";
				
				if(!key.equals("")) {
					
					sql = "select * from payroll where ID like '%"+key+"%' or Name like '%"+key+"%'";
				}
					
				myResult = myStatement.executeQuery(sql);
				
				while(myResult.next())
				{
					Payroll p = new Payroll();
					p.ID = myResult.getInt("ID");
					p.Name = myResult.getString("Name");
					p.Month = myResult.getString("Month");
					p.Year = myResult.getString("Year");
					p.IsActive = myResult.getBoolean("IsActive");
					
					list.add(p); 
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
			return list;
		}

		public boolean Insert(Payroll p)
		{
			try
			{
				int active = p.IsActive?1:0;
				if(p.IsActive)
				{
					String sql = "select * from payroll where IsActive=1";
						
					myResult = myStatement.executeQuery(sql);
					
					while(myResult.next())
					{
						JOptionPane.showMessageDialog(null, "There should be one active payroll at a time.");
						return false;
					}
				}
				String sql = "insert into payroll values("+p.ID+",'"+p.Name+"','"+p.Month+"','"+p.Year+"',"+active+")";
				int row = myStatement.executeUpdate(sql);
				return row>0;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		}
		
		public boolean Update(Payroll p)
		{
			try
			{
				int active = p.IsActive?1:0;
				if(p.IsActive)
				{
					String sql = "select * from payroll where IsActive=1 and ID<>"+p.ID;
						
					myResult = myStatement.executeQuery(sql);
					
					while(myResult.next())
					{
						JOptionPane.showMessageDialog(null, "There should be one active payroll at a time.");
						return false;
					}
				}
				
				String sql = "update payroll set ID="+p.ID+",Name='"+p.Name+"',Month='"+p.Month+"',Year='"+p.Year+"',IsActive="+active+" where ID="+p.ID;
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
				String sql = "delete from payroll where ID="+id;
				int row = myStatement.executeUpdate(sql);
				return row>0;
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
				return false;
			}
		}

		
		public Payroll getByID(int id)
		{
			try
			{
				String sql = "select * from payroll where ID = "+id;
				myResult = myStatement.executeQuery(sql);
				
				if(myResult.next())
				{
					Payroll p = new Payroll();
					p.ID = myResult.getInt("ID");
					p.Name = myResult.getString("Name");
					p.Month = myResult.getString("Month");
					p.Year = myResult.getString("Year");
					p.IsActive = myResult.getBoolean("IsActive");
					
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
}
