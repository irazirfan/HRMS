
package GUILayer;

	import java.awt.*;
	import java.awt.event.*;
	import java.util.ArrayList;
	import javax.swing.*;

    import DAOLayer.EmployeeDao;
    import DAOLayer.PayrollDao;
    import DAOLayer.PayslipDao;
    import EntityLayer.Employee;
    import EntityLayer.Payroll;
    import EntityLayer.Payslip;
    import HelperLayer.LoginHelper;

	public class IndividualPayslipDetailFrame extends JFrame {

		private JLabel lblID,lblEmployee_ID,lblPayroll_ID,lblBasic_Salary,lblHAllowence,lblConveyance,lblAddition,lblDeduction;
		private JTextField txtID,txtBasic_Salary,txtHAllowence,txtConveyance,txtAddition,txtDeduction;
		private JComboBox ddlEmployees;
		private JComboBox ddlPayrolls;
		ArrayList<Employee> employees;
		ArrayList<Payroll> payrolls;
		ArrayList<Payslip> payslips;
		
		private int[] employeeIDs;
		private String[] employeeNames;
		private int[] payrollIDs;
		private String[] payrollNames;
		
		private JButton btnBack;
		private PayslipDao payslipDao;
		private int ID;
		JFrame Parent;
		
	    public IndividualPayslipDetailFrame(JFrame parent,int id) {
			
	    	
	    	this.setTitle("Indiviual Detail Payslip");
			this.setSize(500,500);
			payslipDao = new PayslipDao();
			Parent = parent;
			this.ID=id;
			this.setLayout(null);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.addComponent();
			this.addWindowListener(new WindowListener() {
				
				public void windowOpened(WindowEvent we) {
					
				}
				
				public void windowIconified(WindowEvent we) {
					
				}
		
				public void windowDeiconified(WindowEvent we) {
					
				}
				
				public void windowDeactivated(WindowEvent we) {
					
				}
				
				public void windowClosing(WindowEvent we) {
					
					Parent.setVisible(true);
					setVisible(false);
				}
			
				public void windowClosed(WindowEvent we) {
					
				}
				
				public void windowActivated(WindowEvent we) {
					
				}
			});
			
				this.loadUser();
		}

	    private void loadUser() {
		
	    	Payslip p = payslipDao.getByID(ID);
	    		if(p==null) {
	        		
	        		JOptionPane.showMessageDialog(null, "Invalid ID");
	        		return;
	    		}
	    	
	    	txtID.setText(p.ID+"");
	    	
	    	ddlEmployees.setSelectedItem(new EmployeeDao().getByID(p.Employee_ID).Name);
	    	ddlPayrolls.setSelectedItem(new PayrollDao().getByID(p.Payroll_ID).Name);
	    	txtBasic_Salary.setText(p.Basic_Salary+"");
	    	txtHAllowence.setText(p.House_Allowence+"");
	    	txtConveyance.setText(p.Conveyance+"");
	    	txtAddition.setText(p.Addition+"");
	    	txtDeduction.setText(p.Deduction+"");	    		    	
	    	txtID.setEnabled(false);
	    	ddlEmployees.setEnabled(false);
	    	ddlPayrolls.setEnabled(false);
	    	txtBasic_Salary.setEnabled(false);
	    	txtHAllowence.setEnabled(false);
	    	txtConveyance.setEnabled(false);
	    	txtAddition.setEnabled(false);
	    	txtDeduction.setEnabled(false);
	    	
	    }

		private void addComponent() {
		
	    	
	    	lblID = new JLabel("ID");
	    	lblID.setBounds(20,10,130,30);
			add(lblID);
	    	
			lblEmployee_ID = new JLabel("Employee_Name");
			lblEmployee_ID.setBounds(20,50,130,30);
			add(lblEmployee_ID);
			
			lblPayroll_ID = new JLabel("Payroll_Title");
			lblPayroll_ID.setBounds(20,90,130,30);
			add(lblPayroll_ID);
			
			lblBasic_Salary = new JLabel("Basic Salary");
			lblBasic_Salary .setBounds(20,130,130,30);
			add(lblBasic_Salary );
			
			lblHAllowence= new JLabel("House Allowence");
			lblHAllowence.setBounds(20,170,130,30);
			add(lblHAllowence);
			
			lblConveyance= new JLabel("Conveyance");
			lblConveyance.setBounds(20,210,130,30);
			add(lblConveyance);
			
			lblAddition= new JLabel("Addition");
			lblAddition.setBounds(20,250,130,30);
			add(lblAddition);
			
			lblDeduction= new JLabel("Deduction");
			lblDeduction.setBounds(20,290,130,30);
			add(lblDeduction);
			
			employees = new EmployeeDao().getAll("");
			employeeIDs = new int[employees.size()];
			employeeNames = new String[employees.size()];
			
			int i=0;
			for(Employee emp:employees)
			{
				employeeIDs[i]=emp.ID;
				employeeNames[i]=emp.Name;
				i++;
			}
			
			ddlEmployees = new JComboBox(employeeNames);
			ddlEmployees.setBounds(170,50,130,30);
			ddlEmployees.addItemListener(new ItemListener() {
				
				public void itemStateChanged(ItemEvent e) {
					
					if(e.getStateChange()==ItemEvent.SELECTED)
					{
						Employee emp = employees.get(ddlEmployees.getSelectedIndex());
						//txtID.setText(emp.ID+"");
						txtBasic_Salary.setText(emp.BasicSalary+"");
						txtConveyance.setText(emp.Conveyance+"");
						txtHAllowence.setText(emp.HouseAllowence+"");
						
					}
				}
			});
			add(ddlEmployees);
					
			payrolls = new PayrollDao().getAll("");
			payrollIDs = new int[payrolls.size()];
			payrollNames = new String[payrolls.size()];
			String activePayroll="";
			int j=0;
			for(Payroll emp:payrolls)
			{
				payrollIDs[j]=emp.ID;
				payrollNames[j]=emp.Name;
				if(emp.IsActive)
					activePayroll = emp.Name;
				j++;
			}
			
			ddlPayrolls= new JComboBox(payrollNames);
			ddlPayrolls.setBounds(170,90,130,30);
			ddlPayrolls.setSelectedItem(activePayroll);
			add(ddlPayrolls);
			
			txtID = new JTextField();
			txtID.setBounds(170,10,130,30);
			add(txtID);
			
			txtBasic_Salary = new JTextField();
			txtBasic_Salary.setText(employees.get(0).BasicSalary+"");
			txtBasic_Salary.setBounds(170,130,130,30);
			add(txtBasic_Salary);
			
			txtHAllowence= new JTextField();
			txtHAllowence.setText(employees.get(0).HouseAllowence+"");
			txtHAllowence.setBounds(170,170,130,30);
			add(txtHAllowence);
			
			txtConveyance= new JTextField();
			txtConveyance.setText(employees.get(0).Conveyance+"");
			txtConveyance.setBounds(170,210,130,30);
			add(txtConveyance);
			
			txtAddition= new JTextField("0");
			txtAddition.setBounds(170,250,130,30);
			add(txtAddition);
			
			txtDeduction= new JTextField("0");
			txtDeduction.setBounds(170,290,130,30);
			add(txtDeduction);
			
			btnBack = new JButton("Back");
			btnBack.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 12));
			btnBack.setForeground(Color.BLUE);
			btnBack.setBounds(170,340,130,30);
			btnBack.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					Parent.setVisible(true);
					setVisible(false);
				}
			});
			add(btnBack);
		}

	}
