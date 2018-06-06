
package app;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;



/**
 * 
 * Custom list model class
 * @since 22 may 2018 
 	 * @author Karol Ostrowski
	 * @version 1.0.0
	 * 
	 *
 */
public class ListModel extends AbstractListModel<String>{
	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Values in list
	 */
	String[] values = new String[] {"Sum", "Average", "Min / Max"};
	
	/**
	 * Value getter
	 * @return value ate given position
	 * @param arg0 index of list element
	 */
	public String getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return values[arg0];
	}

	/**
	 * Size getter
	 * @return amount of elements in list
	 */
	public int getSize() {
		// TODO Auto-generated method stub
		return values.length;
	}
	
	/**Method which activates events
	 * @param selectedValue - value which was selected
	 * @param resultArea - textArea in which info is print in application
	 * @param tModel - table model
	 */
	public void activate(Integer selectedValue, JTextArea resultArea, tableModel tModel) {
		if (values[selectedValue] == "Sum") {
			resultArea.append("Sum: " + tModel.sumUp() + "\n");
		}
		if (values[selectedValue] == "Average") {
			resultArea.append("Average: " + tModel.calcAvg() + "\n");
		}
		if (values[selectedValue] == "Min / Max") {
			double[] res = tModel.findMinMax();
			resultArea.append("Min: " + res[0] + " Max: " + res[1] +"\n");
		}
		
	}
	
}
