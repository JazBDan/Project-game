package enemies;

import static helpers.Constants.Enemies.HiddenSquare;

import managers.EnemyManager;

public class HiddenSquareEnemy extends Enemy{

	public HiddenSquareEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, HiddenSquare, em);
	}

}
