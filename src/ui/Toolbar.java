package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helpers.LoadSave;
import objects.Tile;
import scenes.Editing;

public class Toolbar extends Bar{
	
	private MyButton bMenu, bSave;
	private Editing editing;
	private MyButton bPathStart, bPathEnd;
	private BufferedImage pathStart, pathEnd;
	private Tile selectedTile;
	
	//	private ArrayList<MyButton> tileButtons = new ArrayList<>();
	private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();
	
	private MyButton bGround, bVoid, bHPath, bCorner, bTPath, bEdge, bSCorner, bBCorner, bMidEdge;
	private MyButton currentButton;
	private int currentIndex = 0;
	
	public Toolbar(int x, int y, int width, int height, Editing editing) {
		super(x, y, width, height);
		this.editing = editing;
		initPathImgs();
		initButtons();
	}
	
	private void initPathImgs() {
		pathStart = LoadSave.getSpriteTD().getSubimage(8 * 32, 32, 32, 32);
		pathEnd = LoadSave.getSpriteTD().getSubimage(9 * 32, 32, 32, 32);
	}

	private void initButtons() {
		
		bMenu = new MyButton("Menu", 2, 642, 100 , 30);
		bSave = new MyButton("Save", 2, 674, 100 , 30);
		
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 642;
		int xOffset = (int)(w * 1.05f);
		
		int i = 0;
		bGround = new MyButton("Ground", xStart, yStart, w, h, i++);
		bVoid = new MyButton("Void", xStart + xOffset, yStart, w, h, i++);
		
		initMapButton(bHPath, editing.getGame().getTileManager().getPaths(), xStart, yStart, xOffset, w, h, i++);
		initMapButton(bCorner, editing.getGame().getTileManager().getCorners(), xStart, yStart, xOffset, w, h, i++);
		initMapButton(bTPath, editing.getGame().getTileManager().getTurnpaths(), xStart, yStart, xOffset, w, h, i++);
		initMapButton(bEdge, editing.getGame().getTileManager().getEdges(), xStart, yStart, xOffset, w, h, i++);
		initMapButton(bSCorner, editing.getGame().getTileManager().getSmallCorners(), xStart, yStart, xOffset, w, h, i++);
		initMapButton(bBCorner, editing.getGame().getTileManager().getBigCorners(), xStart, yStart, xOffset, w, h, i++);
		initMapButton(bMidEdge, editing.getGame().getTileManager().getMidEdges(), xStart, yStart, xOffset, w, h, i++);
		
		bPathStart = new MyButton("PathStart", xStart, yStart + xOffset, w, h, i++);
		bPathEnd = new MyButton("PathEnd", xStart + xOffset, yStart + xOffset, w, h, i++);
		
	}
	
	private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOffset, int w, int h, int id) {
		
		b = new MyButton("", x + xOffset * id, y, w, h, id);
		map.put(b, list);
		
	}
	
	private void saveLevel() {
		editing.saveLevel();
	}
	
	public void rotateSprite() {
		
		currentIndex++;
		if(currentIndex >= map.get(currentButton).size())
			currentIndex = 0;
		selectedTile = map.get(currentButton).get(currentIndex);
		editing.setSelectedTile(selectedTile);
		
	}
	public void draw(Graphics g) {
		//bg
		g.setColor(new Color(10, 50, 120));
		g.fillRect(x, y, width, height);
		
		//btn
		drawButtons(g);
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		bSave.draw(g);
		
		drawPathButton(g, bPathStart, pathStart);
		drawPathButton(g, bPathEnd, pathEnd);
		
		drawNormalButton(g, bGround);
		drawNormalButton(g, bVoid);
		drawSelectedTile(g);
		drawMapButtons(g);
	}
	
	private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
		
		g.drawImage(img, b.x, b.y, b.width, b.height, null);
		ButtonFeedback(g, b);
	}

	private void drawNormalButton(Graphics g, MyButton b) {
		
		g.drawImage(getButtonImg(b.getId()), b.x, b.y, b.width, b.height, null);
		
		ButtonFeedback(g, b);
	}

	private void drawMapButtons(Graphics g) {
		
		for(Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
			MyButton b = entry.getKey();
			BufferedImage img = entry.getValue().get(0).getSprite();
			
			g.drawImage(img, b.x, b.y, b.width, b.height, null);
			
			ButtonFeedback(g, b);
		}
		
	}
	
	private void drawSelectedTile(Graphics g) {
		
		if(selectedTile != null) {
			g.drawImage(selectedTile.getSprite(), 583, 660, 50, 50, null);
			g.setColor(Color.blue);
			g.drawRect(583, 660, 50, 50);
		}
		
	}
	
	public BufferedImage getButtonImg(int id) {
		return editing.getGame().getTileManager().getSprite(id);
	}
	
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else if(bSave.getBounds().contains(x, y))
			saveLevel();
		else if(bGround.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bGround.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if(bVoid.getBounds().contains(x, y)) {
			selectedTile = editing.getGame().getTileManager().getTile(bVoid.getId());
			editing.setSelectedTile(selectedTile);
			return;
		} else if(bPathStart.getBounds().contains(x, y)) {
			selectedTile = new Tile(pathStart, -1, -1);
			editing.setSelectedTile(selectedTile);
			return;
		} else if(bPathEnd.getBounds().contains(x, y)) {
			selectedTile = new Tile(pathEnd, -2, -2);
			editing.setSelectedTile(selectedTile);
			return;
		}
		else {
			for(MyButton b : map.keySet()) {
				if(b.getBounds().contains(x, y)) {
					selectedTile = map.get(b).get(0);
					editing.setSelectedTile(selectedTile);
					currentButton = b;
					currentIndex = 0;
					return;
				}
			}
		}
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		bGround.setMouseOver(false);
		bVoid.setMouseOver(false);
		bPathStart.setMouseOver(false);
		bPathEnd.setMouseOver(false);
		for(MyButton b : map.keySet()) 
			b.setMouseOver(false);
		
		if(bMenu.getBounds().contains(x, y)) 
			bMenu.setMouseOver(true);
		else if(bSave.getBounds().contains(x, y))
			bSave.setMouseOver(true);
		else if(bGround.getBounds().contains(x, y)) 
			bGround.setMouseOver(true);
		else if(bVoid.getBounds().contains(x, y))
			bVoid.setMouseOver(true);
		else if(bPathStart.getBounds().contains(x, y)) 
			bPathStart.setMouseOver(true);
		else if(bPathEnd.getBounds().contains(x, y))
			bPathEnd.setMouseOver(true);
		else {
			for(MyButton b : map.keySet()) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}
	}
	
	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x, y)) 
			bMenu.setMousePressed(true);
		else if(bSave.getBounds().contains(x, y))
			bSave.setMousePressed(true);
		else if(bGround.getBounds().contains(x, y)) 
			bGround.setMousePressed(true);
		else if(bVoid.getBounds().contains(x, y))
			bVoid.setMousePressed(true);
		else if(bPathStart.getBounds().contains(x, y)) 
			bPathStart.setMousePressed(true);
		else if(bPathEnd.getBounds().contains(x, y))
			bPathEnd.setMousePressed(true);
		else {
			for(MyButton b : map.keySet()) {
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		bSave.resetBooleans();
		bGround.resetBooleans();
		bVoid.resetBooleans();
		bPathStart.resetBooleans();
		bPathEnd.resetBooleans();
		for (MyButton b : map.keySet())
			b.resetBooleans();
	}
	
	public BufferedImage getStartPathImg() {
		return pathStart;
	}
	
	public BufferedImage getEndPathImg() {
		return pathEnd;
	}
	
}
