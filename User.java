// @author Christopher Barfield

import java.util.ArrayList;
import java.util.TreeSet;

public class User implements Comparable<User>{
	private String userName;
	private ArrayList<Meme> memesCreated;
	private TreeSet<Meme> memesViewed;

	public User() {
		this.userName="Name";
		this.memesCreated= new ArrayList<Meme>();
		this.memesViewed= new TreeSet<Meme>();
	}
	
	public User(String userName) {
		this.userName = userName;
		this.memesCreated= new ArrayList<Meme>();
		this.memesViewed= new TreeSet<Meme>();
	}
	
	public void rateMeme(Meme view, int rating) {
		if (view!=null) {
		memesViewed.add(view);
		Rating add = new Rating(this, rating);
		view.addRating(add);
		}
	}
	public Meme createMeme(BackgroundImage picture, String caption) {
		Meme m = new Meme(picture, caption, this);
		this.memesCreated.add(m);
		return m;
	}
	public boolean deleteMeme(Meme delete) {
		ArrayList<Meme> build = new ArrayList<Meme>();
		build = this.getMemesCreated();	
		if (build.contains(delete)) {
			//int memeInd = build.indexOf(delete);
			//if (build.get(memeInd).getShared() == false);{
				//build.remove(memeInd);
				//this.setMemesCreated(build);
				//return true;
			//}
		}
		return false;
	}

	public void shareMeme(Meme meme, Feed feed)
	{
		meme.setShared(true);
		ArrayList<Meme> build = feed.getMemes();
		build.add(meme); 
		feed.setMemes(build);
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
	
	public int compareTo(User o1) {
		//Sort by username
		
		int name = this.getUserName().compareTo(o1.getUserName());
		if (name != 0) 
			return name;
		
		//Sort by number of memes created
		
		int memes = o1.getMemesCreated().size() - this.getMemesCreated().size();
		if (memes != 0)
			return memes;
		return memes;
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
		ArrayList<Meme> accumList = new ArrayList<Meme>();
		for (Meme view : memesViewed) {
			accumList.add(view);
		}
		return accumList;
	}
	
	public void setMemesViewed(ArrayList<Meme> memesViewed) {
		ArrayList<Meme> setView = new ArrayList<Meme>();
		for (Meme set : memesViewed) {
			setView.add(set);
		}
	}
	
	public double calculateReputation() {
		double calc = 0.0;
		for(Meme meme : memesCreated) {
			if(meme == null) {
				calc += 0.0;
			}
			else {
				calc += meme.calculateOverallRating();
			}
		}
		if (memesCreated.size() == 0) {
			calc = 0.0;
		}
		else {
			calc = calc/(memesCreated.size());
		}
		return calc;
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
		return this.userName + " has rated (" + this.getMemesViewed().size() + ") memes, (" + calculateReputation() + ")";
	}
}
