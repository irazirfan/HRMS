package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import EntityLayer.Department;

public class DepartmentTableModel extends AbstractTableModel{

	public String[] colNames = {"ID","Department Name"};
	private ArrayList<Department> List = new ArrayList<Department>();
	
	public DepartmentTableModel(ArrayList<Department> list) {
		
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
		
		Department de = List.get(row);
		switch(col) {
			case 0:
				return de.ID;
			case 1:
				return de.Department_Name;
			default:
				return "-";
		}
	}
}
