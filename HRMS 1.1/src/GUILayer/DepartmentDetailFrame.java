
package GUILayer;

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import DAOLayer.DepartmentDao;
	import EntityLayer.Department;
	import HelperLayer.ValidationHelper;

	public class DepartmentDetailFrame<Parent> extends JFrame {

		private JLabel lblID,lblDepartment_Name;
		private JTextField txtID,txtDepartment_Name;
		private JButton btnSave,btnBack;
		private DepartmentDao departmentDao;
		private int ID;
		DepartmentManagerFrame Parent;
		ImageIcon icon;
		
	    public  DepartmentDetailFrame(int id,DepartmentManagerFrame parent) {
				    	
	    	if(id==-1)
	    		this.setTitle("New");
	    	else
	    		this.setTitle("Detail");
	    	Parent = parent;
	    	
			this.setSize(500,500);
			departmentDao = new DepartmentDao();
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
		
	    	Department de = departmentDao.getByID(ID);
	    		if(de==null) {
	        		
	        		JOptionPane.showMessageDialog(null, "Invalid ID");
	        		btnSave.setEnabled(false);
	        		return;
	    		}
	    	
	    	txtID.setText(de.ID+"");
	    	txtDepartment_Name.setText(de.Department_Name+"");    		    	
	    	txtID.setEnabled(false);
	    	
	    }

		private void addComponent() {
		
			lblID = new JLabel("ID");
			lblID.setBounds(100, 160, 130, 25);	
			add(lblID);
			
			txtID = new JTextField();
			txtID.setFont(new Font("Serif", Font.PLAIN, 18));
			txtID.setBounds(220, 160, 130, 25);	
			add(txtID);
			
			lblDepartment_Name = new JLabel("Department Name");
			lblDepartment_Name.setBounds(100, 200, 130, 25);	
			add(lblDepartment_Name);
			
			txtDepartment_Name = new JTextField();
			txtDepartment_Name.setFont(new Font("Serif", Font.PLAIN, 12));
			txtDepartment_Name.setBounds(220, 200, 130, 25);	
			add(txtDepartment_Name);	
		
			btnSave = new JButton("Save");
			btnSave.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 12));
			btnSave.setForeground(Color.BLUE);
			btnSave.setBounds(220, 240, 100, 25);
			btnSave.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent ae) {
					
					if(ValidationHelper.IsValidInt(txtID.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Invalid ID");
						txtID.setFocusable(true);
						return;
					}
					if(txtDepartment_Name.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Invalid Name");
						txtDepartment_Name.setFocusable(true);
						return;
					}
					
					Department de = new Department();
					de.ID = Integer.parseInt(txtID.getText());
					de.Department_Name=txtDepartment_Name.getText();
					
		
					boolean r = false;
			    	if (ID==-1) {
						int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to insert?");
			    		if (x==0) {
						
			    			r = departmentDao.Insert(de);
						}
					}
			    	else {
			    		
			    		int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?");
			    		if (x==0) {
						
			    			r = departmentDao.Update(de);
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
			btnBack.setBounds(100,240,100,25);
			btnBack.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					Parent.setVisible(true);
					setVisible(false);
				}
			});
			add(btnBack);
		}

	}
