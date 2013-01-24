package edu.msoe.se2800.h4;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UserTableModel extends AbstractTableModel {
	
	/** Generated serialVersionUID */
	private static final long serialVersionUID = 9164306818085289440L;
	
	private List<User> users;
	
	public UserTableModel(List<User> users) {
		this.users = users;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public User getValueAt(int rowIndex, int columnIndex) {
		return users.get(rowIndex);
	}

    /*
     * Don't need to implement this method unless your table's editable.
     */
    public boolean isCellEditable(int row, int col) {
      //Note that the data/cell address is constant,
      //no matter where the cell appears onscreen.
      if (col < 2) {
        return false;
      } else {
        return true;
      }
    }

    /*
     * Don't need to implement this method unless your table's data can
     * change.
     */
    public void setValueAt(User value, int row, int col) {
      System.out.println("Setting value at " + row + "," + col
            + " to " + value + " (an instance of "
            + value.getClass() + ")");

      users.set(row, value);
      fireTableCellUpdated(row, col);
    }
}
