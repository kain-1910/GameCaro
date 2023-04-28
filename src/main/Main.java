package main;

import javax.swing.UIManager;

public class Main {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Game();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
