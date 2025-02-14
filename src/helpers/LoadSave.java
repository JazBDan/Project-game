package helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import objects.PathPoint;

public class LoadSave {
	//for jar file
	public static String homePath = System.getProperty("user.home");
	public static String saveFolder = "TowerDefenseLevels";
	public static String levelFile = "Unnamed_Level.txt";
	public static String filePath = homePath + File.separator + saveFolder + File.separator + levelFile;
	private static File lvlFile = new File(filePath);
	
	public static void CreateFolder () {
		File folder = new File(homePath + File.separator + saveFolder);
		if(!folder.exists())
			folder.mkdir();
	}
	
	public static BufferedImage getSpriteTD() {
    	
		BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("TowerDefenseSprite.png");
      
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return img;
	}
	
	//Creation of level
	public static void CreateLevel(int[] idArray) {
		if(lvlFile.exists()) {
			System.out.println("File: " + lvlFile + " already exists");
			return;
		} else {
			try {
				lvlFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			WriteToFile(idArray, new PathPoint(0, 0), new PathPoint(0, 0));
		}
	}
	
	private static void WriteToFile(int[] idArray, PathPoint start, PathPoint end) {
		try {
			PrintWriter pw = new PrintWriter(lvlFile);
			for(Integer i : idArray)
				pw.println(i);
			
			pw.println(start.getxCord());
			pw.println(start.getyCord());
			pw.println(end.getxCord());
			pw.println(end.getyCord());
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void SaveLevel(int[][] idArray, PathPoint start, PathPoint end) {
		if(lvlFile.exists()) {
			WriteToFile(Utilithings.TwoDto1DintArr(idArray), start, end);
		} else {
			System.out.println("File: " + lvlFile + " does not exists");
			return;
		}
	}
	
	private static ArrayList<Integer> ReadFromFile() {
		ArrayList<Integer> list = new ArrayList<>();
		
		try {
			Scanner scan = new Scanner(lvlFile);
			
			while(scan.hasNextLine()) {
				list.add(Integer.parseInt(scan.nextLine()));
			}
			
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public static ArrayList<PathPoint> getLevelPathPoints() {
		if(lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile();
			ArrayList<PathPoint> points = new ArrayList<>();
			points.add(new PathPoint(list.get(400), list.get(401)));
			points.add(new PathPoint(list.get(402), list.get(403)));
			return points;
		} else {
			System.out.println("File: " + lvlFile + " does not exists");
			return null;
		}
	}
	
	public static int[][] GetLevelData() {		
		if(lvlFile.exists()) {
			ArrayList<Integer> list = ReadFromFile();
			return Utilithings.ArrayListTo2Dint(list, 20, 20);
		} else {
			System.out.println("File: " + lvlFile + " does not exists");
			return null;
		}
	}
}
