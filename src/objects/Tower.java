package objects;

import static helpers.Constants.Towers.*;

public class Tower {
	
	private int x, y, id, towerType, cdTick, dmg;
	private float range, cooldown;
	private int tier;
	
	public Tower(int x, int y, int id, int towerType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		tier = 1;
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	}
	
	public void update() {
		cdTick++;
	}
	
	public void upgrade() {
		this.tier++;
		
		switch(towerType) {
		case GUNNER:
			dmg += 2;
			range += 5;
			cooldown -= 0.5;
			break;
		case EXPLODER:
			dmg += 5;
			range += 2;
			cooldown -= 1.25;
			break;
		case FREEZER:
			dmg += 0;
			range += 25;
			cooldown -= 3;
			break;
		case RANGER:
			dmg += 50;
			range += 50;
			cooldown -= 5;
			break;
		case ACCELERATOR:
			dmg += 25;
			range += 10;
			cooldown -= 0.5;
			break;
		}
	}
	
	public boolean isCooldownOver() {
		return cdTick >= cooldown;
	}

	public void resetCooldown() {
		cdTick = 0;
	}
	
	private void setDefaultCooldown() {
		cooldown = helpers.Constants.Towers.GetDefaultCooldown(towerType);
	}

	private void setDefaultRange() {
		range = helpers.Constants.Towers.GetDefaultRange(towerType);
	}

	private void setDefaultDmg() {
		dmg = helpers.Constants.Towers.GetDefaultDmg(towerType);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTowerType() {
		return towerType;
	}

	public void setTowerType(int towerType) {
		this.towerType = towerType;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRange() {
		return range;
	}

	public float getCooldown() {
		return cooldown;
	}
	
	public int getTier() {
		return tier;
	}
	
}
