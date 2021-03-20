//Christopher Barfield 
//cdb8da
//CS2110 - Homework 4
//3-20-2021
import java.util.ArrayList;

public class User {
	private String userName;
	private ArrayList<Meme> memesCreated;
	private ArrayList<Meme> memesViewed;

	public User() {
		this.userName="Name";
		this.memesCreated= new ArrayList<Meme>();
		this.memesViewed= new ArrayList<Meme>();
	}
	
	public User(String userName) {
		this.userName = userName;
		this.memesCreated= new ArrayList<Meme>();
		this.memesViewed= new ArrayList<Meme>();
	}
	
	public void rateMeme(Meme view, int rating) {
		this.memesViewed.add(view);
		new Rating(this, rating);
	}
	public Meme createMeme(BackgroundImage picture, String caption) {
		Meme m = new Meme(picture, caption, this);
		this.memesCreated.add(m);
		return m;
	}
	public boolean deleteMeme(Meme delete) {
		for (int i = 0; i < memesCreated.size(); i++) {
			if(memesCreated.get(i).equals(delete)) {
				if (delete.getShared() == false) {
					memesCreated.remove(delete);
					return true;
					}
				else {
					return false;
					}
			}
		}
		return false;
	}

	public void shareMeme(Meme share, Feed f) {
		share.setShared(true);
		ArrayList<Meme> nextFeed = new ArrayList<Meme>();
		nextFeed.addAll(f.getMemes());
		nextFeed.add(share);
		f.setMemes(nextFeed);
	}
	
	public boolean rateNextMemeFromFeed(Feed time, int rating) {
		Meme meme = time.getNewMeme(this);
		if(meme != null) {
			rateMeme(meme, rating);
			memesViewed.add(meme);
			return true;
			}
		else {
			return false;
		}
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
	
	public double calculateReputation() {
		double calc = 0;
		if(memesCreated.size() == 0) {
			return 0.0;
		}
		else {
			for (int i = 0; i < memesCreated.size(); i++) {
				if(memesCreated.get(i).getRatings()== null) {
					calc += 0.0;
				}
				else {
					calc += memesCreated.get(i).calculateOverallRating();
				}
			}
			return calc /memesCreated.size();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (memesCreated == null) {
			if (other.memesCreated != null)
				return false;
		} else if (!memesCreated.equals(other.memesCreated))
			return false;
		if (memesViewed == null) {
			if (other.memesViewed != null)
				return false;
		} else if (!memesViewed.equals(other.memesViewed))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		int size = 0;
		if (this.memesViewed == null) {
			size = 0;
		}
		else {
			size = this.memesViewed.size();
		}
		return this.userName + " has rated (" + size + ") memes, (" + calculateReputation() + ")";
	}
}
