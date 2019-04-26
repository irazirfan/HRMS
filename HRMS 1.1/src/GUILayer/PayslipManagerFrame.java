
package GUILayer;

	import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	import javax.swing.*;
	import javax.swing.border.*;
	import DAOLayer.PayslipDao;
	import EntityLayer.Payslip;
	import TableModelLayer.PayslipTableModel;

	public class PayslipManagerFrame extends JFrame{

		private JPanel upperPanel,lowerPanel;
		
		private JLabel lblSearch;
		private JTextField txtSearch;
		private JButton btnSearch,btnAdd,btnEdit,btnDelete,btnBack;
		
		private JTable tblPayslip;
		JFrame Parent;
		private PayslipDao payslipDao;
		ImageIcon icon;
		
		public PayslipManagerFrame(JFrame parent) {
			
			this.setTitle("");
			this.setSize(600,450);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			Parent = parent;
			payslipDao = new PayslipDao();
			
			upperPanel = new JPanel(new FlowLayout());
			this.upperPanel.setBorder(new LineBorder(Color.black, 1));;
			this.upperPanel.setBounds(5, 5, 585, 40);
			add(upperPanel);
			
			lowerPanel = new JPanel(null);
			this.lowerPanel.setBorder(new TitledBorder(new LineBorder(Color.GREEN, 1),"Data"));;
			this.lowerPanel.setBounds(5, 50, 585, 365);
			add(lowerPanel);
			
			this.addUpperComponent();
			this.addLowerComponent();
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
		}

		private void showDetailFrame(int id) {
			
			PayslipDetailFrame pa = new PayslipDetailFrame(id, this);
			pa.setVisible(true);
			setVisible(false);
		}
		
		private void addUpperComponent() {
			
			lblSearch = new JLabel("Search");
			this.upperPanel.add(lblSearch);
			
			txtSearch = new JTextField(10);
			this.upperPanel.add(txtSearch);
			
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					populateTable();			
				}
			});
			this.upperPanel.add(btnSearch);
			
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					showDetailFrame(-1);
				}
			});
			this.upperPanel.add(btnAdd);
			
			btnEdit = new JButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					int row = tblPayslip.getSelectedRow();
					if (row==-1) {
						JOptionPane.showMessageDialog(null, "Please select a row first");
						return;
					}
					
					int id = (int)tblPayslip.getValueAt(row, 0);
					showDetailFrame(id);			
				}
			});
			this.upperPanel.add(btnEdit);
			
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					int row = tblPayslip.getSelectedRow();
					if (row==-1) {
						JOptionPane.showMessageDialog(null, "Please select a row first");
						return;
					}
					
					int id = (int)tblPayslip.getValueAt(row, 0);
					int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?");
					if (x==0) {
					
						boolean r = payslipDao.Delete(id);
				        if(r) {
				        	
				        	JOptionPane.showMessageDialog(null, "Operation Successful");
				        	populateTable();
				        }
				        else {
				        	JOptionPane.showMessageDialog(null, "Something went wrong");
				        }
					}
				}
			});
			this.upperPanel.add(btnDelete);
			
			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					Parent.setVisible(true);
					setVisible(false);
				}
			});
			this.upperPanel.add(btnBack);
	    }
		
		private void addLowerComponent() {
			
			tblPayslip = new JTable();
			tblPayslip.setBackground(Color.WHITE);
			
			JScrollPane sp = new JScrollPane();
			sp.setBounds(10, 15, 565, 340);
			this.lowerPanel.add(sp);
			sp.setViewportView(tblPayslip);
			
			this.populateTable();
		}

		protected void populateTable() {
			
			ArrayList<Payslip> userList = payslipDao.getAll(txtSearch.getText());
			
			   PayslipTableModel model = new PayslipTableModel(userList);
			   tblPayslip.setModel(model);
		}
	}
