package enemies;

import static helpers.Constants.Enemies.AdvCircle;

import managers.EnemyManager;

public class AdvCircleEnemy extends Enemy{

	public AdvCircleEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, AdvCircle, em);
	}

}
