package helpers;

public class Constants {
	
	public static class Projectiles{
		
		public static final int BULLET = 0;
		public static final int BOMB = 1;
		public static final int ICE = 2;
		public static final int HIGHVELOCITYBULLET = 3;
		public static final int BEAM = 4;
		
		public static float GetSpeed(int type) {
			switch(type) {
			case BULLET:
				return 10f;
			case BOMB:
				return 5f;
			case ICE:
				return 8f;
			case HIGHVELOCITYBULLET:
				return 45f;
			case BEAM:
				return 10f;
			}
			
			return 0f;
			
		}
		
	}
	
	public static class Towers {
		public static final int GUNNER = 0;
		public static final int EXPLODER = 1;
		public static final int FREEZER = 2;
		public static final int RANGER = 3;
		public static final int ACCELERATOR = 4;
		
		public static int GetTowerCost(int towerType) {
			switch(towerType) {
			case GUNNER:
				return 200;
			case EXPLODER:
				return 450;
			case FREEZER:
				return 300;
			case RANGER:
				return 3800;
			case ACCELERATOR:
				return 4250;
			}
			return 0;
		}
		
		public static String GetName(int towerType) {
			switch(towerType) {
			case GUNNER:
				return "Gunner";
			case EXPLODER:
				return "Exploder";
			case FREEZER:
				return "Freezer";
			case RANGER:
				return "Ranger";
			case ACCELERATOR:
				return "Accelerator";
			}
			return "";
		}
		
		public static int GetDefaultDmg(int towerType) {
			switch(towerType) {
			case GUNNER:
				return 2;
			case EXPLODER:
				return 5;
			case FREEZER:
				return 1;
			case RANGER:
				return 150;
			case ACCELERATOR:
				return 8;
			}
			
			return 0;
		}
		
		public static float GetDefaultRange(int towerType) {
			switch(towerType) {
			case GUNNER:
				return 110;
			case EXPLODER:
				return 80;
			case FREEZER:
				return 90;
			case RANGER:
				return 200;
			case ACCELERATOR:
				return 80;
			}
			
			return 0;
		}

		public static float GetDefaultCooldown(int towerType) {
			switch(towerType) {
			case GUNNER:
				return 40;
			case EXPLODER:
				return 80;
			case FREEZER:
				return 70;
			case RANGER:
				return 150;
			case ACCELERATOR:
				return 15;
			}
			
			return 0;
		}
	}
	
	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class Enemies {
		
		public static final int BasicCircle = 0;
		public static final int BasicTriangle = 1;
		public static final int BasicSquare = 2;
		public static final int AdvCircle = 3;
		public static final int AdvTriangle = 4;
		public static final int AdvSquare = 5;
		public static final int Trapezoid = 6;
		public static final int DiamondBoss = 7;
		public static final int AdvancedDiamondBoss = 8;
		public static final int SquareBoss = 9;
		public static final int HiddenCircle = 10;
		public static final int HiddenSquare = 11;
	
		public static int GetReward(int enemyType) {
			switch(enemyType) {
			case BasicCircle:	
				return 10;
			case BasicTriangle:
				return 5;
			case BasicSquare:
				return 20;
			case AdvCircle:
				return 50;
			case AdvTriangle:
				return 35;
			case AdvSquare:
				return 85;
			case Trapezoid:
				return 1500;
			case DiamondBoss:
				return 750;
			case AdvancedDiamondBoss:	
				return 10000;
			case SquareBoss:
				return 200;
			case HiddenCircle:
				return 20;
			case HiddenSquare:
				return 500;
			}
			return 0;
		}
		
		public static float GetSpeed(int enemyType) {
			switch(enemyType) {
			case BasicCircle:	
				return 1f;
			case BasicTriangle:
				return 2f;
			case BasicSquare:
				return 0.75f;
			case AdvCircle:
				return 1.5f;
			case AdvTriangle:
				return 2.0f;
			case AdvSquare:
				return 1f;
			case Trapezoid:
				return 1.25f;
			case DiamondBoss:
				return 0.5f;
			case AdvancedDiamondBoss:	
				return 0.45f;
			case SquareBoss:
				return 0.5f;
			case HiddenCircle:
				return 1.1f;
			case HiddenSquare:
				return 1;
			}
			return 0;
		}
		
		public static int getHP(int enemyType) {
			switch(enemyType) {
			case BasicCircle:	
				return 8;
			case BasicTriangle:
				return 4;
			case BasicSquare:
				return 16;
			case AdvCircle:
				return 30;
			case AdvTriangle:
				return 15;
			case AdvSquare:
				return 60;
			case Trapezoid:
				return 2500;
			case DiamondBoss:
				return 1600;
			case AdvancedDiamondBoss:	
				return 30000;
			case SquareBoss:
				return 160;
			case HiddenCircle:
				return 12;
			case HiddenSquare:
				return 175;
			}
			return 0;
		}
	}
	
	public static class Tiles {
		public static final int VOID_TILE = 0;
		public static final int GROUND_TILE = 1;
		public static final int PATH_TILE = 2;
	}
}