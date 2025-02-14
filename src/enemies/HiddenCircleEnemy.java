package enemies;

import static helpers.Constants.Enemies.HiddenCircle;

import managers.EnemyManager;

public class HiddenCircleEnemy extends Enemy{

	public HiddenCircleEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, HiddenCircle, em);
	}

}
