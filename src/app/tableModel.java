package app;

import javax.swing.table.AbstractTableModel;


/**
 * 
 * Custom table model class 
 *
 * @since 22 may 2018
 * @author Karol Ostrowski
 * @version 1.0.0
 * 
 *
 */
public class tableModel extends AbstractTableModel
{
	/**
	 * 
	 * @author Karol Ostrowski
	 * @version 1.0.0
	 * @date: 22 may 2018
	 *
	 */
	/**
	 * VersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Number of columns
	 */
	private final int liczbaKolumn = 5;
	/**
	 * Number of  rows
	 */
	private final int liczbaWierszy = 5;
	/**
	 * Table size
	 */
	private Object[][] daneTabeli = new Object[liczbaWierszy][liczbaKolumn];
	/**
	 * Column names
	 */
	String[] nazwyKolumn = {"A", "B", "C", "D", "E"};
	
	/**
	 * Constructor
	 */
	public tableModel()
	{
		zeros();
	}
	
	/**
	 * @return number of columns
	 */
	@Override
	public int getColumnCount() 
	{
		return liczbaKolumn;
	}
	/**
	 * @return number of rows
	 */
	@Override
	public int getRowCount() 
	{
		return liczbaWierszy;
	}
	/**Method to get a name of given column
	 * @param indeks - index of column 
	 * @return title of given column
	 */
	public String getColumnName(int indeks)
	{
		return nazwyKolumn[indeks];
	}
	
	/**
	 * Value getter
	 * @param wiersz - row
	 * @param kolumna - column
	 * @return value of given position
	 */
	@Override
	public Object getValueAt(int wiersz, int kolumna) 
	{
		Object object = daneTabeli[wiersz][kolumna];
		return object;
	}
	/**
	 * Value setter
	 * @param wartosc - value we want to set
	 * @param wiersz - row in which we are setting
	 * @param kolumna - column where we are setting
	 */
	@Override
	public void setValueAt(Object wartosc, int wiersz, int kolumna)
	{
		daneTabeli[wiersz][kolumna] = wartosc;
	}
	/**
	 * Method which sets zeros in whole table
	 */
	public void zeros()
	{
		for(int i=0; i<liczbaWierszy; i++)
		{
			for(int j=0; j<liczbaKolumn; j++)
			{
				daneTabeli[i][j] = 0.0;
			}
		}
	}
	/**
	 * Method setting 0 in given position
	 * @param wiersz - row in whci we are setting
	 * @param kolumna - column in which we are setting
	 */
	public void setZeroAt(int wiersz, int kolumna)
	{
		daneTabeli[wiersz][kolumna] = 0;
	}
	/**
	 * Method Findin Min and max
	 * @return Min at 0 index max at 1 index
	 */
	public double[] findMinMax() {
		double[] minmax = {Double.parseDouble(getValueAt(0, 0).toString()), Double.parseDouble(getValueAt(0, 0).toString())};   
		for (int i=0; i<getRowCount(); i++) {
			for (int j=0; j<getColumnCount(); j++) {
				if (Double.parseDouble(getValueAt(i, j).toString()) < minmax[0]) {
					minmax[0] = Double.parseDouble(getValueAt(i, j).toString());
				}
				else if(Double.parseDouble(getValueAt(i, j).toString())> minmax[1]) {
					minmax[1] = Double.parseDouble(getValueAt(i, j).toString());
				}
			}
		}
		return minmax;
	}
	/**
	 * Method Sums values in table
	 * @return Sum of table elements
	 */
	public double sumUp() {
	double wynik = 0;
	for (int i=0; i<getRowCount(); i++) {
		for (int j=0; j<getColumnCount(); j++) {
			wynik = wynik + Double.parseDouble(getValueAt(i, j).toString());
		}
	}
	return wynik;
	
	}
	/**
	 * Method calculates average
	 * @return average value
	 */
	public double calcAvg() {
		double sum = sumUp();
		int elems = getColumnCount() * getRowCount();
		return sum/elems;
	}
}
