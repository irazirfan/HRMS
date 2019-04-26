
package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAOLayer.EmployeeDao;
import DAOLayer.PayrollDao;
import EntityLayer.Payslip;

public class PayslipTableModel extends AbstractTableModel{

	public String[] colNames = {"ID","Employee","Payroll","Total Salary"};
	private ArrayList<Payslip> List = new ArrayList<Payslip>();
	
	public PayslipTableModel(ArrayList<Payslip> list) {
		
		List=list;
	}
	
	public int getColumnCount() {
		
		
		return colNames.length;
	}
	
	public String getColumnName(int col) {
		
		return colNames[col];
	}

	public int getRowCount() {
		
		
		return List.size();
	}

	public Object getValueAt(int row, int col) {
		
		Payslip p = List.get(row);
		switch(col) {
			case 0:
				return p.ID;
			case 1:
				return new EmployeeDao().getByID(p.Employee_ID).Name;
			case 2:
				return new PayrollDao().getByID(p.Payroll_ID).Name;
			case 3:
				return p.Basic_Salary+p.Conveyance+p.Addition-p.Deduction;
			default:
				return "-";
		}
	}
	
	
}
