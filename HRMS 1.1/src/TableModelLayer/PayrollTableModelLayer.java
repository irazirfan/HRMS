package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import EntityLayer.Payroll;

public class PayrollTableModelLayer extends AbstractTableModel{

	public String[] colNames = {"ID","Name","Month","Year","IsActive"};
	private ArrayList<Payroll> List = new ArrayList<Payroll>();
	
	public PayrollTableModelLayer(ArrayList<Payroll> list) {
		
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
		
		Payroll p = List.get(row);
		switch(col) {
			case 0:
				return p.ID;
			case 1:
				return p.Name;
			case 2:
				return p.Month;
			case 3:
				return p.Year;
			case 4:
				return p.IsActive;
			default:
				return "-";
		}
	}
	
	
}
