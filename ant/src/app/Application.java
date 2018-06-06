/**
 * Date: 22 may 2018
   Project Project for University
   @Author: Karol "Lolson" Ostrowski
   @version: 3.0.0
   Licence: MIT
 */
package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import org.freixas.jcalendar.JCalendarCombo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import app.AboutDialog;
import org.freixas.jcalendar.DateListener;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.freixas.jcalendar.DateEvent;

/**
 * 
 * Main Application class
 * 
 * @since 22 may 2018
 * @author Karol Ostrowski
 * @version 1.0.0
 * 
 *
 */
public class Application {
	/**
	 * Application frame
	 */
	private JFrame frmApplication;
	/**
	 * Data table
	 */
	private JTable numbers;
	/**
	 * Text field in which user set value
	 */
	private JTextField fldValue;
	/**
	 * Model for JTable
	 */
	private tableModel model;
	/**
	 * Model for list
	 */
	private ListModel listModel;
	/**
	 * Tips in the Tip Of The Day window
	 */
	public JTipOfTheDay porady;
	/**
	 * Frame for drawing a graph
	 */
	public ChartFrame frame2;
	/**
	 * Logger to save info about what happened in app
	 */
	public Logger logger; 
	/**
	 * Additional variable helping handling date chang event
	 */
	boolean fired = false;
	/**
	 * Additional variable helping handling List Value changed event
	 */
	boolean listEventFired = false;
	/**
	 * Launch the application.
	 * @param args - Console arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Application window = new Application();
					window.frmApplication.setVisible(true);
					window.showTip();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		});
	}
	/**
	 * Method which displays Tip of the day
	 */
	private void showTip() {
		// TODO Auto-generated method stub
		DefaultTipModel spis = new DefaultTipModel();
		spis.add(new DefaultTip("Tip1", "Tresc porady 1"));
		spis.add(new DefaultTip("Tip2", "Tresc porady 2"));
		spis.add(new DefaultTip("Tip3", "Tresc porady 3"));
		spis.add(new DefaultTip("Tip4", "Tresc porady 4"));
		JTipOfTheDay porady = new JTipOfTheDay(spis);
		porady.showDialog(null);
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
		
	}
	/**
	 * Initialize the contents of the frame and their properties.
	 */
	private void initialize() {
		frmApplication = new JFrame();
		frmApplication.setMinimumSize(new Dimension(800, 600));
		frmApplication.setBackground(SystemColor.activeCaption);
		frmApplication.setTitle("Application");
		frmApplication.setBounds(100, 100, 640, 480);
		frmApplication.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frmApplication.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				close();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		logger = Logger.getLogger("logger");
		DOMConfigurator.configure("conf//config.xml");
		logger.info("Application started");
		
		JButton taskBtnSave = new JButton ("Save");
		taskBtnSave.setForeground(new Color(0, 0, 0));
		taskBtnSave.setBackground(Color.WHITE);
		taskBtnSave.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnSave.setIcon(new ImageIcon(Application.class.getResource("/app/save.png")));
		taskBtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveToFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JButton taskBtnSubmit = new JButton ("Submit");
		taskBtnSubmit.setForeground(new Color(0, 0, 0));
		taskBtnSubmit.setBackground(Color.WHITE);
		taskBtnSubmit.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnSubmit.setIcon(new ImageIcon(Application.class.getResource("/app/set.png")));
		
		JButton taskBtnZero = new JButton ("Fill 0s");
		taskBtnZero.setForeground(new Color(0, 0, 0));
		taskBtnZero.setBackground(Color.WHITE);
		taskBtnZero.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnZero.setIcon(new ImageIcon(Application.class.getResource("/app/zero.png")));
		taskBtnZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setZeros();
			}
		});
		JButton taskBtnSum = new JButton ("Sum");
		taskBtnSum.setForeground(new Color(0, 0, 0));
		taskBtnSum.setBackground(Color.WHITE);
		taskBtnSum.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnSum.setIcon(new ImageIcon(Application.class.getResource("/app/sum.png")));
		
		JButton taskBtnAverage = new JButton ("Average");
		taskBtnAverage.setForeground(new Color(0, 0, 0));
		taskBtnAverage.setBackground(Color.WHITE);
		taskBtnAverage.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnAverage.setIcon(new ImageIcon(Application.class.getResource("/app/avg.png")));
		
		JButton taskBtnMinMax = new JButton ("Min Max");
		taskBtnMinMax.setForeground(new Color(0, 0, 0));
		taskBtnMinMax.setBackground(Color.WHITE);
		taskBtnMinMax.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnMinMax.setIcon(new ImageIcon(Application.class.getResource("/app/minmax.png")));
		
		JButton taskBtnAbout = new JButton ("About...");
		taskBtnAbout.setForeground(new Color(0, 0, 0));
		taskBtnAbout.setBackground(Color.WHITE);
		taskBtnAbout.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnAbout.setIcon(new ImageIcon(Application.class.getResource("/app/About.png")));
		taskBtnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openAbout();
			}
		});
		JButton taskBtnHelp = new JButton ("Help");
		taskBtnHelp.setForeground(new Color(0, 0, 0));
		taskBtnHelp.setBackground(Color.WHITE);
		taskBtnHelp.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnHelp.setIcon(new ImageIcon(Application.class.getResource("/app/help.png")));
		taskBtnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openHelp();
			}
		});
		
		JButton taskBtnGraph = new JButton ("Draw Graph");
		taskBtnGraph.setForeground(new Color(0, 0, 0));
		taskBtnGraph.setBackground(Color.WHITE);
		taskBtnGraph.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnGraph.setIcon(new ImageIcon(Application.class.getResource("/app/graph.png")));
		
		JButton taskBtnExit = new JButton ("Exit");
		taskBtnExit.setForeground(new Color(0, 0, 0));
		taskBtnExit.setBackground(Color.WHITE);
		taskBtnExit.setHorizontalAlignment(SwingConstants.LEADING);
		taskBtnExit.setIcon(new ImageIcon(Application.class.getResource("/app/exit.png")));
		taskBtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		JTaskPane taskPane = new JTaskPane();
		taskPane.add(taskBtnSave);
		taskPane.add(taskBtnSubmit);
		taskPane.add(taskBtnZero);
		taskPane.add(taskBtnSum);
		taskPane.add(taskBtnAverage);
		taskPane.add(taskBtnMinMax);
		taskPane.add(taskBtnGraph);
		taskPane.add(taskBtnAbout);
		taskPane.add(taskBtnHelp);
		taskPane.add(taskBtnExit);
		frmApplication.getContentPane().add(taskPane, BorderLayout.WEST);
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.WHITE);
		frmApplication.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnExit = new JButton("");
		btnExit.setBackground(Color.WHITE);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		ImageIcon exitIcon = new ImageIcon(Application.class.getResource("/app/exit.png"));
		
		JButton saveBtn = new JButton("");
		saveBtn.setBackground(Color.WHITE);
		
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveToFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showOptionDialog(null, "Failed to save", "Error", JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE, null, null, "Ok");
					logger.error("Error while saving!");
				}
			}
		});
		saveBtn.setIcon(new ImageIcon(Application.class.getResource("/app/save.png")));
		toolBar.add(saveBtn);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setMaximumSize(new Dimension(10, 32767));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);
		
		JButton btnTbSet = new JButton("");
		btnTbSet.setBackground(Color.WHITE);
		btnTbSet.setIcon(new ImageIcon(Application.class.getResource("/app/set.png")));
		toolBar.add(btnTbSet);
		
		JButton btnTbZero = new JButton("");
		btnTbZero.setBackground(Color.WHITE);
		btnTbZero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setZeros();
			}
		});
		btnTbZero.setIcon(new ImageIcon(Application.class.getResource("/app/zero.png")));
		toolBar.add(btnTbZero);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.WHITE);
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setMaximumSize(new Dimension(10, 32767));
		toolBar.add(separator_4);
		
		JButton btnTbavg = new JButton("");
		btnTbavg.setBackground(Color.WHITE);
		
		btnTbavg.setIcon(new ImageIcon(Application.class.getResource("/app/avg.png")));
		toolBar.add(btnTbavg);
		
		JButton btnTbsum = new JButton("");
		btnTbsum.setBackground(Color.WHITE);
		btnTbsum.setIcon(new ImageIcon(Application.class.getResource("/app/sum.png")));
		toolBar.add(btnTbsum);
		
		JButton btnTbminmax = new JButton("");
		btnTbminmax.setBackground(Color.WHITE);
		btnTbminmax.setIcon(new ImageIcon(Application.class.getResource("/app/minmax.png")));
		toolBar.add(btnTbminmax);
		
		JButton tbBtnGraph = new JButton("");
		
		
		tbBtnGraph.setIcon(new ImageIcon(Application.class.getResource("/app/graph.png")));
		tbBtnGraph.setBackground(Color.WHITE);
		toolBar.add(tbBtnGraph);
		
		JButton btnTbabout = new JButton("");
		btnTbabout.setBackground(Color.WHITE);
		btnTbabout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openAbout();
			}
		});
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.WHITE);
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setMaximumSize(new Dimension(10, 32767));
		toolBar.add(separator_5);
		btnTbabout.setIcon(new ImageIcon(Application.class.getResource("/app/About.png")));
		toolBar.add(btnTbabout);
		
		JButton btnTbhelp = new JButton("");
		btnTbhelp.setBackground(Color.WHITE);
		btnTbhelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openHelp();
			}
		});
		btnTbhelp.setIcon(new ImageIcon(Application.class.getResource("/app/help.png")));
		toolBar.add(btnTbhelp);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.WHITE);
		separator_6.setPreferredSize(new Dimension(1, 2));
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setMaximumSize(new Dimension(10, 32767));
		toolBar.add(separator_6);
		btnExit.setIcon(exitIcon);
		toolBar.add(btnExit);
		
		JPanel statusBar = new JPanel();
		statusBar.setBackground(SystemColor.inactiveCaption);
		frmApplication.getContentPane().add(statusBar, BorderLayout.SOUTH);
		statusBar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel statusLabel = new JLabel("Ready");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusBar.add(statusLabel);
		
		JPanel centralWidget = new JPanel();
		centralWidget.setBackground(SystemColor.activeCaption);
		frmApplication.getContentPane().add(centralWidget, BorderLayout.CENTER);
		GridBagLayout gbl_centralWidget = new GridBagLayout();
		gbl_centralWidget.columnWidths = new int[]{250, 340, 0, 0};
		gbl_centralWidget.rowHeights = new int[]{20, 370, 0, 20};
		gbl_centralWidget.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_centralWidget.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		centralWidget.setLayout(gbl_centralWidget);
		
		JPanel controlPane = new JPanel();
		controlPane.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_controlPane = new GridBagConstraints();
		gbc_controlPane.fill = GridBagConstraints.BOTH;
		gbc_controlPane.insets = new Insets(0, 0, 5, 5);
		gbc_controlPane.gridx = 0;
		gbc_controlPane.gridy = 1;
		centralWidget.add(controlPane, gbc_controlPane);
		GridBagLayout gbl_controlPane = new GridBagLayout();
		gbl_controlPane.columnWidths = new int[] {2, 0, 168, 0, 30};
		gbl_controlPane.rowHeights = new int[] {30, 30, 0, 30, 30, 30, 0};
		gbl_controlPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_controlPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		controlPane.setLayout(gbl_controlPane);
		
		JCalendarCombo calendar = new JCalendarCombo();
		calendar.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.gridx = 2;
		gbc_calendar.gridy = 5;
		controlPane.add(calendar, gbc_calendar);
		
		JLabel lblRow = new JLabel("Row");
		GridBagConstraints gbc_lblRow = new GridBagConstraints();
		gbc_lblRow.anchor = GridBagConstraints.EAST;
		gbc_lblRow.fill = GridBagConstraints.VERTICAL;
		gbc_lblRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblRow.gridx = 1;
		gbc_lblRow.gridy = 0;
		controlPane.add(lblRow, gbc_lblRow);
		
		JSpinner spnRow = new JSpinner();
		spnRow.setBackground(SystemColor.inactiveCaptionBorder);
		spnRow.setModel(new SpinnerNumberModel(0, 0, 4, 1));
		GridBagConstraints gbc_spnRow = new GridBagConstraints();
		gbc_spnRow.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnRow.insets = new Insets(0, 0, 5, 0);
		gbc_spnRow.gridx = 2;
		gbc_spnRow.gridy = 0;
		controlPane.add(spnRow, gbc_spnRow);
		
		JLabel lblColumn = new JLabel("Column");
		GridBagConstraints gbc_lblColumn = new GridBagConstraints();
		gbc_lblColumn.anchor = GridBagConstraints.EAST;
		gbc_lblColumn.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumn.fill = GridBagConstraints.VERTICAL;
		gbc_lblColumn.gridx = 1;
		gbc_lblColumn.gridy = 1;
		controlPane.add(lblColumn, gbc_lblColumn);
		
		JSpinner spnCol = new JSpinner();
		spnCol.setForeground(SystemColor.window);
		spnCol.setBackground(SystemColor.inactiveCaptionBorder);
		spnCol.setModel(new SpinnerNumberModel(0, 0, 4, 1));
		GridBagConstraints gbc_spnCol = new GridBagConstraints();
		gbc_spnCol.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnCol.insets = new Insets(0, 0, 5, 0);
		gbc_spnCol.gridx = 2;
		gbc_spnCol.gridy = 1;
		controlPane.add(spnCol, gbc_spnCol);
		
		JLabel lblValue = new JLabel("Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.anchor = GridBagConstraints.EAST;
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 1;
		gbc_lblValue.gridy = 2;
		controlPane.add(lblValue, gbc_lblValue);
		
		fldValue = new JTextField();
		fldValue.setHorizontalAlignment(SwingConstants.RIGHT);
		fldValue.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_fldValue = new GridBagConstraints();
		gbc_fldValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldValue.insets = new Insets(0, 0, 5, 0);
		gbc_fldValue.gridx = 2;
		gbc_fldValue.gridy = 2;
		controlPane.add(fldValue, gbc_fldValue);
		
		JButton btnZero = new JButton("Zero");
		btnZero.setBackground(SystemColor.activeCaption);
		btnZero.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setZeros();
			}
		});
		GridBagConstraints gbc_btnZero = new GridBagConstraints();
		gbc_btnZero.anchor = GridBagConstraints.WEST;
		gbc_btnZero.fill = GridBagConstraints.NONE;
		gbc_btnZero.insets = new Insets(0, 0, 5, 5);
		gbc_btnZero.gridx = 2;
		gbc_btnZero.gridy = 3;
		controlPane.add(btnZero, gbc_btnZero);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(SystemColor.activeCaption);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int x = Integer.parseInt(spnRow.getValue().toString());
					int y = Integer.parseInt(spnCol.getValue().toString());
					newValue(x, y);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Incorrect value! Must be a number", "Error",JOptionPane.ERROR_MESSAGE);
					logger.warn("User tried insert incorrect value!");
				}
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.anchor = GridBagConstraints.EAST;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 3;
		controlPane.add(btnSubmit, gbc_btnSubmit);
		
		JLabel lblOperation = new JLabel("Operations");
		GridBagConstraints gbc_lblOperation = new GridBagConstraints();
		gbc_lblOperation.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblOperation.insets = new Insets(0, 0, 5, 5);
		gbc_lblOperation.gridx = 1;
		gbc_lblOperation.gridy = 4;
		controlPane.add(lblOperation, gbc_lblOperation);
		
		JList <String> operationList = new JList<String>();
		operationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		operationList.setVisibleRowCount(3);
		operationList.setBorder(new LineBorder(SystemColor.inactiveCaption, 1, true));
		operationList.setBackground(SystemColor.inactiveCaptionBorder);
		listModel = new ListModel();
		operationList.setModel(listModel);
		GridBagConstraints gbc_operationList = new GridBagConstraints();
		gbc_operationList.anchor = GridBagConstraints.NORTH;
		gbc_operationList.fill = GridBagConstraints.HORIZONTAL;
		gbc_operationList.insets = new Insets(0, 0, 5, 0);
		gbc_operationList.gridx = 2;
		gbc_operationList.gridy = 4;
		controlPane.add(operationList, gbc_operationList);
		
		JPanel resultPane = new JPanel();
		resultPane.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_resultPane = new GridBagConstraints();
		gbc_resultPane.insets = new Insets(0, 0, 5, 5);
		gbc_resultPane.anchor = GridBagConstraints.WEST;
		gbc_resultPane.fill = GridBagConstraints.BOTH;
		gbc_resultPane.gridx = 1;
		gbc_resultPane.gridy = 1;
		centralWidget.add(resultPane, gbc_resultPane);
		GridBagLayout gbl_resultPane = new GridBagLayout();
		gbl_resultPane.columnWidths = new int[] {375};
		gbl_resultPane.rowHeights = new int[] {286, 215, 0};
		gbl_resultPane.columnWeights = new double[]{1.0};
		gbl_resultPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		resultPane.setLayout(gbl_resultPane);
		
		numbers = new JTable();
		numbers.setBackground(SystemColor.inactiveCaptionBorder);
		numbers.setBorder(new LineBorder(SystemColor.controlShadow));
		numbers.setRowHeight(50);
		GridBagConstraints gbc_numbers = new GridBagConstraints();
		gbc_numbers.insets = new Insets(0, 0, 5, 0);
		gbc_numbers.fill = GridBagConstraints.BOTH;
		gbc_numbers.gridx = 0;
		gbc_numbers.gridy = 0;
		resultPane.add(numbers, gbc_numbers);
		model = new tableModel();
		numbers.setModel(model);
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		resultPane.add(scrollPane, gbc_scrollPane);
		
		JTextArea resultArea = new JTextArea();
		resultArea.setBackground(SystemColor.inactiveCaptionBorder);
		scrollPane.setViewportView(resultArea);
		resultArea.setFont(new Font("Candara", Font.PLAIN, 13));
		resultArea.setLineWrap(true);
		resultArea.setWrapStyleWord(true);
		resultArea.setEditable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frmApplication.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveToFile();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Failed to save", "Error",JOptionPane.ERROR_MESSAGE);
					logger.error("Failed when saving!");
				}
			}
		});
		mntmSave.setIcon(new ImageIcon(Application.class.getResource("/app/save.png")));
		mnFile.add(mntmSave);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		mntmExit.setIcon(new ImageIcon(Application.class.getResource("/app/exit.png")));
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmZero = new JMenuItem("Zero");
		mntmZero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setZeros();
			}
		});
		mnEdit.add(mntmZero);
		
		JMenuItem mntmSubmit = new JMenuItem("Submit");
		mntmSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int x = Integer.parseInt(spnRow.getValue().toString());
					int y = Integer.parseInt(spnCol.getValue().toString());
					newValue(x, y);
					}
					catch (Exception e){
						JOptionPane.showMessageDialog(null, "Incorrect value! Must be a number", "Error",JOptionPane.ERROR_MESSAGE);
						logger.warn("Usert tried to insert incorrect value");
					}
				}
		});
		mnEdit.add(mntmSubmit);
		
		JMenuItem mntmSum = new JMenuItem("Sum");
		mntmSum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resultArea.append("Sum: " + sumUp() + "\n");
			}
		});
		
		JSeparator separator_7 = new JSeparator();
		mnEdit.add(separator_7);
		
		JMenuItem mntmDrawGraph = new JMenuItem("Draw Graph");
		
		mnEdit.add(mntmDrawGraph);
		
		JSeparator separator_3 = new JSeparator();
		mnEdit.add(separator_3);
		mnEdit.add(mntmSum);
		
		JMenuItem mntmAverage = new JMenuItem("Average");
		mntmAverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resultArea.append("Average: " + calcAvg() + "\n");
			}
		});
		mnEdit.add(mntmAverage);
		
		JMenuItem mntmMinmax = new JMenuItem("Min/Max");
		mntmMinmax.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double[] res = findMinMax();
				resultArea.append("Min: " + res[0] + " Max: " + res[1] +"\n");
			}
		});
		mnEdit.add(mntmMinmax);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About...");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openAbout();
			}
		});
		mnHelp.add(mntmAbout);
		
		JSeparator separator_2 = new JSeparator();
		mnHelp.add(separator_2);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openHelp();
			}
		});
		mnHelp.add(mntmHelp);
		
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Save");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
			}
		});
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Exit");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
			}
		});
		btnTbavg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resultArea.append("Average: " + calcAvg() + "\n");
			}
		});
		btnTbsum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resultArea.append("Sum: " + sumUp() + "\n");
			}
		});
		btnTbminmax.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double[] res = findMinMax();
				resultArea.append("Min: " + res[0] + " Max: " + res[1] +"\n");
			}
		});
		operationList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(!listEventFired) {
				listModel.activate(operationList.getSelectedIndex(), resultArea, model);
				listEventFired = true;
				logger.info("List value changed");
				}
				else {
					listEventFired = false;
				}
			}
		});
		btnTbSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int x = Integer.parseInt(spnRow.getValue().toString());
					int y = Integer.parseInt(spnCol.getValue().toString());
					newValue(x, y);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Incorrect value! Must be a number", "Error",JOptionPane.ERROR_MESSAGE);
					logger.warn("User tried to enter invalid value");
				}
			}
		});
		btnTbSet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Insert value to table");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
				
			}
		});
		btnTbabout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("About author");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
				
			}
		});
		btnTbavg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Calculate average");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
				
			}
		});
		btnTbhelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Open help");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
				
			}
		});
		btnTbminmax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Find minimum and maximum");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
				
			}
		});
		btnTbsum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Sum elements in table");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
				
			}
		});
		btnTbZero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Fill table with 0");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
				
			}
		});
		
		taskBtnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int x = Integer.parseInt(spnRow.getValue().toString());
					int y = Integer.parseInt(spnCol.getValue().toString());
					newValue(x, y);
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Incorrect value! Must be a number", "Error",JOptionPane.ERROR_MESSAGE);
					logger.warn("User tried to enter incorrect value");
				}
			}
		});
		
		taskBtnSum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resultArea.append("Sum: " + sumUp() + "\n");
			}
		});
		
		taskBtnAverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resultArea.append("Average: " + calcAvg() + "\n");
			}
		});
		
		taskBtnMinMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double[] res = findMinMax();
				resultArea.append("Min: " + res[0] + " Max: " + res[1] +"\n");
			}
		});
		
		calendar.addDateListener(new DateListener() {
			public void dateChanged(DateEvent arg0) {
				if(fired) {
					String date_string = "";
					String month_string = "";
					String day_string = "";
					Date date = calendar.getDate();
					Integer year = date.getYear()+1900;
					Integer month = date.getMonth()+1;
					Integer day = date.getDate();
				
					if(month<10)
					{
						month_string = "0" + String.valueOf(month);
					}
					else
					{
						month_string = String.valueOf(month);
					}
				
					if(day<10)
					{
						day_string = "0" + String.valueOf(day);
					}
					else
					{
						day_string = String.valueOf(day);
					}
				
					date_string = String.valueOf(year) + "-" + month_string + "-" + day_string;
				
					resultArea.append("Selected new date: "+ date_string + "\n");
					logger.info("Date changed");
					return;
				}
				else {fired = !fired;}
			}
		});
		
		tbBtnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawGraph();
			}
		});
		
		tbBtnGraph.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				statusLabel.setText("Draw graph");
			}
			
			public void mouseExited(MouseEvent arg0) {
				statusLabel.setText("Ready");
			}
		});
		
		mntmDrawGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawGraph();
			}
		});
		
		taskBtnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawGraph();
			}
		});
	}
	
	/**
	 * @param x - column number
	 * @param y = row number
	 * Method which sets a value on given position in table
	 */
	protected void newValue(int x,int y) {
		// TODO Auto-generated method stub
		String value = fldValue.getText();
		model.setValueAt(Double.parseDouble(value), x, y);
		logger.info("Value changed");
		refresh();
	}
	/**
	 * Method which refreshes view of table
	 */
	private void refresh() {
		numbers.selectAll();
		numbers.clearSelection();
	}
	/**
	 * Method which sets 0 in whole table
	 */
	protected void setZeros() {
		// TODO Auto-generated method stub
		model.zeros();
		logger.info("Zeros set");
		refresh();
	}
	/**
	 * Sums up all values in table
	 * @return Sum of values
	 */
	private double sumUp() {
		logger.info("Values summed");
		return model.sumUp();
	}
	/**
	 * Calcualtes average of values in table
	 * @return average value
	 */
	
	private double calcAvg() {
		logger.info("Average calculated");
		return model.calcAvg();
	}
	/**
	 * Method which found minimum and maximum value in table
	 * @return Min at 0 index and Max at 1 index
	 */
	private double[] findMinMax() {
		logger.info("Min Max found");
		return model.findMinMax();
	}
	/**
	 * Method which draws a graph form values in table
	 */
	private void drawGraph() {
		DefaultCategoryDataset datae = new DefaultCategoryDataset();
		
		for(int i=0; i<model.getColumnCount(); i++)
		{
			for(int j=0; j<model.getRowCount(); j++) 
			{
				datae.addValue((Number) model.getValueAt(i, j), i+1, model.getColumnName(j));
			}
		}
		// create a chart...
		JFreeChart chart = ChartFactory.createBarChart(
		"Chart",
		"Kolumna", 
		"Wartosc", 
		datae,
		PlotOrientation.VERTICAL,
		true,
		true,
		true
		);
		// create and display a frame...
		ChartFrame frame2 = new ChartFrame("Wykres Slupkowy", chart);
		frame2.pack();
		frame2.setSize(new Dimension(800,600));
		frame2.setVisible(true);
		logger.info("Graph generated");
	}
	/**
	 * Custom window close
	 */
	private void close() {
		int value = JOptionPane.showOptionDialog(null,"Do You want to exit?","Warning",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,new String[] {"Yes","No"},"Yes");
		if (value == JOptionPane.YES_OPTION) 
		{
			logger.info("Application stopped");
			frmApplication.dispose();
		}
	}
	/**
	 * Saving to file method
	 * @throws IOException - Exception thrown when saving failed
	 */
	private void saveToFile() throws IOException {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(frmApplication);
		File plik = fc.getSelectedFile();
		if (!plik.exists()) {
			plik.createNewFile();
			logger.info("New file created!");
		}
		FileWriter zapis = new FileWriter(plik, true);
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				zapis.write(model.getValueAt(i, j).toString() + " ");
			}
			zapis.write("\r\n");
		}
		zapis.close();
		logger.info("Saved succesfully");
	}
	/**
	 * Method which opens help
	 */
	private void openHelp() {
		try {
			String dir = "file:///" + System.getProperty("user.dir") + "/src/app/help.pdf";
			dir = dir.replace('\\', '/');
			URI url = new URI(dir);
			Desktop.getDesktop().browse(url);
			logger.info("Help opened");
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Failed to open help");
		}
	}
	/**
	 * Method which opens Dialog with App info
	 */
	private void openAbout() {
		AboutDialog d = new AboutDialog();
		d.setVisible(true);
		d.setEnabled(true);
		logger.info("About info opened");
	}
}
