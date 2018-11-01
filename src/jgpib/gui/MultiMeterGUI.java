package jgpib.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;

public class MultiMeterGUI extends JPanel {
	public MultiMeterGUI() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Multimeter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblNewLabel_2 = new JLabel("Name : ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txta = new JTextField();
		txta.setText("34401A");
		txta.setDisabledTextColor(Color.BLACK);
		txta.setBackground(Color.GREEN);
		txta.setEnabled(false);
		GridBagConstraints gbc_txta = new GridBagConstraints();
		gbc_txta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txta.gridx = 1;
		gbc_txta.gridy = 0;
		add(txta, gbc_txta);
		txta.setColumns(10);
		
		lblNewLabel = new JLabel("Order : ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("1");
		comboBox.addItem("2");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(5, 0, 5, 0);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		add(comboBox, gbc_comboBox);
		
		lblNewLabel_1 = new JLabel("Range : ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(5, 5, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3384851349182165434L;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txta;
	
	
	
	
	
	// for test
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Multimeter GUI") ;
		MultiMeterGUI gui = new MultiMeterGUI() ;
		frame.getContentPane().add(gui) ;
		frame.pack();
		frame.setVisible(true);
	}
	
	
	

}
