package enemies;

import static helpers.Constants.Enemies.BasicSquare;

import managers.EnemyManager;

public class BasicSquareEnemy extends Enemy{

	public BasicSquareEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, BasicSquare, em);
	}

}
