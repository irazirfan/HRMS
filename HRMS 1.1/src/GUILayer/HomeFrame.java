
package GUILayer;

import DAOLayer.UserDao;
import EntityLayer.Users;
import HelperLayer.LoginHelper;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomeFrame extends JFrame{

	private JLabel lblImage,lblMsg,lblUserName,lblPassword;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	UserDao userDao;
	
    private HomeFrame MyReference() {
		
		return this;
	}
	
	public HomeFrame() {
		
		this.setTitle("Home");
		this.setSize(500, 500);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		userDao = new UserDao();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponent();
	}

	private void addComponent() {
		
		ImageIcon icon = new ImageIcon(getClass().getResource("Sky.jpg"));
		Image img = icon.getImage().getScaledInstance(500, 500, 1);
		
		
		
		lblMsg = new JLabel("Welcome to HRMS");
		lblMsg.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 40));
		lblMsg.setForeground(Color.BLUE);
		lblMsg.setBounds(70, 80, 350, 50);
		add(lblMsg);
		
		lblUserName = new JLabel("User Name :");
		lblUserName.setBounds(100, 160, 100, 25);	
		add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Serif", Font.PLAIN, 18));
		txtUserName.setBounds(210, 160, 150, 25);	
		add(txtUserName);
		
		lblPassword = new JLabel("Password :");
		lblPassword.setBounds(100, 200, 100, 25);	
		add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Serif", Font.PLAIN, 12));
		txtPassword.setBounds(210, 200, 150, 25);	
		add(txtPassword);
		
		btnLogin = new JButton("Log In");
		btnLogin.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 12));
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setBounds(210, 240, 150, 25);
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Users u = userDao.Login(txtUserName.getText(), txtPassword.getText());
				if(u==null)
				{
					JOptionPane.showMessageDialog(null, "Invalid UserName or Password");
					return;
				}
				
				LoginHelper.CurrentUser = u;
				MenuFrame mf = new MenuFrame();
				mf.setVisible(true);
				setVisible(false);
			}
		});
		btnLogin.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent ke) {
				
			}
			
			public void keyReleased(KeyEvent ke) {
				
			}
			
			public void keyPressed(KeyEvent ke) {
				
				if (ke.getKeyCode()==KeyEvent.VK_ENTER) {
					
					btnLogin.doClick();
				}
			}
		});
		add(btnLogin);
		
		lblImage = new JLabel();
		lblImage.setBounds(0, 0, 500, 500);
		lblImage.setIcon(new ImageIcon(img));
		add(lblImage);
	}
}