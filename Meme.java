//Christopher Barfield 
//cdb8da
//CS2110 - Homework 4
//3-20-2021

import java.util.Arrays;

public class Meme implements Comparable<Meme>{
	private User creator;
	private BackgroundImage backgroundImage; 
	private Rating[] ratings; 
	private String caption;
	private String captionVerticalAlign;
	private boolean shared;

	public Meme() {
		this.creator= new User();
		this.backgroundImage= new BackgroundImage("imageFilename","title","description");
		this.ratings= new Rating[10];
		this.caption = "";
		this.captionVerticalAlign = "bottom";
		shared = false;
	}
	public Meme(BackgroundImage a, String caption, User user) {
		this.creator= user;
		this.backgroundImage= a;
		this.ratings= new Rating[10];
		this.caption = caption;
		this.captionVerticalAlign = "bottom";
	}
	
	public boolean addRating (Rating rating) {
		int len = ratings.length;
		for (int i = 0; i< len; i++) {
			if (ratings[i] == null) {
				ratings[i] = rating;
				return true;
			}
		}
		for (int x = 0; x < len -1; x++) {
			ratings[x] = ratings[x+1];
		}
			ratings[len-1]= rating;
		return true;
	}
		
	public int numRatingsPos () {
		int len = ratings.length;
		int sum = 0;
		for (int i = 0; i< len; i++) {
			if(ratings[i] != null) {
				if(ratings[i].getScore() > 0) {
				sum ++;
				}
			}
		}
		return sum;
	}
	
	public int compareTo(Meme o1) {
		//Sort by caption
		
		int caption = this.getCaption().compareTo(o1.getCaption());
		if (caption != 0) 
			return caption;
		
		//Sort by background image
		
		int back = this.backgroundImage.compareTo(o1.getBackgroundImage());
		if (back != 0)
			return back;
		
		//Sort by ratings
		
		int ratings = (int) (o1.calculateOverallRating() - this.calculateOverallRating());
		if (ratings != 0)
			return ratings;
		
		//Sort by shared Memes first
		
		int shared = 0;
		if (this.getShared() == true && o1.getShared() == true) {
			shared = 0; 
		}
		if (this.getShared() == false && o1.getShared() == false) {
			shared = 0; 
		}
		if (this.getShared() == true && o1.getShared() == false) {
			shared = -1;	
		}
		if (this.getShared() == false && o1.getShared() == true) {
			shared = 1;
		}
		return shared;
	}
	
	public int numRatingsNeg () {
		int len = ratings.length;
		int sum = 0;
		for (int i = 0; i< len; i++) {
			if(ratings[i] != null) {
				if(ratings[i].getScore() < 0) {
				sum ++;
				}
			}
		}
		return sum;
	}
	
	public double calculateOverallRating() {
		int len = ratings.length;
		double sum = 0.0;
		for (int i = 0; i < len; i++) {
			if (ratings[i] != null) {
				sum += ratings[i].getScore();
			}
		}
		return sum;
	}
	
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(BackgroundImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	public Rating[] getRatings() {
		return ratings;
	}
	public void setRatings(Rating[] ratings) {
		this.ratings = ratings;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getCaptionVerticalAlign() {
		return captionVerticalAlign;
	}
	public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
		if (captionVerticalAlign == "bottom" || captionVerticalAlign == "middle" || captionVerticalAlign == "top") {
			this.captionVerticalAlign = captionVerticalAlign;
			return true;
		}
		else {
			return false;
		}
	}
	public boolean getShared() {
		return shared;
	}
	public void setShared(boolean shared) {
		this.shared = shared;
	}
	
	@Override
	public String toString() {
		return backgroundImage + " '" + caption + "' " + calculateOverallRating() + " [+1: " + String.valueOf(numRatingsPos()) + ", -1: " + String.valueOf(numRatingsNeg()) + "] - created by " + this.getCreator().getUserName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meme other = (Meme) obj;
		if (backgroundImage == null) {
			if (other.backgroundImage != null)
				return false;
		} else if (!backgroundImage.equals(other.backgroundImage))
			return false;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (!captionVerticalAlign.equals(other.captionVerticalAlign))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (!Arrays.equals(ratings, other.ratings))
			return false;
		if (shared != other.shared)
			return false;
		return true;
		}
	//public static void main(String[] args) {
		//User u1 = new User();
		//BackgroundImage back = new BackgroundImage();
		//Meme me = new Meme(back, "Caption", u1);
		//Rating rat1 = new Rating();
		//System.out.println(me.addRating(rat1));
		//System.out.println(me.toString());
	//}
}