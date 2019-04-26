
package GUILayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import DAOLayer.UserDao;
import EntityLayer.Users;
import HelperLayer.ValidationHelper;

public class UserDetailFrame extends JFrame {

	private JLabel lblID,lblName,lblUserName,lblPassword,lblConPassword,lblUserType;
	private JTextField txtID,txtName,txtUserName,txtUserType;
	private JPasswordField txtPassword,txtConPassword;
	private JButton btnSave,btnBack;
	private UserDao userDao;
	private int ID;
	UserManagerFrame Parent;
	ImageIcon icon;
	
    public UserDetailFrame(int id,UserManagerFrame parent) {
		
    	Parent = parent;
    	
    	if(id==-1)
    		this.setTitle("New User");
    	else
    		this.setTitle("User Detail");
    	
		this.setSize(500,500);
		userDao = new UserDao();
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
	
    	Users u = userDao.getByID(ID);
    	if(u==null) {
    		
    		return;
    	}
    	
    	txtID.setText(u.ID+"");
    	txtName.setText(u.Name);
    	txtUserName.setText(u.UserName);
    	txtPassword.setText(u.Pass);
    	txtConPassword.setText(u.Pass);
    	txtUserType.setText(u.UserType);
    	txtID.setEnabled(false);
    }

	private void addComponent() {
	
    	
    	lblID = new JLabel("ID");
    	lblID.setBounds(20,10,130,30);
		add(lblID);
    	
		lblName = new JLabel("Name");
		lblName.setBounds(20,50,130,30);
		add(lblName);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setBounds(20,90,130,30);
		add(lblUserName);
		
		lblPassword = new JLabel("Passsword");
		lblPassword.setBounds(20,130,130,30);
		add(lblPassword);
		
		lblConPassword = new JLabel("Confirm Password");
		lblConPassword.setBounds(20,170,130,30);
		add(lblConPassword);
		
		lblUserType = new JLabel("User Type");
		lblUserType.setBounds(20, 210, 130, 30);
		add(lblUserType);
		
		txtID = new JTextField();
		txtID.setBounds(150,10,130,30);
		add(txtID);
		
		txtName = new JTextField();
		txtName.setBounds(150,50,130,30);
		add(txtName);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(150,90,130,30);
		add(txtUserName);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(150,130,130,30);
		add(txtPassword);
		
		txtConPassword = new JPasswordField();
		txtConPassword.setBounds(150,170,130,30);
		add(txtConPassword);
		
		txtUserType = new JTextField();
		txtUserType.setBounds(150, 210, 130, 30);
		add(txtUserType);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 12));
		btnSave.setForeground(Color.BLUE);
		btnSave.setBounds(220,260,100,30);
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(ValidationHelper.IsValidInt(txtID.getText())==false)
				{
					JOptionPane.showMessageDialog(null, "Invalid ID");
					txtID.setFocusable(true);
					return;
				}
				if(txtName.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Invalid Name");
					txtName.setFocusable(true);
					return;
				}
				
				if(txtPassword.getText().equals(txtConPassword.getText())==false)
				{
					JOptionPane.showMessageDialog(null, "Password and Confirm Password should match");
					txtPassword.setFocusable(true);
					return;
				}
				
				Users u = new Users();
				u.ID = Integer.parseInt(txtID.getText());
				u.Name = txtName.getText();
				u.UserName = txtUserName.getText();
				u.Pass = txtPassword.getText();
				u.UserType = txtUserType.getText();
				
				boolean r = false;
		    	if (ID==-1) {
					int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to insert?");
		    		if (x==0) {
					
		    			r = userDao.Insert(u);
		    			JOptionPane.showMessageDialog(null, "Operation Succesful");
					}
				}
		    	else {
		    		
		    		int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?");
		    		if (x==0) {
					
		    			r = userDao.Update(u);
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
		btnBack.setBounds(100,260,100,30);
		btnBack.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Parent.setVisible(true);
				setVisible(false);
			}
		});
		add(btnBack);
	}

}
