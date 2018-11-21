package jgpib.util;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Log extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2507251561413803653L;
	
	public Log() {
		setBackground(Color.WHITE);
		setEditable(false);
	}
	
	public void log(String message) {
		append(">> " + message+"\n");
		
	}
	
	public void logError(String message) {
		append(">> " + message+"\n");
	}
	
	// for test
	public static void main(String[] args) {
		JFrame frame = new JFrame("Logger") ;
		Log logger = new Log() ;
		frame.getContentPane().add(logger, null);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logger.log("Normal operation started...");
		logger.log("Normal operation started...");
		logger.logError("Test..");
		logger.log("Normal operation started...");
		logger.log("Normal operation started...");
	}

}
