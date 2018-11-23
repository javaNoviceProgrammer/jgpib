package jgpib.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import jgpib.util.GpibBus;
import jgpib.util.Log;

public class Console extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7220356960470815752L;
	private JMenuBar menuBar;
	private Log console;
	private JMenuItem printBus;
	private JMenuItem mntmRun;
	private JMenu mnFile;
	private JMenu mnConsole;
	private JPanel contentPane;
	private JSeparator separator;
	private JMenuItem mntmNewMenuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console frame = new Console();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Console() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("GPIB Controller V1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Console.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 323);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmRun = new JMenuItem("Run...");
		mntmRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						Laboratory.main(null);
						
					}
				});
			}
		});
		mnFile.add(mntmRun);
		
		mnConsole = new JMenu("Console");
		menuBar.add(mnConsole);
		
		printBus = new JMenuItem("Print GPIB Bus");
		printBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GpibBus bus = new GpibBus() ;
				console.log(bus.printBus());
			}
		});
		mnConsole.add(printBus);
		
		separator = new JSeparator();
		mnConsole.add(separator);
		
		mntmNewMenuItem = new JMenuItem("Log Path...");
		mnConsole.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		console = new Log();
		console.setLineWrap(true);
		console.setEditable(false);
		contentPane.add(console, BorderLayout.CENTER);
		
	}

}
