package enemies;

import static helpers.Constants.Enemies.BasicCircle;

import managers.EnemyManager;

public class BasicCircleEnemy extends Enemy{

	public BasicCircleEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, BasicCircle, em);
	}

}
