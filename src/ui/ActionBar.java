package ui;

import static main.GameStates.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import helpers.Constants.Towers;
import objects.Tower;
import scenes.Playing;

public class ActionBar extends Bar {
	
	private Playing playing;
	private MyButton bMenu, bPause;
	
	private MyButton[] towerButtons;
	private Tower selectedTower;
	private Tower displayedTower;
	private MyButton sellTower, upgradeTower;
	//turning into 2 decimal place only
	private DecimalFormat formatter;
	
	private int gold = 600;
	private boolean showTowerCost;
	private int towerCostType;
	
	private int lives = 20;
	
	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		formatter = new DecimalFormat("0.00");
		
		initButtons();
	}
	
	public void resetEverything() {
		lives = 25;
		towerCostType = 0;
		showTowerCost = false;
		gold = 600;
		selectedTower = null;
		displayedTower = null;
	}
	
	private void initButtons() {
		
		bMenu = new MyButton("Menu", 2, 642, 100 , 30);
		bPause = new MyButton("Pause", 2, 675, 100 , 30);
		
		towerButtons = new MyButton[5];
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 642;
		int xOffset = (int)(w * 1.05f);
		
		for(int i = 0; i < towerButtons.length; i++) {
			towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
		}
		
		sellTower = new MyButton("S", 450, 700, 20, 20);
		upgradeTower = new MyButton("U", 420, 700, 20, 20);
	}
	
	public void removeLife() {
		lives --;
		if(lives <= 0)
			SetGameState(GAME_OVER);
			
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bPause.draw(g);
		
		for(MyButton b : towerButtons) {
			g.setColor(new Color(0, 125, 125));
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
			
			ButtonFeedback(g, b);
		
		}
	}
	
	
	public void draw(Graphics g) {
		//bg
		g.setColor(new Color(10, 50, 120));
		g.fillRect(x, y, width, height);
		
		//btn
		drawButtons(g);
		
		//display tower
		drawDisplayTower(g);
		
		//Wave info
		drawWaveInfos(g);
		
		//Gold info
		drawGoldAmount(g);
		
		//drawTowerCost
		if (showTowerCost)
			drawTowerCost(g);
		
		//Lives
		g.setColor(Color.white);
		g.drawString("Lives: " + getLives(), 230, 715);
	}
	
	private void drawTowerCost(Graphics g) {
		g.setColor(new Color(0, 100, 230));
		g.fillRect(225, 725, 120, 50);
		g.setColor(Color.blue);
		g.drawRect(225, 725, 120, 50);
		
		g.drawString("" + getTowerCostName(), 230, 745);
		g.drawString("Cost " + getTowerCost() + "g", 230, 765);
		
		//Amount of gold checker
		if(isTowerCostMoreThanCurrentGold()) {
			g.setFont(new Font("Andy", Font.BOLD, 20));
			g.setColor(Color.red);
			g.drawString("haha you're broke", 350, 745);
		}
	}

	private boolean isTowerCostMoreThanCurrentGold() {
		return getTowerCost() > gold;
	}

	private int getTowerCost() {
		return helpers.Constants.Towers.GetTowerCost(towerCostType);
	}

	private String getTowerCostName() {
		return helpers.Constants.Towers.GetName(towerCostType);
	}

	private void drawGoldAmount(Graphics g) {
		g.setColor(Color.yellow);
		g.drawString("Gold: " + gold, 350, 770);
		
	}

	private void drawWaveInfos(Graphics g) {
		g.setFont(new Font("Andy", Font.BOLD, 24));
		g.setColor(Color.white);
		
		drawWaveTimerInfo(g);
		drawEnemiesLeftInfo(g);
		drawWavesLeftInfo(g);
		
		
	}
	
	private void drawWavesLeftInfo(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex();
		int size = playing.getWaveManager().getWaves().size();
		
		g.drawString("Wave: " + (current + 1) + " / " + size, 490, 745);
		
	}

	private void drawEnemiesLeftInfo(Graphics g) {
		int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
		g.drawString("Enemies Left: " + remaining, 490, 770);
	}

	private void drawWaveTimerInfo(Graphics g) {
		if(playing.getWaveManager().isWaveTimeStarted()) {
			float timeLeft = playing.getWaveManager().getTimeLeft();
			String formattedText = formatter.format(timeLeft);
			g.drawString("Time Left: " + formattedText, 10, 770);
		}
	}
	
	private void drawDisplayTower(Graphics g) {
		if(displayedTower != null) {
			g.setColor(new Color(0, 100, 230));
			g.fillRect(410, 640, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 640, 220, 85);
			g.drawRect(420, 645, 50, 50);
			g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 645, 50, 50, null);
			g.setFont(new Font("Andy", Font.BOLD, 18));
			g.setColor(Color.black);
			g.drawString("" + Towers.GetName(displayedTower.getTowerType()), 490, 660);
			g.drawString("ID: " + displayedTower.getId(), 490, 675);
			g.drawString("DMG: " + displayedTower.getDmg(), 490, 690);
			g.drawString("RANGE: " + displayedTower.getRange(), 490, 705);
			g.drawString("ATKSPD: " + displayedTower.getCooldown(), 490, 720);
			g.drawString("Level: " + displayedTower.getTier(), 570, 660);
			
			drawDisplayedTowerBorder(g);
			drawDisplayedTowerRange(g);
			
			//Sellbutton
			sellTower.draw(g);
			ButtonFeedback(g, sellTower);
			
			//Upgradebutton
			if (displayedTower.getTier() < 5) {
				upgradeTower.draw(g);
				ButtonFeedback(g, upgradeTower);
			}
			
			if(sellTower.isMouseOver()) {
				g.setColor(Color.red);
				g.drawString("Sell for: " + getSellAmount(displayedTower) + "g", 350, 745);
			} else if(upgradeTower.isMouseOver() && gold >= getUpgradeAmount(displayedTower)) {
				g.setColor(Color.white);
				g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower)+ "g", 350, 745);
			}
			
		}
		
	}

	private int getUpgradeAmount(Tower displayedTower) {
		return (int) (helpers.Constants.Towers.GetTowerCost(displayedTower.getTowerType()) * 0.75f);
	}

	private int getSellAmount(Tower displayedTower) {
		int upgradeCost = (displayedTower.getTier() - 1) * getUpgradeAmount(displayedTower);
		upgradeCost *= 0.45f;
		
		return helpers.Constants.Towers.GetTowerCost(displayedTower.getTowerType()) / 2 + upgradeCost;
	}

	private void drawDisplayedTowerRange(Graphics g) {
		g.setColor(Color.white);
		g.drawOval(displayedTower.getX() + 16 - (int)(displayedTower.getRange() * 2) /2, 
				displayedTower.getY() + 16 - (int)(displayedTower.getRange() * 2) /2, (int)(displayedTower.getRange() * 2), (int)(displayedTower.getRange() * 2));
		
	}

	private void drawDisplayedTowerBorder(Graphics g) {
		g.setColor(new Color(0, 100, 230));
		g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
	}

	public void displayTower(Tower t) {
		displayedTower = t;
	}
	
	private void upgradeTowerClicked() {
		playing.upgradeTower(displayedTower);
		gold -= getUpgradeAmount(displayedTower);
	}
	
	private void sellTowerClicked() {
		int upgradeCost = (displayedTower.getTier() - 1) * getUpgradeAmount(displayedTower);
		upgradeCost *= 0.75f;
		
		playing.removeTower(displayedTower);
		gold += helpers.Constants.Towers.GetTowerCost(displayedTower.getTowerType()) / 2;
		gold += upgradeCost;
		
		displayedTower = null;
	}
	
	private void togglePause() {
		playing.setGamePaused(!playing.isGamePaused());
		
		if(playing.isGamePaused())
			bPause.setText("Unpause");
		else
			bPause.setText("Pause");
	}
	
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else if (bPause.getBounds().contains(x, y)) {
			togglePause();
		}
			
		else {
			
			if(displayedTower != null) {
				if(sellTower.getBounds().contains(x, y)) {
					sellTowerClicked();
					return;
				} else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 5 && gold >= getUpgradeAmount(displayedTower)) {
					upgradeTowerClicked();
					return;
				}
			}
			
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					if(!isGoldEnough(b.getId()))
						return;
					
					selectedTower = new Tower(0, 0, -1, b.getId());
					playing.setSelectedTower(selectedTower);
					return;
				}
			}
		}
		
	}

	private boolean isGoldEnough(int towerType) {
		return gold >= helpers.Constants.Towers.GetTowerCost(towerType);
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bPause.setMouseOver(false);
		showTowerCost = false;
		sellTower.setMouseOver(false);
		upgradeTower.setMouseOver(false);
		
		for (MyButton b : towerButtons)
			b.setMouseOver(false);
		
		if (bMenu.getBounds().contains(x, y)) 
			bMenu.setMouseOver(true);
		else if (bPause.getBounds().contains(x, y)) 
				bPause.setMouseOver(true);
		else {
			
			if(displayedTower != null) {
				if(sellTower.getBounds().contains(x, y)) {
					sellTower.setMouseOver(true);
					return;
				} else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 5) {
					upgradeTower.setMouseOver(true);
					return;
				}
			}
			for (MyButton b : towerButtons)
				if(b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					showTowerCost = true;
					towerCostType = b.getId();
					return;
				}
		}
	}
	
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) 
			bMenu.setMousePressed(true);
		else if (bPause.getBounds().contains(x, y)) 
			bPause.setMousePressed(true);
		else {
			if(displayedTower != null) {
				if(sellTower.getBounds().contains(x, y)) {
					sellTower.setMousePressed(true);
					return;
				} else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 5) {
					upgradeTower.setMousePressed(true);
					return;
				}
				
			for (MyButton b : towerButtons)
				if(b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bPause.resetBooleans();
		sellTower.resetBooleans();
		upgradeTower.resetBooleans();
		for (MyButton b : towerButtons)
			b.resetBooleans();
	}

	public void payForTower(int towerType) {
		this.gold -= helpers.Constants.Towers.GetTowerCost(towerType);
		
	}

	public void addGold(int getReward) {
		this.gold += getReward;
		//give player gold per wave
	}
	
	public int getLives() {
		return lives;
	}
	
}
