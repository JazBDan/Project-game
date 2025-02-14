package enemies;

import static helpers.Constants.Enemies.Trapezoid;

import managers.EnemyManager;

public class TrapezoidEnemy extends Enemy{

	public TrapezoidEnemy(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, Trapezoid, em);
	}

}
