package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpers.Imagefix;
import helpers.LoadSave;
import objects.Tile;
import static helpers.Constants.Tiles.*;

public class TileManager {
	
	public Tile GROUND, VOID, HPATH, VPATH, TL_CORNER, TR_CORNER, BL_CORNER, BR_CORNER,
	LtDPATH, LtUPATH, UtRPATH, DtRPATH, U_EDGE, R_EDGE, D_EDGE, L_EDGE, 
	TL_SMALLCORNER, TR_SMALLCORNER, BL_SMALLCORNER, BR_SMALLCORNER, U_BCORNER, R_BCORNER, D_BCORNER, L_BCORNER,
	HMIDEDGE, VMIDEDGE;
	public BufferedImage tdsprite;
	
	public ArrayList<Tile> tiles = new ArrayList<>();
	
	public ArrayList<Tile> paths = new ArrayList<>();
	public ArrayList<Tile> tpaths = new ArrayList<>();
	public ArrayList<Tile> corners = new ArrayList<>();
	public ArrayList<Tile> edge = new ArrayList<>();
	public ArrayList<Tile> smolcorners = new ArrayList<>();
	public ArrayList<Tile> bigcorners = new ArrayList<>();
	public ArrayList<Tile> midedge = new ArrayList<>();
	
	public TileManager() {
		
		loadTDSprites();
		createTiles();
		
	}
	
	private void createTiles() {
		
		int id = 0;
		//Basic
		tiles.add(GROUND = new Tile(getGroundAnimatedSprites(4, 0),id++, GROUND_TILE));
		tiles.add(VOID = new Tile(getVoidAnimatedSprites(0, 0),id++, VOID_TILE));
		//Paths
		paths.add(HPATH = new Tile(getSprite(0, 1),id++, PATH_TILE));
		paths.add(VPATH = new Tile(Imagefix.getRotImg(getSprite(0, 1), 90),id++, PATH_TILE));
		//Corners
		corners.add(TL_CORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(2, 1), 0), id++, VOID_TILE));
		corners.add(TR_CORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(2, 1), 90), id++, VOID_TILE));
		corners.add(BR_CORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(2, 1), 180), id++, VOID_TILE));
		corners.add(BL_CORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(2, 1), 270), id++, VOID_TILE));
		//Turning Paths
		tpaths.add(LtDPATH = new Tile(getSprite(1, 1), id++, PATH_TILE));
		tpaths.add(LtUPATH = new Tile(Imagefix.getRotImg(getSprite(1, 1), 90), id++, PATH_TILE));
		tpaths.add(UtRPATH = new Tile(Imagefix.getRotImg(getSprite(1, 1), 180), id++, PATH_TILE));
		tpaths.add(DtRPATH = new Tile(Imagefix.getRotImg(getSprite(1, 1), 270), id++, PATH_TILE));
		//Edges
		edge.add(U_EDGE = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(4, 1), 0), id++, VOID_TILE));
		edge.add(R_EDGE = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(4, 1), 90), id++, VOID_TILE));
		edge.add(D_EDGE = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(4, 1), 180), id++, VOID_TILE));
		edge.add(L_EDGE = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(4, 1), 270), id++, VOID_TILE));
		//Small corners
		smolcorners.add(TL_SMALLCORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(3, 1), 0), id++, VOID_TILE));
		smolcorners.add(TR_SMALLCORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(3, 1), 90), id++, VOID_TILE));
		smolcorners.add(BR_SMALLCORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(3, 1), 180), id++, VOID_TILE));
		smolcorners.add(BL_SMALLCORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(3, 1), 270), id++, VOID_TILE));
		//Big corners
		bigcorners.add(TL_SMALLCORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(5, 1), 0), id++, VOID_TILE));
		bigcorners.add(TR_SMALLCORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(5, 1), 90), id++, VOID_TILE));
		bigcorners.add(BR_SMALLCORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(5, 1), 180), id++, VOID_TILE));
		bigcorners.add(BL_SMALLCORNER = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(5, 1), 270), id++, VOID_TILE));
		//Mid edges
		midedge.add(HMIDEDGE = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(6, 1), 0),id++, VOID_TILE));
		midedge.add(VMIDEDGE = new Tile(Imagefix.getRotImg(getVoidAnimatedSprites(0, 0), getSprite(6, 1), 90),id++, VOID_TILE));
		
		tiles.addAll(paths);
		tiles.addAll(corners);
		tiles.addAll(tpaths);
		tiles.addAll(edge);
		tiles.addAll(smolcorners);
		tiles.addAll(bigcorners);
		tiles.addAll(midedge);
	}
	
	private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {
		return new BufferedImage[] {getSprite(firstX, firstY), getSprite(secondX, secondY)};
	}
	
	private void loadTDSprites() {
		
		tdsprite = LoadSave.getSpriteTD();
		
	}
	
	public Tile getTile(int id) {
		return tiles.get(id);
	}
	
	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}
	
	public BufferedImage getAniSprite(int id, int animationIndex) {
		return tiles.get(id).getSprite(animationIndex);
	}
	
	private BufferedImage[] getVoidAnimatedSprites(int xCord, int yCord) {
		BufferedImage[] arr = new BufferedImage[4];
		for(int i = 0; i < 4; i++) {
			arr[i] = getSprite(xCord + i, yCord);
		}
		return arr;
	}
	
	private BufferedImage[] getGroundAnimatedSprites(int xCord, int yCord) {
		BufferedImage[] arr = new BufferedImage[6];
		for(int i = 0; i < 6; i++) {
			arr[i] = getSprite(xCord + i, yCord);
		}
		return arr;
	}
	
	private BufferedImage getSprite(int xCord, int yCord) {
		return tdsprite.getSubimage(xCord * 32, yCord * 32, 32, 32);
	}
	
	public boolean isSpriteAnimated(int spriteID) {
		return tiles.get(spriteID).isAnimated();
	}

	public ArrayList<Tile> getPaths() {
		return paths;
	}

	public ArrayList<Tile> getTurnpaths() {
		return tpaths;
	}

	public ArrayList<Tile> getCorners() {
		return corners;
	}
	
	public ArrayList<Tile> getEdges() {
		return edge;
	}

	public ArrayList<Tile> getSmallCorners() {
		return smolcorners;
	}
	
	public ArrayList<Tile> getBigCorners() {
		return bigcorners;
	}
	
	public ArrayList<Tile> getMidEdges() {
		return midedge;
	}
	
}
