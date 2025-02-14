package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpers.LoadSave;
import objects.Projectile;
import objects.Tower;
import scenes.Playing;
import static helpers.Constants.Towers.*;
import static helpers.Constants.Projectiles.*;

public class ProjectileManager {

	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Explosion> explosions = new ArrayList<>();
	private BufferedImage[] projectile_imgs, exploimgs;
	private int projectile_id = 0;
	
	public ProjectileManager(Playing playing) {
		this.playing = playing;
		importImgs();
	}
	
	private void importImgs() {
		BufferedImage td = LoadSave.getSpriteTD();
		projectile_imgs = new BufferedImage[5];
		//bullet
		projectile_imgs[0] = td.getSubimage(7 * 32, 32, 32, 32);
		//bomb
		projectile_imgs[1] = td.getSubimage(2 * 32, 4 * 32, 32, 32);
		//ice
		projectile_imgs[2] = td.getSubimage(7 * 32, 3 * 32, 32, 32);
		//high velocity bullet
		projectile_imgs[3] = td.getSubimage(1 * 32, 5 * 32, 32, 32);
		//beam
		projectile_imgs[4] = td.getSubimage(2 * 32, 5 * 32, 32, 32);
		importExplosion(td);
	}
	
	private void importExplosion(BufferedImage td) {
		exploimgs = new BufferedImage[7];
		
		for(int i = 0; i < 7; i++)
			exploimgs[i] = td.getSubimage((3 + i) * 32, 4 * 32, 32, 32);
		
	}

	public void newProjectile(Tower t, Enemy e) {
		int type = getProjectileType(t);
		
		int xDist = (int) (t.getX() - e.getX());
		int yDist = (int) (t.getY() - e.getY());
		int totDist = Math.abs(xDist) + Math.abs(yDist);
		
		float xPer = (float) Math.abs(xDist)/totDist;
		
		float xSpeed = xPer * helpers.Constants.Projectiles.GetSpeed(type);
		float ySpeed = helpers.Constants.Projectiles.GetSpeed(type) - xSpeed;
		
		if(t.getX() > e.getX())
			xSpeed *= -1;
		if(t.getY() > e.getY())
			ySpeed *= -1;
		
		
		//rotation of projectiles
		float rotate = 0;
		
		if(type == BULLET || type == ICE || type == HIGHVELOCITYBULLET || type == BEAM) {
			float arcValue = (float) Math.atan(yDist / (float) xDist);
			rotate = (float) Math.toDegrees(arcValue);
			
			if(xDist < 0)
				rotate += 180;
		}
		
		
		//reusing of projectiles to avoid lag
		for(Projectile p : projectiles) 
			if(p.getProjectileType() == type && p.getProjectileType() != BEAM) {
				p.reuse(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate);
				return;
			}
		projectiles.add(new Projectile(t.getX() + 16, t.getY() + 16, xSpeed, ySpeed, t.getDmg(), rotate, projectile_id++, type));
		
	}

	public void update() {
		for(Projectile p : projectiles)
			if(p.isActive()) {
				p.move();
				if(isProjHitEnemy(p)) {
					p.setActive(false);
					if(p.getProjectileType() == BOMB) {
						explosions.add(new Explosion(p.getPos()));
						explodeOnEnemies(p);
					}
				} else if(isProjOutsideBounds(p)) {
					p.setActive(false);
				}
			}
		
		for(Explosion e : explosions)
			if(e.getIndex() < 7)
				e.update();
	}

	private void explodeOnEnemies(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive()) {
				float radius = 40.0f;

				float xDist = Math.abs(p.getPos().x - e.getX());
				float yDist = Math.abs(p.getPos().y - e.getY());
				
				float realDist = (float) Math.hypot(xDist, yDist);
				
				if(realDist <= radius) 
					e.hurt(p.getDmg());
			}
		}
	}

	private boolean isProjHitEnemy(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive())
				if(e.getBounds().contains(p.getPos())) {
					e.hurt(p.getDmg());
					if(p.getProjectileType() == ICE) {
						e.slow();
					}
					return true;
				}
		}
		return false;
	}
	
	private boolean isProjOutsideBounds(Projectile p) {
		if(p.getPos().x >= 0)
			if(p.getPos().x <= 640)
				if(p.getPos().y >= 0)
					if(p.getPos().y <= 780)
						return false;
		return true;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for(Projectile p : projectiles)
			if(p.isActive()) {
				if(p.getProjectileType() == BULLET || p.getProjectileType() == ICE || p.getProjectileType() == HIGHVELOCITYBULLET || p.getProjectileType() == BEAM) {
					g2d.translate(p.getPos().x, p.getPos().y);
					g2d.rotate(Math.toRadians(p.getRotation()));
					g2d.drawImage(projectile_imgs[p.getProjectileType()], -16, -16, null);
					g2d.rotate(-Math.toRadians(p.getRotation()));
					g2d.translate(-p.getPos().x, -p.getPos().y);
				} else {
					g2d.drawImage(projectile_imgs[p.getProjectileType()], (int) p.getPos().x - 16, (int) p.getPos().y - 16, null);
				}
			}
		
		drawExplosions(g2d);
		
	}

	private void drawExplosions(Graphics2D g2d) {
		for (Explosion e : explosions) 
			if(e.getIndex() < 7) 
				g2d.drawImage(exploimgs[e.getIndex()], (int) e.getPos().x - 16, (int) e.getPos().y - 16, null);
	}

	private int getProjectileType(Tower t) {
		switch(t.getTowerType()) {
		case GUNNER:
			return BULLET;
		case EXPLODER:
			return BOMB;
		case FREEZER:
			return ICE;
		case RANGER:
			return HIGHVELOCITYBULLET;
		case ACCELERATOR:
			return ACCELERATOR;
		}
		return 0;
	}
	
	//Inside class for explosion
	public class Explosion {
		
		private Point2D.Float pos;
		private int exploTick = 0, exploIndex = 0;
		public Explosion(Point2D.Float pos) {
			this.pos = pos;
		}
		
		public void update() {
			exploTick++;
			if(exploTick >= 3) {
				exploTick = 0;
				exploIndex++;
				
			}
		}
		
		public int getIndex() {
			return exploIndex;
		}
		
		public Point2D.Float getPos() {
			return pos;
		}
	}
	
	
	public void reset() {
		projectiles.clear();
		explosions.clear();
		
		projectile_id = 0;
	}
}
