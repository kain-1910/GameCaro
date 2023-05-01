package main;

import static contants.Contants.State.*;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import state.Menu;


public class Game {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Clip clip;
	private Menu menu;
	private boolean muteSong = false;

	public Game() {
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		menu = new Menu(this);
		playSong();
		
	}
//	nhạc game
	private void playSong() {
		Thread threadSong = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					clip = AudioSystem.getClip();
					File song = new File("sound/playing.wav");
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(song);
					clip.open(audioInput);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		threadSong.start();
	}
	
	public GameWindow getGameWindow() {
		return gameWindow;
	}
	
	public void muteSong() {
		muteSong = !muteSong;
		if(muteSong) clip.stop();
		else clip.start();
	}
	
}
