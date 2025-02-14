package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods {
	
	private MyButton bReplay, bMenu;
	private Random ran = new Random();
	private int red = ran.nextInt(256), green = ran.nextInt(256), blue = ran.nextInt(256);

	public GameOver(Game game) {
		super(game);
		initButtons();
		
	}

	private void initButtons() {
		int w = 200;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 250;
		int yOffset = 150;
		
		bMenu = new MyButton("Menu", x, y ,w ,h);
		bReplay = new MyButton("Replay", x, y + yOffset ,w ,h);
	}

	@Override
	public void render(Graphics g) {
		drawBG(g);
		
		g.setFont(new Font("Andy", Font.BOLD, 64));
		if(red >= 128 && green >= 128 && blue >= 128)
			g.setColor(new Color (100, 0, 0));
		if(red <= 128 && green <= 128 && blue <= 128)
			g.setColor(new Color (255, 0, 0));
		else
			g.setColor(new Color (100, 0, 0));
		
		
		g.drawString("GAME OVER", 150, 80);

		bMenu.draw(g);
		bReplay.draw(g);
	}

	private void drawBG(Graphics g) {
		g.setColor(new Color(red, green, blue));
		g.fillRect(0, 0, 640, 780);
	}

	private void replayGame() {
		//reset
		resetAll();
		SetGameState(PLAYING);
	}
	
	private void resetAll() {
		game.getPlaying().resetEverything();
	}
	
	@Override
	public void mouseClicked(int x, int y) {
		if(bMenu.getBounds().contains(x, y))  {
			SetGameState(MENU);
			resetAll();
		}
		else if (bReplay.getBounds().contains(x, y)) 
			replayGame();
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bReplay.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) 
			bMenu.setMouseOver(true);
		else if (bReplay.getBounds().contains(x, y)) 
			bReplay.setMouseOver(true);
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) 
			bMenu.setMousePressed(true);
		else if (bReplay.getBounds().contains(x, y)) 
			bReplay.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {	
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();
		bReplay.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		
		
	}

}
