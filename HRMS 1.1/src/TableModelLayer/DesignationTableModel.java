
package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import EntityLayer.Designation;

public class DesignationTableModel extends AbstractTableModel{

	public String[] colNames = {"ID","Designation Name"};
	private ArrayList<Designation> List = new ArrayList<Designation>();
	
	public DesignationTableModel(ArrayList<Designation> list) {
		
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
		
		Designation d = List.get(row);
		switch(col) {
			case 0:
				return d.ID;
			case 1:
				return d.Designation_Name;
			default:
				return "-";
		}
	}
}
