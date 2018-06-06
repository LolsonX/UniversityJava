
package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

/**
 *@since 22 may 2018
 	 * @author Karol Ostrowski
	 * @version 1.0.0
	 * 
	 *
 */
public class AboutDialog extends JDialog {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Main content panel
	 */
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 * @param args - console arguments
	 */
	public static void main(String[] args) {
		try {
			AboutDialog dialog = new AboutDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setType(Type.POPUP);
		setTitle("About Window");
		setEnabled(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{155, 0, 0, 234, 0};
		gbl_contentPanel.rowHeights = new int[]{14, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel label = new JLabel("Calculate Sheet");
			label.setFont(new Font("Tahoma", Font.BOLD, 17));
			label.setIcon(new ImageIcon(AboutDialog.class.getResource("/app/logo.png")));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 2;
			gbc_label.gridy = 1;
			contentPanel.add(label, gbc_label);
		}
		{
			JLabel lblAuthorKarollolson = new JLabel("Author: Karol \"Lolson\" Ostrowski");
			lblAuthorKarollolson.setFont(new Font("Tahoma", Font.PLAIN, 17));
			GridBagConstraints gbc_lblAuthorKarollolson = new GridBagConstraints();
			gbc_lblAuthorKarollolson.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblAuthorKarollolson.insets = new Insets(0, 0, 5, 5);
			gbc_lblAuthorKarollolson.gridx = 2;
			gbc_lblAuthorKarollolson.gridy = 2;
			contentPanel.add(lblAuthorKarollolson, gbc_lblAuthorKarollolson);
		}
		{
			JLabel lblProjectFor = new JLabel("Project for Designig Computer Applications 2018");
			lblProjectFor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblProjectFor = new GridBagConstraints();
			gbc_lblProjectFor.insets = new Insets(0, 0, 5, 5);
			gbc_lblProjectFor.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblProjectFor.gridx = 2;
			gbc_lblProjectFor.gridy = 3;
			contentPanel.add(lblProjectFor, gbc_lblProjectFor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(SystemColor.inactiveCaption);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
