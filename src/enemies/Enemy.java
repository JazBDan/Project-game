package enemies;

import java.awt.Rectangle;

import managers.EnemyManager;

import static helpers.Constants.Direction.*;

public abstract class Enemy {
	protected EnemyManager enemyManager;
	protected float x, y;
	//hitbox
	protected Rectangle bounds;
	//info
	protected int health;
	protected int maxHealth;
	protected int ID;
	protected int enemyType;
	protected int lastDirect;
	protected boolean alive = true;
	protected int slowTickLimit = 120;
	protected int slowTick = slowTickLimit;
	
	public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager) {
		this.enemyManager = enemyManager;
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		bounds = new Rectangle((int) x, (int) y, 32, 32);
		lastDirect = -1;
		setHP();
		
	}
	
	private void setHP() {
		health = helpers.Constants.Enemies.getHP(enemyType);
		maxHealth = health;
	}
	
	public void hurt(int dmg) {
		this.health -= dmg;
		if(health <= 0) {
			alive = false;
			enemyManager.rewardPlayer(enemyType);
		}
	}
	
	public void kill() {
		alive = false;
		health = 0;
	}
	public void slow() {
		slowTick = 0;
	}
	
	public void move(float speed, int direction) {
		lastDirect = direction;
		
		if(slowTick < slowTickLimit) {
			slowTick++;
			speed *= 0.5f;
		}
		
 		switch(direction) {
 		case LEFT:
 			this.x -= speed;
 			break;
 		case UP:
 			this.y -= speed;
 			break;
 		case RIGHT:
 			this.x += speed;
 			break;
 		case DOWN:
 			this.y += speed;
 			break;
 		}
 		
 		updateHitbox();
 		
	}

	private void updateHitbox() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}

	public void setPos(int x, int y) {
		//only for position fix
		this.x = x;
		this.y = y;
	}
	
	public float getHealthBarFloat() {
		return health /(float) maxHealth;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getHealth() {
		return health;
	}

	public int getID() {
		return ID;
	}

	public int getEnemyType() {
		return enemyType;
	}
	
	public int getLastDirect() {
		return lastDirect;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isSlowed() {
		return slowTick < slowTickLimit;
	}

}
