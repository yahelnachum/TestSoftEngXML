import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class UserProfileXML {

	public static UserProfile read(String fileName){
		try {

			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();		
			
			// Get user Name
			String userName = doc.getElementsByTagName("UserProfile").item(0).getAttributes().item(0).getNodeValue();
			System.out.println("User Name: " + userName);
			
			UserProfile userProfile = new UserProfile(userName);
			
			// Get highest level unlocked by user
			int highestLevelUnlocked = Integer.parseInt(doc.getElementsByTagName("HighestLevelUnlocked").item(0).getTextContent());
			userProfile.setHighestLevel(highestLevelUnlocked);
			System.out.println("Highest Level Unlocked: " + highestLevelUnlocked);
			
			// Get list of badges that the user has unlocked
			ArrayList<String> badges = new ArrayList<String>();
			NodeList badgeNodeList = ((Element) doc.getElementsByTagName("Badges").item(0)).getElementsByTagName("Name");
			for(int i = 0; i < badgeNodeList.getLength(); i++){
				badges.add(badgeNodeList.item(i).getTextContent());
				userProfile.addBadgeEarned(badgeNodeList.item(i).getTextContent());
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
				
				userProfile.addLevelHighScore(Integer.parseInt(levelScoreNodeList.item(i).getAttributes().item(0).getTextContent()), 
											  Integer.parseInt(levelScoreNodeList.item(i).getAttributes().item(1).getTextContent()), 
											  Integer.parseInt(levelScoreNodeList.item(i).getAttributes().item(2).getTextContent()));
			}
			System.out.println("Level Scores:");
			for(String str: levelScores){
				System.out.println(str);
			}
			
			// Get the aesthetic the player uses
			String aesthetic = doc.getElementsByTagName("Aesthetic").item(0).getTextContent();
			System.out.println("Aesthetic: " + aesthetic);
			
			userProfile.setAestheticName(aesthetic);
			
			System.out.println("============");
			System.out.println(userProfile.toString());
			
			return userProfile;

		} catch (Exception e) {
			System.out.println("FILE NOT FOUND!");
		}
		
		return null;
	}
}
