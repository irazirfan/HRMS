package TableModelLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import EntityLayer.Users;

public class UserTableModel extends AbstractTableModel{

	public String[] colNames = {"ID","User Name","Name","User Type"};
	private ArrayList<Users> List = new ArrayList<Users>();
	
	public UserTableModel(ArrayList<Users> list) {
		
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
		
		Users u = List.get(row);
		switch(col) {
			case 0:
				return u.ID;
			case 1:
				return u.UserName;
			case 2:
				return u.Name;
			case 3:
				return u.UserType;
			default:
				return "-";
		}
	}
	
	
}
