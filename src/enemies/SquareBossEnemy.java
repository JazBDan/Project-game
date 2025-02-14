package enemies;

import static helpers.Constants.Enemies.SquareBoss;

import managers.EnemyManager;

public class SquareBossEnemy extends Enemy{

	public SquareBossEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, SquareBoss, em);
	}

}
