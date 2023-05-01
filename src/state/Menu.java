package state;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Cell;
import main.Game;


public class Menu extends JFrame implements ActionListener{
	private Game game;
	private JCheckBox playerX, playerO, easy, medium, hard;
	private JButton startGame, muteSong, muteEffect;
	private JPanel menuPanel, chooseLevel, choosePlayer, muteButton;
	private ButtonGroup groupPlayer, groupLevel;
	private BufferedImage img;
	private JLabel label, labelLevel;
	
	public Menu(Game game) {
		this.game = game;
		
		createMenu();
	}

	private void createMenu() {
		playerO = new JCheckBox("Chọn O");
		playerX = new JCheckBox("Chọn X");
		easy = new JCheckBox("Easy");
		medium = new JCheckBox("Medium");
		hard = new JCheckBox("Hard");
		startGame = new JButton("Start");
		muteSong = new JButton("Mute song");
		muteEffect = new JButton("Mute effect");
		label = new JLabel("Decide on the first move????");
		labelLevel = new JLabel("Default is hard");
		chooseLevel = new JPanel();
		choosePlayer = new JPanel();
		menuPanel = new JPanel();
		muteButton = new JPanel();
		groupPlayer = new ButtonGroup();
		groupLevel = new ButtonGroup();
		
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		Font f = new Font("Ink Free", Font.BOLD, 18);
		
		playerO.setFont(f);
		playerO.addActionListener(this);
		playerX.setFont(f);
		playerX.addActionListener(this);
		startGame.setFont(f);
		startGame.addActionListener(this);
		easy.setFont(f);
		easy.addActionListener(this);
		medium.setFont(f);
		medium.addActionListener(this);
		hard.setFont(f);
		hard.addActionListener(this);
		muteSong.setFont(f);
		muteSong.addActionListener(this);
		muteEffect.setFont(f);
		muteEffect.addActionListener(this);
		
		groupPlayer.add(playerO);
		groupPlayer.add(playerX);
		
		groupLevel.add(easy);
		groupLevel.add(medium);
		groupLevel.add(hard);
		
		label.setFont(new Font("Ink Free", Font.BOLD, 20));
		label.setForeground(Color.YELLOW);
		label.setPreferredSize(new Dimension(300, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(Color.GRAY);
		
		labelLevel.setFont(new Font("Ink Free", Font.BOLD, 20));
		labelLevel.setForeground(Color.YELLOW);
		labelLevel.setPreferredSize(new Dimension(300, 50));
		labelLevel.setHorizontalAlignment(JLabel.CENTER);
		labelLevel.setVerticalAlignment(JLabel.CENTER);
		labelLevel.setOpaque(true);
		labelLevel.setBackground(Color.GRAY);
		
		
		chooseLevel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		chooseLevel.add(easy);
		chooseLevel.add(medium);
		chooseLevel.add(hard);
		
		choosePlayer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		choosePlayer.add(playerO);
		choosePlayer.add(playerX);
	
		muteButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		muteButton.setBackground(Color.GRAY);
		muteButton.add(muteSong);
		muteButton.add(muteEffect);

		menuPanel.setLayout(new GridLayout(4, 1,10, 10));
		menuPanel.add(choosePlayer);
		menuPanel.add(labelLevel);
		menuPanel.add(chooseLevel);
		menuPanel.add(muteButton);
		
//		xóa viền đen mặc định khi click vào
		playerO.setFocusPainted(false);
		playerX.setFocusPainted(false);
		startGame.setFocusPainted(false);
		easy.setFocusPainted(false);
		medium.setFocusPainted(false);
		hard.setFocusPainted(false);
		muteSong.setFocusPainted(false);
		muteEffect.setFocusPainted(false);
		
		add(label, BorderLayout.NORTH);
		add(menuPanel, BorderLayout.CENTER);
		add(startGame, BorderLayout.SOUTH);
		
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		
		if(src.equals("Start")) {
			this.game.getGameWindow().setVisible(true);
			this.dispose();
		}
	
		
		if(playerO.isSelected()) {
			this.game.getGameWindow().getGamepanel().getBoard().setCurrentPlayer(Cell.O_VALUE);
			label.setText("O moves first");
		}
		
		if(playerX.isSelected()) {
			this.game.getGameWindow().getGamepanel().getBoard().setCurrentPlayer(Cell.X_VALUE);
			label.setText("X moves first");
		}
		
		if(easy.isSelected()) {
			this.game.getGameWindow().getGamepanel().getBoard().setQuantityChar(3);
			labelLevel.setText("You choose level easy");
		}
		if(medium.isSelected()) {
			this.game.getGameWindow().getGamepanel().getBoard().setQuantityChar(4);
			labelLevel.setText("You choose level medium");
		}
		if(hard.isSelected()) {
			this.game.getGameWindow().getGamepanel().getBoard().setQuantityChar(5);
			labelLevel.setText("You choose level hard");
		}
		
		if(src.equals("Mute song")) {
			this.game.muteSong();
		}
		
		if(src.equals("Mute effect")) {
			this.game.getGameWindow().getGamepanel().getBoard().muteEffect();
		}
	}
	
}
