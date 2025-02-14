package enemies;

import static helpers.Constants.Enemies.AdvancedDiamondBoss;

import managers.EnemyManager;

public class FinalBoss extends Enemy{

	public FinalBoss(float x, float y, int ID, EnemyManager em) {
		super(x, y, ID, AdvancedDiamondBoss, em);
	}

}
