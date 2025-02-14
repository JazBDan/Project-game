package enemies;

import static helpers.Constants.Enemies.DiamondBoss;

import managers.EnemyManager;

public class DiamondBossEnemy extends Enemy{

	public DiamondBossEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, DiamondBoss, em);
	}

}
