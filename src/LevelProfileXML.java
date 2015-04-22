import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class LevelProfileXML {

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
			
			// Get list of tile frequencies
			ArrayList<String> frequencies = new ArrayList<String>();
			NodeList frequenciesNodeList = ((Element) doc.getElementsByTagName("TileFrequency").item(0)).getElementsByTagName("Tile");
			for(int i = 0; i < frequenciesNodeList.getLength(); i++){
				frequencies.add(frequenciesNodeList.item(i).getAttributes().item(1).getNodeValue() + " " + frequenciesNodeList.item(i).getAttributes().item(0).getNodeValue());
			}
			for(String str: frequencies){
				System.out.println("	"+str);
			}
			
			/*// Get highest level unlocked by user
			int highestLevelUnlocked = Integer.parseInt(doc.getElementsByTagName("HighestLevelUnlocked").item(0).getTextContent());
			System.out.println("Highest Level Unlocked: " + highestLevelUnlocked);
			
			// Get list of badges that the user has unlocked
			ArrayList<String> badges = new ArrayList<String>();
			NodeList badgeNodeList = ((Element) doc.getElementsByTagName("Badges").item(0)).getElementsByTagName("Name");
			for(int i = 0; i < badgeNodeList.getLength(); i++){
				badges.add(badgeNodeList.item(i).getTextContent());
			}
			System.out.println("Badges Unlocked:");
			for(String str: badges){
				System.out.println("	"+str);
			}
			
			// Get list of levels, their high scores, and star equivalents
			NodeList levelScoreNodeList = ((Element) doc.getElementsByTagName("LevelScores").item(0)).getElementsByTagName("Level");
			ArrayList<String> levelScores = new ArrayList<String>();
			ArrayList<LevelHighScore> levelHighScores = new ArrayList<LevelHighScore>();
			for(int i = 0; i < levelScoreNodeList.getLength(); i++){
				String str = "";
				str += "	LevelNumber: " + levelScoreNodeList.item(i).getAttributes().item(0).getTextContent() + "\n";
				str += "	HighScore: " + levelScoreNodeList.item(i).getAttributes().item(1).getTextContent() + "\n";
				str += "	Stars: " + levelScoreNodeList.item(i).getAttributes().item(2).getTextContent() + "\n";
				levelScores.add(str);
				
				levelHighScores.add(new LevelHighScore(Integer.parseInt(levelScoreNodeList.item(i).getAttributes().item(0).getTextContent()), 
													   Integer.parseInt(levelScoreNodeList.item(i).getAttributes().item(1).getTextContent()), 
													   Integer.parseInt(levelScoreNodeList.item(i).getAttributes().item(2).getTextContent())));
			}
			System.out.println("Level Scores:");
			for(String str: levelScores){
				System.out.println(str);
			}
			
			// Get the aesthetic the player uses
			String aesthetic = doc.getElementsByTagName("Aesthetic").item(0).getTextContent();
			System.out.println("Aesthetic: " + aesthetic);
			
			// Put all the data into a user profile object
			UserProfile userProfile = new UserProfile(userName, highestLevelUnlocked, badges, levelHighScores, new Aesthetic(aesthetic));
			
			System.out.println("============");
			System.out.println(userProfile.toString());
			
			return userProfile;*/

		} catch (Exception e) {
			System.out.println("FILE NOT FOUND!");
		}
		
		/*return null;*/
	}
}
