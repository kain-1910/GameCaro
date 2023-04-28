package main;

import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	private Board board;
	private ButtonControl btnControl;
	private Game game;
	public GamePanel(Game game) {
		board = new Board();
		btnControl = new ButtonControl(board);
		this.game = game;
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);
		add(board);
		add(btnControl);
	}
	public Board getBoard() {
		return board;
	}


	
}
