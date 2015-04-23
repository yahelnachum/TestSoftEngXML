import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class LevelProfileXML {

	/**
	 * @param fileName
	 */
	public static void read(String fileName){
		try {

			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();		
			
			
			// Get level number
			String levelNumber = doc.getElementsByTagName("Level").item(0).getAttributes().item(0).getNodeValue();
			System.out.println("Level Number: " + levelNumber);
			
			// Get level name
			String levelName = doc.getElementsByTagName("Name").item(0).getTextContent();
			System.out.println("Level Name: " + levelName);
			
			// Get level type
			String levelType = doc.getElementsByTagName("Type").item(0).getTextContent();
			System.out.println("Level Type: " + levelType);
			
			System.out.println();
			
			// Get list of tile frequencies
			ArrayList<String> tileFrequencies = new ArrayList<String>();
			NodeList tileFrequenciesNodeList = ((Element) doc.getElementsByTagName("TileFrequency").item(0)).getElementsByTagName("Tile");
			for(int i = 0; i < tileFrequenciesNodeList.getLength(); i++){
				tileFrequencies.add("Tile Number: " + tileFrequenciesNodeList.item(i).getAttributes().item(1).getNodeValue());
				tileFrequencies.add("    Tile Frequency: " + tileFrequenciesNodeList.item(i).getAttributes().item(0).getNodeValue());
			}
			for(String str: tileFrequencies){
				System.out.println("	"+str);
			}
			
			System.out.println();
			
			// Get list of tile frequencies
			ArrayList<String> multiplierFrequencies = new ArrayList<String>();
			NodeList multiplierFrequenciesNodeList = ((Element) doc.getElementsByTagName("TileFrequency").item(0)).getElementsByTagName("Multiplier");
			for(int i = 0; i < multiplierFrequenciesNodeList.getLength(); i++){
				multiplierFrequencies.add("Multiplier: " + multiplierFrequenciesNodeList.item(i).getAttributes().item(1).getNodeValue());
				multiplierFrequencies.add("    Multiplier Frequency: " + multiplierFrequenciesNodeList.item(i).getAttributes().item(0).getNodeValue());
			}
			for(String str: multiplierFrequencies){
				System.out.println("	"+str);
			}
			
			// Get special move constraints
			String resetBoardMoves = doc.getElementsByTagName("ResetBoard").item(0).getTextContent();
			String swapTileMoves = doc.getElementsByTagName("SwapTile").item(0).getTextContent();
			String removeTileMoves = doc.getElementsByTagName("RemoveTile").item(0).getTextContent();
			String otherMoveMoves = doc.getElementsByTagName("OtherMove").item(0).getTextContent();
			
			System.out.println("ResetBoard: " + resetBoardMoves);
			System.out.println("SwapTiles : " + swapTileMoves);
			System.out.println("RemoveTile: " + removeTileMoves);
			System.out.println("OtherMove : " + otherMoveMoves);
			
			System.out.println();
			
			// Get point thresholds
			ArrayList<String> pointThresholds = new ArrayList<String>();
			NodeList pointThresholdNodeList = ((Element) doc.getElementsByTagName("PointThresholds").item(0)).getElementsByTagName("Point");
			for(int i = 0; i < pointThresholdNodeList.getLength(); i++){
				pointThresholds.add("Point Threshold: " + pointThresholdNodeList.item(i).getTextContent());
			}
			for(String str: pointThresholds){
				System.out.println(str);
			}
			
			System.out.println();
			
			// Get special move constraints
			String moveCount = doc.getElementsByTagName("MoveCount").item(0).getTextContent();
			String timer = doc.getElementsByTagName("Timer").item(0).getTextContent();
			
			System.out.println("Move Count: " + moveCount);
			System.out.println("Timer     : " + timer);
			
			System.out.println();
			
			// Get point thresholds
			ArrayList<String> boardRows = new ArrayList<String>();
			NodeList boardRowsNodeList = ((Element) doc.getElementsByTagName("Board").item(0)).getElementsByTagName("Row");
			for(int i = 0; i < boardRowsNodeList.getLength(); i++){
				boardRows.add("Row " + i + ": " + boardRowsNodeList.item(i).getTextContent());
			}
			for(String str: boardRows){
				System.out.println(str);
			}

		} catch (Exception e) {
			System.out.println("FILE NOT FOUND!");
		}
		
		/*return null;*/
	}
}
