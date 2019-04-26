
package GUILayer;

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import DAOLayer.PayrollDao;
	import EntityLayer.Payroll;
	import HelperLayer.ValidationHelper;

	public class PayrollDetailFrame extends JFrame {

		private JLabel lblID,lblName,lblMonth,lblYear,lblIsActive;
		private JTextField txtID,txtTitle,txtMonth,txtYear;
		private JCheckBox chkActive;
		private JButton btnSave,btnBack;
		private PayrollDao payrollDao;
		private int ID;
		PayrollManagerFrame Parent;
		ImageIcon icon;
		
	    public PayrollDetailFrame(int id,PayrollManagerFrame parent) {
			
	    	Parent = parent;
	    	
	    	if(id==-1)
	    		this.setTitle("New");
	    	else
	    		this.setTitle("Detail");
	    	
			this.setSize(500,500);
			payrollDao = new PayrollDao();
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
		
	    	Payroll p = payrollDao.getByID(ID);
	    		if(p==null) {
	        		
	        		JOptionPane.showMessageDialog(null, "Invalid ID");
	        		btnSave.setEnabled(false);
	        		return;
	    		}
	    	
	    	txtID.setText(p.ID+"");
	    	txtTitle.setText(p.Name);
	    	txtMonth.setText(p.Month);
	    	txtYear.setText(p.Year);
	    	chkActive.setSelected(p.IsActive);;
	    	txtID.setEnabled(false);
	    }

		private void addComponent() {
		
	    	
	    	lblID = new JLabel("ID");
	    	lblID.setBounds(20,10,130,30);
			add(lblID);
	    	
			lblName = new JLabel("Name");
			lblName.setBounds(20,50,130,30);
			add(lblName);
			
			lblMonth = new JLabel("Month");
			lblMonth.setBounds(20,90,130,30);
			add(lblMonth);
			
			lblYear = new JLabel("Year");
			lblYear.setBounds(20,130,130,30);
			add(lblYear);
			
			lblIsActive= new JLabel("Is Active");
			lblIsActive.setBounds(20,170,130,30);
			add(lblIsActive);
			
			txtID = new JTextField();
			txtID.setBounds(150,10,130,30);
			add(txtID);
			
			txtTitle = new JTextField();
			txtTitle.setBounds(150,50,130,30);
			txtTitle.setEditable(false);
			add(txtTitle);
			
			txtMonth = new JTextField();
			txtMonth.setBounds(150,90,130,30);
			add(txtMonth);
			
			txtYear = new JTextField();
			txtYear.setBounds(150,130,130,30);
			add(txtYear);
			
			chkActive = new JCheckBox("Is Active");
			chkActive.setBounds(150,170,130,30);
			add(chkActive);
			
			btnSave = new JButton("Save");
			btnSave.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 12));
			btnSave.setForeground(Color.BLUE);
			btnSave.setBounds(220,230,100,30);
			btnSave.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					if(ValidationHelper.IsValidInt(txtID.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Invalid ID");
						txtID.setFocusable(true);
						return;
					}

					if(ValidationHelper.IsValidMonth(txtMonth.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Invalid Month");
						txtMonth.setFocusable(true);
						return;
					}
					
					if(txtYear.getText().length()!=4)
					{
						JOptionPane.showMessageDialog(null, "Invalid Year");
						txtYear.setFocusable(true);
						return;
					}
					
					String title = "";
					switch (txtMonth.getText()) {
					case "1":
						title="January,"+txtYear.getText();
						break;
					case "2":
						title="February,"+txtYear.getText();
						break;
					case "3":
						title="March,"+txtYear.getText();
						break;
					case "4":
						title="April,"+txtYear.getText();
						break;
					case "5":
						title="May,"+txtYear.getText();
						break;
					case "6":
						title="June,"+txtYear.getText();
						break;
					case "7":
						title="July,"+txtYear.getText();
						break;
					case "8":
						title="August,"+txtYear.getText();
						break;
					case "9":
						title="September,"+txtYear.getText();
						break;
					case "10":
						title="Octobor,"+txtYear.getText();
						break;
					case "11":
						title="November,"+txtYear.getText();
						break;
					case "12":
						title="December,"+txtYear.getText();
						break;
					default:
						title="";
						break;
					}
					txtTitle.setText(title);
					
					if(txtTitle.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Invalid Name");
						txtTitle.setFocusable(true);
						return;
					}
					
					
					
					Payroll p = new Payroll();
					p.ID = Integer.parseInt(txtID.getText());
					p.Name = txtTitle.getText();
					p.Month = txtMonth.getText();
					p.Year = txtYear.getText();
					p.IsActive = chkActive.isSelected();
					
					boolean r = false;
			    	if (ID==-1) {
						int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to insert?");
			    		if (x==0) {
						
			    			r = payrollDao.Insert(p);
			    			JOptionPane.showMessageDialog(null, "Operation Succesful");
						}
					}
			    	else {
			    		
			    		int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?");
			    		if (x==0) {
						
			    			r = payrollDao.Update(p);
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
			btnBack.setBounds(100, 230, 100, 30);
			btnBack.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					Parent.setVisible(true);
					setVisible(false);
				}
			});
			add(btnBack);
		}

	}

