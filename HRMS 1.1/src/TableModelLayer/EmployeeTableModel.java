
package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAOLayer.DepartmentDao;
import DAOLayer.DesignationDao;
import EntityLayer.Employee;

public class EmployeeTableModel extends AbstractTableModel{

	public String[] colNames = {"ID","Name","JoiningDate","Department","Designetion"};
	private ArrayList<Employee> List = new ArrayList<Employee>();
	
	public EmployeeTableModel(ArrayList<Employee> list) {
		
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
		
		Employee e = List.get(row);
		switch(col) {
			case 0:
				return e.ID;
			case 1:
				return e.Name;
			case 2:
				return e.JoiningDate;
			case 3:
				return new DepartmentDao().getByID(e.DepartmentID).Department_Name;
			case 4:
				return new DesignationDao().getByID(e.DepartmentID).Designation_Name;
			default:
				return "-";
		}
	}
	
	
}
