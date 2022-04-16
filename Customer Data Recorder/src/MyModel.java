
import javax.swing.table.*;

/**
 * @author KeMo
 */

public class MyModel extends AbstractTableModel {

    private String[] columnNames;
    // Data
    private Object[][] data;

    public MyModel(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;
    }

    public void setData(Object[][] data) {
        this.data = data;       //Save data..
        fireTableDataChanged(); //Update table
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);

    }
}
