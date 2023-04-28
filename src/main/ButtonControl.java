package main;

import static contants.Contants.BoardContants.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonControl extends JPanel implements ActionListener{
	private JButton btnRestart;
	private JLabel score;
	private JLabel turnPlayer;
	private JButton btnExit;
	private JPanel btnPanel, turnPanel;


	private Board board;
	
	public ButtonControl(Board board) {
		this.board = board;
		createLayout();
		updateturnPlayer();
		btnRestart.addActionListener(this);
		btnExit.addActionListener(this);
		
	}

	private void createLayout() {
		btnRestart = new JButton("New Game");
		turnPlayer = new JLabel("Turn :");
		score = new JLabel("Game Start");
		btnExit = new JButton("Exit");
		btnPanel = new JPanel();
		turnPanel = new JPanel();
		
		btnRestart.setPreferredSize(new Dimension((SIZE_CELL*BOARD_COL)/3, 50));
		btnExit.setPreferredSize(new Dimension((SIZE_CELL*BOARD_COL)/3, 50));
		
		btnPanel.setLayout(new BorderLayout());
		btnPanel.setPreferredSize(new Dimension(SIZE_CELL*BOARD_COL, 50));
		
		turnPanel.setLayout(new BorderLayout());
		turnPanel.setPreferredSize(new Dimension(SIZE_CELL*BOARD_COL, 40));
		turnPlayer.setForeground(Color.YELLOW);
		
		turnPanel.setBackground(Color.GRAY);
		
		Font font = new Font("Ink Free", Font.BOLD, 18);
		btnRestart.setFont(font);
		btnRestart.setFocusPainted(false);
		btnExit.setFont(font);
		btnExit.setFocusPainted(false);
		
		turnPlayer.setFont(new Font("Ink Free", Font.BOLD, 22));
		turnPlayer.setHorizontalAlignment(JLabel.CENTER);
		turnPlayer.setVerticalAlignment(JLabel.CENTER);
		
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setVerticalAlignment(JLabel.CENTER);
		score.setFont(font);
		
		btnPanel.add(score, BorderLayout.CENTER);
		btnPanel.add(btnRestart, BorderLayout.WEST);
		btnPanel.add(btnExit, BorderLayout.EAST);
		turnPanel.add(turnPlayer, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(btnPanel, BorderLayout.CENTER);
		add(turnPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		
		if(src.equals("New Game")) {
			board.resetGame();
			board.resetScore();
		}
		if(src.equals("Exit")) exitProgram();
		
	}

	private void exitProgram() {
		int option = new JOptionPane().showConfirmDialog(null, "Chạy thật sao", "Định chạy à", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		if(option == 0) System.exit(0);
		
	}
	
//	đổi text label
	private void updateturnPlayer() {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					turnPlayer.setText("Turn: " + board.getCurrentPlayer());
					changeScore(board.getScoreO(), board.getScoreX());
				}
			}
		});
		t.start();
	}
	
	public void changeScore(int x, int y) {
		this.score.setText(x + " : " + y);
	}
}
