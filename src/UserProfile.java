import java.util.ArrayList;

public class UserProfile {

	String userName;
	int highestLevel;
	ArrayList<String> badgesEarned;
	ArrayList<LevelHighScore> levelHighScores;
	Aesthetic aesthetic;

	public UserProfile(String userName, int highestLevel,
			ArrayList<String> badgesEarned,
			ArrayList<LevelHighScore> levelHighScores, Aesthetic aesthetic) {
		this.userName = userName;
		this.highestLevel = highestLevel;
		this.badgesEarned = badgesEarned;
		this.levelHighScores = levelHighScores;
		this.aesthetic = aesthetic;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public int getHighestLevel(){
		return highestLevel;
	}
	
	public ArrayList<String> getBadgesEarned(){
		return badgesEarned;
	}
	
	public ArrayList<LevelHighScore> getLevelHighScore(){
		return levelHighScores;
	}
	
	public int getHighScoreOfLevel(int level){
		for(LevelHighScore lhs: levelHighScores){
			if(lhs.getLevel() == level){
				return lhs.getHighScore();
			}
		}
		
		return -1;
	}
	
	public Aesthetic getAesthetic(){
		return aesthetic;
	}
	
	public void setHighestLevel(int highestLevel){
		this.highestLevel = highestLevel;
	}
	
	public void setBadgesEarned(ArrayList<String> badgesEarned){
		this.badgesEarned = badgesEarned;
	}
	
	public void setLevelHighScores(ArrayList<LevelHighScore> levelHighScores){
		this.levelHighScores = levelHighScores;
	}
	
	public void setAesthetic(Aesthetic aesthetic){
		this.aesthetic = aesthetic;
	}
	
	public String toString(){
		String str = "";
		str += "User Name: " + userName + "\n";
		str += "	Highest Level: " + highestLevel + "\n";
		str += "	Badges Earned:\n";
		for(String badge: badgesEarned){
			str += "		" + badge + "\n";
		}
		str += "	Level High Scores:\n";
		for(LevelHighScore lhs: levelHighScores){
			str += "		Level: " + lhs.getLevel() + "\n";
			str += "		  High Score: " + lhs.getHighScore() + "\n";
			str += "		  Stars: " + lhs.getStars() + "\n";
		}
		
		str += "	Aesthetic: " + aesthetic.getAesthetic();
		
		return str;
	}
}
