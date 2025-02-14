package enemies;

import static helpers.Constants.Enemies.BasicTriangle;

import managers.EnemyManager;

public class BasicTriangleEnemy extends Enemy{

	public BasicTriangleEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, BasicTriangle, em);
	}

}
