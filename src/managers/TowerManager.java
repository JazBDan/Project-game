package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpers.LoadSave;
import objects.Tower;
import scenes.Playing;

public class TowerManager {
	
	private Playing playing;
	private BufferedImage[] towerImgs;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;
	
	public TowerManager(Playing playing) {
		this.playing = playing;
		
		loadTowerImg();
	}

	private void loadTowerImg() {
		BufferedImage twrsprite = LoadSave.getSpriteTD();
		towerImgs = new BufferedImage[5];
		//gunner
		towerImgs[0] = twrsprite.getSubimage(3 * 32, 2 * 32, 32, 32);
		//exploder
		towerImgs[1] = twrsprite.getSubimage(8 * 32, 2 * 32, 32, 32);
		//freezer
		towerImgs[2] = twrsprite.getSubimage(3 * 32, 5 * 32, 32, 32);
		//ranger
		towerImgs[3] = twrsprite.getSubimage(8 * 32, 5 * 32, 32, 32);
		//accelerator
		towerImgs[4] = twrsprite.getSubimage(9 * 32, 5 * 32, 32, 32);
	}
	

	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
	}
	
	public void upgradeTower(Tower displayedTower) {
		for(Tower t: towers)
			if(t.getId() == displayedTower.getId())
				t.upgrade();
	}
	
	public void removeTower(Tower displayedTower) {
		for(int i = 0; i < towers.size(); i++)
			if(towers.get(i).getId() == displayedTower.getId())
				towers.remove(i);
	}
	
	public void update() {
		for(Tower t : towers) {
			t.update();
			attackEnemyifClose(t);
		}
	}
	
	private void attackEnemyifClose(Tower t) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive())
				if(isEnemyinRange(t, e)) {
					if(t.isCooldownOver()) {
						playing.shootEnemy(t, e);
						t.resetCooldown();
					}
				}
		}
	
	}

	private boolean isEnemyinRange(Tower t, Enemy e) {
		
		int range = helpers.Utilithings.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
		
		return range < t.getRange();
	}

	public void draw(Graphics g) {
		for(Tower t: towers)
			g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
	}
	
	public Tower getTowerAt(int x, int y) {
		//to stop placing tower on top of towers
		for(Tower t : towers)
			if(t.getX() == x)
				if(t.getY() == y)
					return t;
		
		return null;
	}
	
	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}
	
	public void reset() {
		towers.clear();
		towerAmount = 0;
	}
}
