package main;

import javax.swing.JFrame;

import helpers.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.GameOver;
import scenes.GameWin;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

public class Game extends JFrame implements Runnable {
    
    private GameScreen gameScreen;
    private Thread gameThread;
    
    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    
    //classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private GameOver gameOver;
    private GameWin gameWin;
    
	private TileManager tileManager;
	
    public Game() {
    	
    	LoadSave.CreateFolder();
    	
    	createDefaultLevel();
        initClasses();
        
        //JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Tower Depends");
        add(gameScreen);
        pack();
        
        setVisible(true);
        //Test run
        System.out.println("This is running");
    }
    
    private void createDefaultLevel() {
		int[] array = new int[400];
		for(int i = 0; i < array.length; i++)
			array[i] = 0;
		
		LoadSave.CreateLevel(array);
		
	}
    
    private void initClasses() {
		tileManager = new TileManager();
    	render = new Render(this);
        gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editing = new Editing(this);
		gameOver = new GameOver(this);
		gameWin = new GameWin(this);
	}
    
    public void start() {
    	gameThread = new Thread(this) {};
    	
    	gameThread.start();
    }
    

  
    private void updateGame() {

    	switch(GameStates.gameState) {
		case EDIT:
			editing.update();
			break;
		case MENU:
			break;
		case PLAYING:
			playing.update();
			break;
		case SETTINGS:
			break;
		default:
			break;
    	}
    	
    }
    
    public static void main(String[] args) {
        
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
        
    }
		@Override
		public void run() {
			
		    double timePerframe = 1000000000.0 / FPS_SET;
		    double timePerUpdate = 1000000000.0 / UPS_SET;
		    long lastframe = System.nanoTime();
		    long lastUpdate = System.nanoTime();
		    long lastTimeCheck = System.currentTimeMillis();
		    
		    int frames = 0;
		    int updates = 0;
		    
		    long now;
		    
			while(true) {
				
				now = System.nanoTime();
				//Render
				if(now - lastframe >= timePerframe) {
	            	repaint();
	            	lastframe = now;
	            	frames++;
	            }
				
				//Update
				if(now - lastUpdate >= timePerUpdate) {
					updateGame();
			    	lastUpdate = now;
					updates++;
				}
				
				//checking Frames per second and Update per second
	            if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
	            	System.out.println("FPS: " + frames + "| UPS: " + updates);
	            	frames = 0;
	            	updates = 0;
	            	lastTimeCheck = System.currentTimeMillis();
	            }
			}
		}
		
		//get
		public Render getRender() {
			return render;
		}

		public Menu getMenu() {
			return menu;
		}


		public Playing getPlaying() {
			return playing;
		}


		public Settings getSettings() {
			return settings;
		}
		
		public Editing getEditor() {
			return editing;
		}
		
		public GameOver getGameOver() {
			return gameOver;
		}
		
		public GameWin getGameWin() {
			return gameWin;
		}
		
		public TileManager getTileManager() {
			return tileManager;
		}

}

/*
            THANK YOU KAARIN GAMING FOR THE IDEA OF THE CODE!
            Credit: https://www.youtube.com/@KaarinGaming
        
            I MAY ALSO PUT DIFFERENT TYPES OF TOWERS IF I WANT TO BTW
            AAAAAAAAAAAAAAAAAAAAAAA
        
        */
