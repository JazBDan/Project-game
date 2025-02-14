package managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.AdvCircleEnemy;
import enemies.AdvSquareEnemy;
import enemies.AdvTriangleEnemy;
import enemies.BasicCircleEnemy;
import enemies.BasicSquareEnemy;
import enemies.BasicTriangleEnemy;
import enemies.DiamondBossEnemy;
import enemies.Enemy;
import enemies.FinalBoss;
import enemies.HiddenCircleEnemy;
import enemies.HiddenSquareEnemy;
import enemies.SquareBossEnemy;
import enemies.TrapezoidEnemy;
import helpers.LoadSave;
import objects.PathPoint;
import scenes.Playing;

import static helpers.Constants.Direction.*;
import static helpers.Constants.Tiles.*;
import static helpers.Constants.Enemies.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[] enemyImgs;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private int hpBarWidth = 24;
	private PathPoint start, end;
	private BufferedImage slowEffect;
	
	public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
		this.playing = playing;
		enemyImgs = new BufferedImage[12];
		this.start = start;
		this.end = end;
		
		loadEffectImg();
		loadEnemyImgs();
		
	}
	
	private void loadEffectImg() {
		slowEffect = LoadSave.getSpriteTD().getSubimage(0, 5 * 32, 32, 32);
	}

	private void loadEnemyImgs() {
		//Reminder per index position of enemy is 32 so you just times it to the index
		BufferedImage tdsprt = LoadSave.getSpriteTD();
		enemyImgs[0] = tdsprt.getSubimage(32, 32*2, 32, 32);
		enemyImgs[1] = tdsprt.getSubimage(0, 32*2, 32, 32);
		enemyImgs[2] = tdsprt.getSubimage(32*2, 32*2, 32, 32);
		enemyImgs[3] = tdsprt.getSubimage(0, 32*3, 32, 32);
		enemyImgs[4] = tdsprt.getSubimage(32, 32*3, 32, 32);
		enemyImgs[5] = tdsprt.getSubimage(2 * 32, 32*3, 32, 32);
		enemyImgs[6] = tdsprt.getSubimage(3 * 32, 32*3, 32, 32);
		enemyImgs[7] = tdsprt.getSubimage(4 * 32, 32*3, 32, 32);
		enemyImgs[8] = tdsprt.getSubimage(5 * 32, 32*3, 32, 32);
		enemyImgs[9] = tdsprt.getSubimage(6 * 32, 32*3, 32, 32);
		enemyImgs[10] = tdsprt.getSubimage(32, 32*4, 32, 32);
		enemyImgs[11] = tdsprt.getSubimage(0, 32*4, 32, 32);
	}

	public void update() {
		
		for(Enemy e : enemies) 
			if(e.isAlive())
				//Checking next tile is a pathway with a position and direction
				updateEnemyMovement(e);	
	}

	public void updateEnemyMovement(Enemy e) {
		
		if(e.getLastDirect() == -1) 
			setNewDirectionandMove(e);
		
		int newX = (int) (e.getX() + getSpeedandWidth(e.getLastDirect(), e.getEnemyType()));
		int newY = (int) (e.getY() + getSpeedandHeight(e.getLastDirect(), e.getEnemyType()));
		
		if(getTileType(newX, newY) == PATH_TILE) {
			//move on same direction
			e.move(GetSpeed(e.getEnemyType()), e.getLastDirect());
		} else if(isAtEnd(e)) {
			e.kill();
			//end
			playing.removeLife();
		} else {
			//finding of new direction
			setNewDirectionandMove(e);
		}
	}

	private void setNewDirectionandMove(Enemy e) {
		int dir = e.getLastDirect();
		
		//move into current tile fully
		int xCord = (int)(e.getX() / 32);
		int yCord = (int)(e.getY() / 32);
		
		fixEnemyOffsetTile(e, dir, xCord, yCord);
		
		if(isAtEnd(e))
			return;
		
		if(dir == LEFT || dir == RIGHT) {
			int newY = (int) (e.getY() + getSpeedandHeight(UP, e.getEnemyType()));
			if(getTileType((int) e.getX(), newY) == PATH_TILE)
				e.move(GetSpeed(e.getEnemyType()), UP);
			else
				e.move(GetSpeed(e.getEnemyType()), DOWN);
		} else {
			int newX = (int) (e.getX() + getSpeedandWidth(RIGHT, e.getEnemyType()));
			if(getTileType(newX,(int) e.getY()) == PATH_TILE)
				e.move(GetSpeed(e.getEnemyType()), RIGHT);
			else
				e.move(GetSpeed(e.getEnemyType()), LEFT);
		}
	}

	private void fixEnemyOffsetTile(Enemy e, int direction, int xCord, int yCord) {
		switch(direction) {
 		case RIGHT:
 			if(xCord < 19)
 				xCord++;
 			break;
 		case DOWN:
 			if(yCord < 19)
 				yCord++;
 			break;
 		}
		
		e.setPos(xCord * 32, yCord * 32);
	}

	private boolean isAtEnd(Enemy e) {
		if(e.getX() == end.getxCord() * 32)
			if(e.getY() == end.getyCord() * 32)
				return true;
		return false;
	}

	private int getTileType(int x, int y) {
		return playing.getTileType(x, y);
	}

	private float getSpeedandHeight(int lastDirect, int enemyType) {
		if(lastDirect == UP) 
			return -GetSpeed(enemyType);
		 else if (lastDirect == DOWN) 
			return GetSpeed(enemyType) + 32;

		return 0;
	}

	private float getSpeedandWidth(int lastDirect, int enemyType) {
		if(lastDirect == LEFT) 
			return -GetSpeed(enemyType);
		 else if (lastDirect == RIGHT) 
			return GetSpeed(enemyType) + 32;
		
		return 0;
	}

	public void spawnEnemy(int nextEnemy) {
		addEnemy(nextEnemy);
	}
	
	public void addEnemy(int enemyType) {
		int x = start.getxCord() * 32;
		int y = start.getyCord() * 32;
		//THIS IS SO UNORGANIZED LIKE THE NUMBER OF ENEMIES LMAO
		switch(enemyType) {
		case BasicCircle:
			enemies.add(new BasicCircleEnemy(x, y, 0, this));
			break;
		case BasicTriangle:
			enemies.add(new BasicTriangleEnemy(x, y, 0, this));
			break;
		case BasicSquare:
			enemies.add(new BasicSquareEnemy(x, y, 0, this));
			break;
		case AdvCircle:
			enemies.add(new AdvCircleEnemy(x, y, 0, this));
			break;
		case AdvTriangle:
			enemies.add(new AdvTriangleEnemy(x, y, 0, this));
			break;
		case AdvSquare:
			enemies.add(new AdvSquareEnemy(x, y, 0, this));
			break;
		case Trapezoid:
			enemies.add(new TrapezoidEnemy(x, y, 0, this));
			break;
		case DiamondBoss:
			enemies.add(new DiamondBossEnemy(x, y, 0, this));
			break;
		case AdvancedDiamondBoss:
			enemies.add(new FinalBoss(x, y, 0, this));
			break;
		case SquareBoss:
			enemies.add(new SquareBossEnemy(x, y, 0, this));
			break;
		case HiddenCircle:
			enemies.add(new HiddenCircleEnemy(x, y, 0, this));
			break;
		case HiddenSquare:
			enemies.add(new HiddenSquareEnemy(x, y, 0, this));
			break;
		}
	}
	
	public void draw(Graphics g) {
		for(Enemy e : enemies) {
			if(e.isAlive()) {
				drawEnemy(e, g);
				drawHealthBar(e, g);
				drawEffects(e, g);
			}
		}
	}

	private void drawEffects(Enemy e, Graphics g) {
		if(e.isSlowed()) {
			g.drawImage(slowEffect, (int) e.getX(), (int) e.getY(), null);
		}
	}

	private void drawHealthBar(Enemy e, Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2),(int) e.getY() - 7, getNewBarWidth(e), 3);
	}
	
	private int getNewBarWidth(Enemy e) {
		return (int)(hpBarWidth * e.getHealthBarFloat());
	}

	private void drawEnemy(Enemy e, Graphics g) {
		g.drawImage(enemyImgs[e.getEnemyType()],(int) e.getX(),(int) e.getY(), null);
		
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public int getAmountOfAliveEnemies() {
		int size = 0;
		for(Enemy e : enemies)
			if(e.isAlive())
				size++;
		
		return size;
	}

	public void rewardPlayer(int enemyType) {
		playing.rewardPlayer(enemyType);
	}
	
	public void reset() {
		enemies.clear();
	}
	
}
