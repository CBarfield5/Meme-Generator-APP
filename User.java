// Christopher Barfield 

import java.util.ArrayList;

public class User {
	private String userName;
	private ArrayList<Meme> memesCreated;
	private ArrayList<Meme> memesViewed;

	public User() {
		this.userName="Name";
		this.memesCreated= null;
		this.memesViewed= null;
	}
	
	public void rateMeme(Meme like,int rating) {
		
	}
	public Meme createMeme(BackgroundImage picture, String caption) {
		Meme m = new Meme();
		return m;
	}
	public boolean deleteMeme(Meme delete) {
		return true;
	}
	public void shareMeme(Meme share, Feed f) {
		
	}
	public void rateNextMemeFromFeed(Meme like,int rating) {
		
	}

	@Override
	public String toString() {
		return "";
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public ArrayList<Meme> getMemesCreated() {
		return memesCreated;
	}
	
	public void setMemesCreated(ArrayList<Meme> memesCreated) {
		this.memesCreated = memesCreated;
	}
	
	public ArrayList<Meme> getMemesViewed() {
		return memesViewed;
	}
	
	public void setMemesViewed(ArrayList<Meme> memesViewed) {
		this.memesViewed = memesViewed;
		
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}