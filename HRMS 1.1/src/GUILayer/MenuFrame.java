
package GUILayer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import HelperLayer.LoginHelper;

public class MenuFrame extends JFrame{
	
	private JButton btnProfile,btnPayroll,btnEmployee,btnPayslip,btnUsers,btnDesignation,btnDepartment,btnLogout;
	ImageIcon icon;
	
	public MenuFrame MyReference() {
		
		return this;
	}
	
        public MenuFrame() {
		this.setTitle("Welcome, "+LoginHelper.CurrentUser.Name);
		this.setSize(500,500);
		if(LoginHelper.CurrentUser.UserType.equals("admin")) {
			
			this.setLayout(new GridLayout(2, 2, 1, 1));
		}
		else {
			
			this.setLayout(new GridLayout());
		}
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponent();
	}
	
	private void addComponent() {
		
		btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 15));
		btnProfile.setForeground(Color.BLUE);
		btnProfile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				RegistrationFrame rf = new RegistrationFrame(LoginHelper.CurrentUser.ID,MyReference());
				rf.setVisible(true);
				setVisible(false);
			}
		});
		add(btnProfile);
		
		btnPayroll = new JButton("Payroll");
		btnPayroll.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 15));
		btnPayroll.setForeground(Color.BLUE);
		if(LoginHelper.CurrentUser.UserType.equals("admin")) {
		btnPayroll.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				PayrollManagerFrame pf = new PayrollManagerFrame(MyReference());
				pf.setVisible(true);
				setVisible(false);
			}
		});
		add(btnPayroll);
		}
		
		btnEmployee = new JButton("Employee");
		btnEmployee.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 15));
		btnEmployee.setForeground(Color.BLUE);
		if(LoginHelper.CurrentUser.UserType.equals("admin")) {
			btnEmployee.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					EmployeeManagerFrame ef = new EmployeeManagerFrame(MyReference());
					ef.setVisible(true);
					setVisible(false);
				}
			});
		add(btnEmployee);
		}
		
		/*btnPayslip = new JButton("Payslip");
		btnPayslip.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 15));
		btnPayslip.setForeground(Color.BLUE);
		if (LoginHelper.CurrentUser.UserType.equals("admin")) {
			btnPayslip.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
				 
					PayslipManagerFrame py = new PayslipManagerFrame(MyReference());
					py.setVisible(true);
					setVisible(false);
				}
			});
			add(btnPayslip);
		}*/
		
		else {
			
			btnPayslip.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
				 
					IndividualPayslipManagerFrame ip = new IndividualPayslipManagerFrame(MyReference());
					ip.setVisible(true);
					setVisible(false);
				}
			});
			add(btnPayslip);
		}
		
		btnUsers = new JButton("Users");
		btnUsers.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 15));
		btnUsers.setForeground(Color.BLUE);
		if(LoginHelper.CurrentUser.UserType.equals("admin")) {
			btnUsers.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					UserManagerFrame uf = new UserManagerFrame(MyReference());
					uf.setVisible(true);
					setVisible(false);
				}
			});
		add(btnUsers);
		}
		
		if(LoginHelper.CurrentUser.UserType.equals("admin")) {
		btnDepartment = new JButton("Department");
		btnDepartment.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 15));
		btnDepartment.setForeground(Color.BLUE);
		btnDepartment.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
					DepartmentManagerFrame df = new DepartmentManagerFrame(MyReference());
					df.setVisible(true);
					setVisible(false);
				}				
		});
		add(btnDepartment);
		}
		
		if(LoginHelper.CurrentUser.UserType.equals("admin")) {
		btnDesignation = new JButton("Designation");
		btnDesignation.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 15));
		btnDesignation.setForeground(Color.BLUE);
		btnDesignation.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
					DesignationManagerFrame df = new DesignationManagerFrame(MyReference());
					df.setVisible(true);
					setVisible(false);
				}				
		});
		add(btnDesignation);
		}
		
		btnLogout = new JButton("Log Out");
		btnLogout.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 15));
		btnLogout.setForeground(Color.BLUE);
		btnLogout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int r = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?");
				if (r==0) {
					
					HomeFrame hf = new HomeFrame();
					hf.setVisible(true);
					setVisible(false);
				}				
			}
		});
		add(btnLogout);
	}

	protected UserManagerFrame UserManagerFrame() {
		
		return null;
	}
}
