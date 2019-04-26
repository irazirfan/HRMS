
package GUILayer;

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import DAOLayer.DesignationDao;
	import EntityLayer.Designation;
	import HelperLayer.ValidationHelper;

	public class DesignationDetailFrame<Parent> extends JFrame {

		private JLabel lblID,lblDegignation_Name;
		private JTextField txtID,txtDesignation_Name;
		private JButton btnSave,btnBack;
		private DesignationDao designationDao;
		private int ID;
		DesignationManagerFrame Parent;
		ImageIcon icon;
		
	    public DesignationDetailFrame(int id,DesignationManagerFrame parent) {
				    	
	    	if(id==-1)
	    		this.setTitle("New");
	    	else
	    		this.setTitle("Detail");
	    	Parent = parent;
	    	
			this.setSize(500,500);
			designationDao = new DesignationDao();
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
		
	    	Designation d = designationDao.getByID(ID);
	    		if(d==null) {
	        		
	        		JOptionPane.showMessageDialog(null, "Invalid ID");
	        		btnSave.setEnabled(false);
	        		return;
	    		}
	    	
	    	txtID.setText(d.ID+"");
	    	txtDesignation_Name.setText(d.Designation_Name+"");    		    	
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
			
			lblDegignation_Name = new JLabel("Designation Name");
			lblDegignation_Name.setBounds(100, 200, 130, 25);	
			add(lblDegignation_Name);
			
			txtDesignation_Name = new JTextField();
			txtDesignation_Name.setFont(new Font("Serif", Font.PLAIN, 12));
			txtDesignation_Name.setBounds(220, 200, 130, 25);	
			add(txtDesignation_Name);	
		
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
					if(txtDesignation_Name.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Invalid Name");
						txtDesignation_Name.setFocusable(true);
						return;
					}
					
					Designation de = new Designation();
					de.ID = Integer.parseInt(txtID.getText());
					de.Designation_Name = txtDesignation_Name.getText();
					
		
					boolean r = false;
			    	if (ID==-1) {
						int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to insert?");
			    		if (x==0) {
						
			    			r = designationDao.Insert(de);
			    			JOptionPane.showMessageDialog(null, "Operation Succesful");
						}
					}
			    	else {
			    		
			    		int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?");
			    		if (x==0) {
						
			    			r = designationDao.Update(de);
			    			JOptionPane.showMessageDialog(null, "Operation Succesful");
						}
			    	}
					if(r)
					{
						setVisible(false);
						Parent.populateTable();
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
