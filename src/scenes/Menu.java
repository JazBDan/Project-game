package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{
	
	private MyButton bPlaying, bEdit, bSettings, bQuit;
	private Random ran = new Random();
	private int red = ran.nextInt(256), green = ran.nextInt(256), blue = ran.nextInt(256);
	
	public Menu(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;
		
		bPlaying = new MyButton("Play", x, y, w , h);
		bEdit = new MyButton("Edit", x, y + yOffset, w, h);
		bSettings = new MyButton("Settings", x, y + yOffset * 2, w , h);
		bQuit = new MyButton("Quit", x, y + yOffset * 3, w , h);
	}

	@Override
	public void render(Graphics g) {
		drawBG(g);
		drawButtons(g);
		drawTips(g);
		
	}
	
	private void drawTips(Graphics g) {
		g.setFont(new Font("Andy", Font.BOLD, 32));
		if(red >= 128 && green >= 128 && blue >= 128)
			g.setColor(Color.black);
		if(red <= 128 && green <= 128 && blue <= 128)
			g.setColor(Color.white);
		
		//Title
		g.drawString("Tower Defense", 238, 40);
		//tips
		g.drawString("Press Q to cancel placing", 190, 550);
		g.drawString("Don't let enemies reach the end!", 170, 575);
		g.drawString("You can create custom map", 190, 600);
		
	}

	public void drawBG(Graphics g) {
		g.setColor(new Color(red, green, blue));
		g.fillRect(0, 0, 640, 780);
	}
	
    private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bEdit.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
	}

		@Override
		public void mouseClicked(int x, int y) {
			
			if (bPlaying.getBounds().contains(x, y)) 
				SetGameState(PLAYING);
			else if (bEdit.getBounds().contains(x, y))
				SetGameState(EDIT);
			 else if (bSettings.getBounds().contains(x, y)) 
				SetGameState(SETTINGS);
			 else if (bQuit.getBounds().contains(x, y)) 
				System.exit(0);
			
		}

		@Override
		public void mouseMoved(int x, int y) {
			bPlaying.setMouseOver(false);
			bEdit.setMouseOver(false);
			bSettings.setMouseOver(false);
			bQuit.setMouseOver(false);
			
			if(bPlaying.getBounds().contains(x, y)) 
				bPlaying.setMouseOver(true);
			else if (bEdit.getBounds().contains(x, y)) 
				bEdit.setMouseOver(true);
			 else if (bSettings.getBounds().contains(x, y)) 
				bSettings.setMouseOver(true);
			 else if (bQuit.getBounds().contains(x, y)) 
				bQuit.setMouseOver(true);
			
		}

		@Override
		public void mousePressed(int x, int y) {	
			if (bPlaying.getBounds().contains(x, y)) 
				bPlaying.setMousePressed(true);
			else if (bEdit.getBounds().contains(x, y)) 
				bEdit.setMousePressed(true);
			else if (bSettings.getBounds().contains(x, y))
				bSettings.setMousePressed(true);
			 else if (bQuit.getBounds().contains(x, y)) 
				bQuit.setMousePressed(true);
			
		}
		
		public void mouseReleased(int x, int y) {	
			resetButtons();
		}

		private void resetButtons() {
			bPlaying.resetBooleans();
			bEdit.resetBooleans();
			bSettings.resetBooleans();
			bQuit.resetBooleans();
		}

		@Override
		public void mouseDragged(int x, int y) {
			
			
		}
}
