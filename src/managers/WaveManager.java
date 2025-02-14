package managers;

import java.util.ArrayList;
import java.util.Arrays;

import events.Wave;
import scenes.Playing;

public class WaveManager {
	
	private Playing playing;
	private ArrayList<Wave> waves = new ArrayList<>();
	private int enemySpawnTickLimit = 60;
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex;
	private int waveTickLimit = 5 * 60;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;
	
	public WaveManager(Playing playing) {
		this.playing = playing;
		createWaves();
	}
	
	public void update() {
		if(enemySpawnTick < enemySpawnTickLimit)
			enemySpawnTick++;
		
		if(waveStartTimer) {
			waveTick++;
			if(waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}
	}
	
	public void increaseWaveIndex() {
		waveIndex++;
		waveTick = 0;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}
	
	public boolean isWaveTimeOver() {
		
		return waveTickTimerOver;
	}
	
	public void startWaveTimer() {
		waveStartTimer = true;
	}
	
	public int getNextEnemy() {
		enemySpawnTick = 0;
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}
	
	private void createWaves() {
		//test
		//waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))));
		//total of 35 waves
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 1, 1, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 9)))); //10
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 2, 2, 9, 9))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 9, 9))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 9, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 3, 3, 4, 4))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 3, 3, 1, 1, 4, 4, 2, 2, 9, 9))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4)))); //20
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 5, 5, 5, 2, 2, 4, 4, 2, 1, 2, 3, 4, 5))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 3, 3, 3, 3, 9, 9, 7))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 4, 4, 5, 5, 7))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(9, 9, 9, 9, 9))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 5, 4, 3, 4, 3, 4, 3, 7, 7))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 11, 7, 7, 7))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(7, 7, 7, 7, 7, 3, 4, 3, 4, 5, 3, 4, 5, 4, 3, 3, 4, 5, 5, 3, 4, 4))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(7, 7, 7, 7, 7, 7, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(7, 9, 7, 9, 7, 9, 7, 9, 7, 9, 7))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 7, 7, 7, 7, 7, 6)))); //30
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 10, 11))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(7, 7, 1, 7, 7, 2, 7, 7, 3, 7, 7, 4, 7, 7, 5, 7, 7, 6, 7, 7, 7, 7, 7, 9, 7, 7, 10, 7, 7, 11, 7, 7))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 9, 9, 9, 9, 9, 7 ,7 ,7, 9, 7,9))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 2, 2, 2, 10, 10, 10, 11, 11, 11, 7, 7, 7, 6, 6, 6, 8)))); //35
	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public boolean isTimeforNewEnemy() {
		
		return enemySpawnTick >= enemySpawnTickLimit;
	}
	
	public boolean isThereMoreEnemiesInWave() {
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	}

	public boolean isThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}
	
	public void resetEnemyIndex() {
		enemyIndex = 0;
	}
	
	public int getWaveIndex() {
		return waveIndex;
	}
	
	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimeStarted() {
		return waveStartTimer;
	}
	
	public void reset() {
		waves.clear();
		createWaves();
		enemyIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
	}
	
}
