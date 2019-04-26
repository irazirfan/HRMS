
package GUILayer;

	import java.awt.*;
	import java.awt.event.*;
	import java.util.ArrayList;
	import javax.swing.*;
	import DAOLayer.EmployeeDao;
import DAOLayer.UserDao;
import DAOLayer.DepartmentDao;
	import DAOLayer.DesignationDao;
	import EntityLayer.Department;
	import EntityLayer.Designation;
	import EntityLayer.Employee;
	import EntityLayer.Payslip;
import EntityLayer.Users;
import HelperLayer.LoginHelper;
import HelperLayer.ValidationHelper;

	public class EmployeeDetailFrame<Parent> extends JFrame {

		private JLabel lblID,lblName,lblJoinDate,lblContact,lblAddress,lblBSalary,lblHAllowence,lblConveyance,lblDeptID,lblDesID;
		private JTextField txtID,txtName,txtJoinDate,txtContact,txtAddress,txtBSalary,txtHAllowence,txtConveyance;
		private JButton btnSave,btnBack;
		
		private JComboBox ddlDepartments;
		private JComboBox ddlDesignations;
		ArrayList<Department> departments;
		ArrayList<Designation> designations;
		ArrayList<Payslip> payslips;
		
		private int[] departmentIDs;
		private String[] departmentNames;
		private int[] designationIDs;
		private String[] designationNames;
		
		private EmployeeDao employeeDao;
		private int ID;
		EmployeeManagerFrame Parent;
		ImageIcon icon;
		
	    public EmployeeDetailFrame(int id,EmployeeManagerFrame parent) {
				    	
	    	if(id==-1)
	    		this.setTitle("New Employee");
	    	else
	    		this.setTitle("Employee Detail");
	    	
			this.setSize(500,500);
			employeeDao = new EmployeeDao();
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
			
			if(id!=-1)
				this.loadUser();
		}

	    private void loadUser() {
		
	    	Employee e = employeeDao.getByID(ID);
	    		if(e==null) {
	        		
	        		JOptionPane.showMessageDialog(null, "Invalid ID");
	        		btnSave.setEnabled(false);
	        		return;
	    		}
	    	
	    	txtID.setText(e.ID+"");
	    	txtName.setText(e.Name+"");
	    	txtJoinDate.setText(e.JoiningDate+"");
	    	txtContact.setText(e.Contact+"");
	    	txtAddress.setText(e.Address+"");
	    	txtBSalary.setText(e.BasicSalary+"");
	    	txtHAllowence.setText(e.HouseAllowence+"");
	    	txtConveyance.setText(e.Conveyance+"");
	    	ddlDepartments.setSelectedItem(new DepartmentDao().getByID(e.DepartmentID).Department_Name);
	    	ddlDesignations.setSelectedItem(new DesignationDao().getByID(e.DesignetionID).Designation_Name);   		    	
	    	txtID.setEnabled(false);
	    	txtBSalary.setEnabled(false);
	    	txtHAllowence.setEnabled(false);
	    	txtConveyance.setEnabled(false);
	    }

		private void addComponent() {
		
	    	
	    	lblID = new JLabel("ID");
	    	lblID.setBounds(20,10,130,30);
			add(lblID);
	    	
			lblName = new JLabel("Name");
			lblName.setBounds(20,50,130,30);
			add(lblName);
			
			lblJoinDate = new JLabel("Joining Date");
			lblJoinDate.setBounds(20,90,130,30);
			add(lblJoinDate);
			
			lblContact = new JLabel("Contact");
			lblContact.setBounds(20,130,130,30);
			add(lblContact);
			
			lblAddress= new JLabel("Address");
			lblAddress.setBounds(20,170,130,30);
			add(lblAddress);
			
			lblBSalary= new JLabel("Basic Salary");
			lblBSalary.setBounds(20,210,130,30);
			add(lblBSalary);
			
			lblHAllowence= new JLabel("House Allowence");
			lblHAllowence.setBounds(20,250,130,30);
			add(lblHAllowence);
			
			lblConveyance= new JLabel("Conveyance");
			lblConveyance.setBounds(20,290,130,30);
			add(lblConveyance);
			
			lblDeptID= new JLabel("Department ID");
			lblDeptID.setBounds(20,330,130,30);
			add(lblDeptID);
			
			lblDesID= new JLabel("Designation ID");
			lblDesID.setBounds(20,370,130,30);
			add(lblDesID);
			
			txtID = new JTextField();
			txtID.setBounds(170,10,130,30);
			add(txtID);
	    	
			txtName = new JTextField();
			txtName.setBounds(170,50,130,30);
			add(txtName);
			
			txtJoinDate = new JTextField("yyyy-mm-dd");
			txtJoinDate.setBounds(170,90,130,30);
			add(txtJoinDate);
			
			txtContact = new JTextField();
			txtContact.setBounds(170,130,130,30);
			add(txtContact);
			
			txtAddress= new JTextField();
			txtAddress.setBounds(170,170,130,30);
			add(txtAddress);
			
			txtBSalary= new JTextField();
			txtBSalary.setBounds(170,210,130,30);
			add(txtBSalary);
			
			txtHAllowence= new JTextField();
			txtHAllowence.setBounds(170,250,130,30);
			add(txtHAllowence);
			
			txtConveyance= new JTextField();
			txtConveyance.setBounds(170,290,130,30);
			add(txtConveyance);
			
			departments = new DepartmentDao().getAll("");
			departmentIDs = new int[departments.size()];
			departmentNames = new String[departments.size()];
			
			int i=0;
			for(Department dep:departments)
			{
				departmentIDs[i]=dep.ID;
				departmentNames[i]=dep.Department_Name;
				i++;
			}
			
			ddlDepartments = new JComboBox(departmentNames);
			ddlDepartments.setBounds(170,330,130,30);
			add(ddlDepartments);
			
			designations = new DesignationDao().getAll("");
			designationIDs = new int[designations.size()];
			designationNames = new String[designations.size()];
			
			int j=0;
			
			for(Designation des:designations)
			{
				designationIDs[j]=des.ID;
				designationNames[j]=des.Designation_Name;
				j++;
			}
			
			ddlDesignations= new JComboBox(designationNames);
			ddlDesignations.setBounds(170,370,130,30);
			add(ddlDesignations);
			
			btnSave = new JButton("Save");
			btnSave.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 12));
			btnSave.setForeground(Color.BLUE);
			btnSave.setBounds(220,420,100,30);
			btnSave.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					
					if(ValidationHelper.IsValidInt(txtID.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Invalid ID");
						txtID.setFocusable(true);
						return;
					}
					
					if(ValidationHelper.IsValidEmployee(txtID.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "An admin cannot be added as a employee!");
						return;
					}
					
					if(txtName.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Invalid Name");
						txtName.setFocusable(true);
						return;
					}
					
					Employee e = new Employee();
					
					e.ID = Integer.parseInt(txtID.getText());
					e.Name = txtName.getText();
					e.JoiningDate = txtJoinDate.getText();
					e.Contact = txtContact.getText();
					e.Address = txtAddress.getText();
					e.BasicSalary = Double.parseDouble(txtBSalary.getText());
					e.HouseAllowence = Double.parseDouble(txtHAllowence.getText());
					e.Conveyance = Double.parseDouble(txtConveyance.getText());
					e.DepartmentID = departmentIDs[ddlDepartments.getSelectedIndex()];
					e.DesignetionID = designationIDs[ddlDesignations.getSelectedIndex()];
					
					
					boolean r = false;
			    	if (ID==-1) {
						int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to insert?");
			    		if (x==0) {
						
			    			r = employeeDao.Insert(e);
			    			JOptionPane.showMessageDialog(null, "Operation Succesful");
						}
					}
			    	else {
			    		
			    		int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?");
			    		if (x==0) {
						
			    			r = employeeDao.Update(e);
			    			JOptionPane.showMessageDialog(null, "Operation Succesful");
						}
			    	}
					if(r)
					{
						setVisible(false);
						Parent.populateTable();
						Parent.setVisible(true);
					}
				}
			});
			add(btnSave);
			
			btnBack = new JButton("Back");
			btnBack.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 12));
			btnBack.setForeground(Color.BLUE);
			btnBack.setBounds(100,420,100,30);
			btnBack.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					Parent.setVisible(true);
					setVisible(false);
				}
			});
			add(btnBack);
		}

	}
