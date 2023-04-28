package state;

import static contants.Contants.BoardContants.*;
import static contants.Contants.State.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
import main.GameWindow;

public class Menu extends JFrame implements ActionListener{
	private Game game;
	private JCheckBox playerX, playerO;
	private JButton startGame;
	private JPanel choosePlayer;
	private ButtonGroup group;
	private BufferedImage img;
	private JLabel label;
	
	public Menu(Game game) {
		this.game = game;
		
		createMenu();
	}

	private void createMenu() {
		playerO = new JCheckBox("Chọn O");
		playerX = new JCheckBox("Chọn X");
		startGame = new JButton("Start");
		label = new JLabel("Choose????");
//		vẽ hình vào jpanel
		choosePlayer = new JPanel() {
			 @Override
	            public void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                
	                try {
						img = ImageIO.read(new FileInputStream("img/bg.jpg"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                g.drawImage(img, 0, 0, 500, 500, null);
	            }
		};
		group = new ButtonGroup();
		
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
		
		
		
		group.add(playerO);
		group.add(playerX);
		
		label.setFont(new Font("Ink Free", Font.BOLD, 20));
		label.setForeground(Color.YELLOW);
		label.setPreferredSize(new Dimension(300, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(Color.GRAY);
		
		
		
		choosePlayer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		choosePlayer.add(playerO);
		choosePlayer.add(playerX);
		
//		xóa viền đen mặc định khi click vào
		playerO.setFocusPainted(false);
		playerX.setFocusPainted(false);
		startGame.setFocusPainted(false);
	
		
		add(label, BorderLayout.NORTH);
		add(choosePlayer, BorderLayout.CENTER);
		add(startGame, BorderLayout.SOUTH);
		
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		
		if(src.equals("Start")) {
			this.game.getGameWindow().setVisible(true);
			this.game.setState(PLAYING);
		}
		
		if(playerO.isSelected()) {
			this.game.getGameWindow().getGamepanel().getBoard().setCurrentPlayer(Cell.O_VALUE);
			label.setText("You choose O");
		}
		
		if(playerX.isSelected()) {
			this.game.getGameWindow().getGamepanel().getBoard().setCurrentPlayer(Cell.X_VALUE);
			label.setText("You choose X");
		}
	}
	
}
