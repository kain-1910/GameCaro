package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	private GamePanel gamepanel;
	public GameWindow(GamePanel gamePanel) {
		this.gamepanel = gamePanel;
		setTitle("Game Caro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(gamePanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}
	

	public GamePanel getGamepanel() {
		return gamepanel;
	}	
}
