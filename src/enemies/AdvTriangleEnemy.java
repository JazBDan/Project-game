package enemies;

import static helpers.Constants.Enemies.AdvTriangle;

import managers.EnemyManager;

public class AdvTriangleEnemy extends Enemy{

	public AdvTriangleEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, AdvTriangle, em);
	}

}
