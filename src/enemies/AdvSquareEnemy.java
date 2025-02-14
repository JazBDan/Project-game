package enemies;

import static helpers.Constants.Enemies.AdvSquare;

import managers.EnemyManager;

public class AdvSquareEnemy extends Enemy{

	public AdvSquareEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, AdvSquare, em);
	}

}
