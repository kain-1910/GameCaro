package main;

import static contants.Contants.BoardContants.*;
import static contants.Contants.GameResult.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {
	private int scoreX = 0;
	private int scoreO = 0;
	private BufferedImage imgO, imgX;
	private Cell[][] matrix;
	private String currentPlayer = Cell.O_VALUE;
	private boolean[][] ticked;
	private int Gamestate = NORMAL;
	private Clip clip;
	
	public Board() {
		
		initMatrix();  
		setBoardSize();  
		loadImgs();     
   
		
		
//		add sự kiện chuột
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				
				int col = e.getX()/SIZE_CELL;
				int row = e.getY()/SIZE_CELL;
				soundEffect();
				
				if(ticked[row][col] == false) {
					matrix[row][col].setValue(currentPlayer);
					ticked[row][col] = true;
					repaint();
					
					if(checkWin(currentPlayer, row, col)) {
						updateScore();
						Gamestate = WIN;
					}
					if(isBoardFull()) Gamestate = DRAW;
					
					showResult(Gamestate);
					changeCurrentplayer();
					
				}
			}
		});
		
	}
	
	// show kết quả ván đấu
	private void showResult(int Gamestate) {
		if(Gamestate == WIN) {
			JOptionPane.showMessageDialog(null, currentPlayer + " là người chiến thắng");
			
			resetGame();
		}
		else if(Gamestate == DRAW) {
			JOptionPane.showMessageDialog(null, "Ván đấu hòa");
			
			resetGame();
		}
		
	}
	// update tỉ số trận đấu
	private void updateScore() {
		if(currentPlayer.equals(Cell.O_VALUE)) scoreO++;
		else scoreX++;
	}

	// khởi tạo bảng
	private void initMatrix() {
		matrix = new Cell[BOARD_ROW][BOARD_COL];
		ticked = new boolean[BOARD_ROW][BOARD_COL];
		for(int i = 0; i < BOARD_ROW; i++) {
			for(int j = 0; j < BOARD_COL; j++) {
				int x = i*SIZE_CELL;
				int y = j*SIZE_CELL;
				Cell cell = new Cell(x, y, SIZE_CELL, SIZE_CELL);
				matrix[i][j] = cell;
				ticked[i][j] = false;
			}
		}
		
	}

	// set kích thước cho bảng
	private void setBoardSize() {
		Dimension size = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
		setPreferredSize(size);
	}
	
	// tải ảnh cần dùng
	private void loadImgs() {
		try {
			imgX = ImageIO.read(new FileInputStream("img/x.png"));
			imgO = ImageIO.read(new FileInputStream("img/o.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// vẽ
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < BOARD_ROW; i++) {
			for(int j = 0; j < BOARD_COL; j++) {
				int x = j*SIZE_CELL;
				int y = i*SIZE_CELL;
				Color c = Color.BLACK;
				g.setColor(c);
				g.drawRect(x, y, SIZE_CELL, SIZE_CELL);
				
				if(matrix[i][j].getValue().equals(Cell.O_VALUE)) 
					g.drawImage(imgO, x, y, SIZE_CELL, SIZE_CELL, null);
				else if(matrix[i][j].getValue().equals(Cell.X_VALUE)) 
					g.drawImage(imgX, x, y, SIZE_CELL, SIZE_CELL, null);
						
			}
		}
	}
	
	// reset game
	public void resetGame() {
		initMatrix();
		Gamestate = NORMAL;
		repaint();
	}
	
	// reset lại tỉ số
	public void resetScore() {
		scoreO = scoreX = 0;
	}
	
	// âm thanh click
	private void soundEffect() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					clip = AudioSystem.getClip();
					File clickEffect = new File("sound/click.wav");
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(clickEffect);
					clip.open(audioInput);
					clip.start();
				} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		thread.start();
	}
	
	// check còn nước hay không
	private boolean isBoardFull() {
		int cnt = 0;
		for(int i = 0; i < BOARD_ROW; i++) {
			for(int j = 0; j < BOARD_COL; j++) {
				if(ticked[i][j]) cnt++;
			}
		}
		if(cnt == BOARD_COL*BOARD_ROW) return true;
		else return false;
	}
	
	// check có thắng không
	private boolean checkWin(String value, int row, int col) {
//		check ngang
		int cnt = 0;
		for(int i = 0; i < BOARD_COL; i++) {
			if(matrix[row][i].getValue().equals(value)) {
				cnt++;
				if(cnt == 5) 
					return true;
			}else cnt = 0;	
			
		}
		
//		check dọc
		cnt = 0;
		for(int i = 0; i < BOARD_ROW; i++) {
			if(matrix[i][col].getValue().equals(value)) {
				cnt++;
				if(cnt == 5) 
					return true;
			}else cnt = 0;	
			
		}
		
		
//		check chéo trái -> phải
		cnt = 0;
		int topR = row - Math.min(col, row);
		int topC = col - Math.min(col, row);
		for(;topR < BOARD_ROW && topC < BOARD_COL; topR++, topC++) {
			if(matrix[topR][topC].getValue().equals(value)) {
				cnt++;
				if(cnt == 5) 
					return true;
			}else cnt = 0;	
			
		}
		
//		check chéo phải -> trái
		cnt = 0;
		topR = row - Math.min(col, row);
		topC = col + Math.min(col, row);
		if(topC >= BOARD_COL) {
			topR += topC - (BOARD_COL -1);
			topC = BOARD_COL -1;
		}
		
		for(;topR < BOARD_ROW && topC >= 0; topR++, topC--) {
			if(matrix[topR][topC].getValue().equals(value)) {
				cnt++;
				if(cnt == 5) 
					return true;
			}else cnt = 0;	
			
		}
		
		return false;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void changeCurrentplayer() {
		if(currentPlayer.equals(Cell.X_VALUE))
			currentPlayer = Cell.O_VALUE;
		else 
			currentPlayer = Cell.X_VALUE;
	}

	public int getScoreX() {
		return scoreX;
	}

	public int getScoreO() {
		return scoreO;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
