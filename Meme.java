//Christopher Barfield 
//cdb8da
//CS2110 - Homework 3
//3-13-2021

import java.util.Arrays;

public class Meme {
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
		shared = true;
	}
	public Meme(BackgroundImage a, String caption, User user) {
		this.creator= user;
		this.backgroundImage= a;
		this.ratings= new Rating[10];
		this.caption = caption;
		this.captionVerticalAlign = "bottom";
		shared = true;
	}
	
	public boolean addRating (Rating rating) {
		int len = ratings.length;
		if (rating == null) {
			return false;
		}
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
		
	private int numRatingsPos () {
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
	
	private int numRatingsNeg () {
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
		return backgroundImage + " '" + caption + "' " + String.valueOf(calculateOverallRating()) + "[+1: " + String.valueOf(numRatingsPos()) + ", -1: " + String.valueOf(numRatingsNeg()) + "]";
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
		if (captionVerticalAlign == null) {
			if (other.captionVerticalAlign != null)
				return false;
		} else if (!captionVerticalAlign.equals(other.captionVerticalAlign))
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
	public static void main(String[] args) {
		
		//Constructors
		BackgroundImage back1 = new BackgroundImage("File1", "Chris Stays Inside", "Christopher wastes time playing his playstation 5 instead of being productive");
		BackgroundImage back2 = new BackgroundImage("File2","Chris Goes Outside","Christopher takes his talents outside to stay in shape");
		Meme me1 = new Meme(back1, "Christopher wastes time playing his playstation 5 instead of being productive", null);
		Meme me2 = new Meme(back2, "Christopher takes his talents outside to stay in shape", null);
		Rating rat1 = new Rating(null, 0);
		Rating rat2 = new Rating(null, -1);
		Rating rat3 = new Rating(null, 1);
		Rating rat4 = new Rating(null, 1);
		// toString methods
		System.out.println(me1.toString());
		System.out.println(me2.toString());
		// Add Rating
		System.out.println(me1.addRating(rat1));
		System.out.println(me2.addRating(rat2));
		// Vertical Align
		System.out.println(me1.setCaptionVerticalAlign("top"));
		System.out.println(me2.setCaptionVerticalAlign("middle"));
		// equals methods
		System.out.println(me1.equals(me2));
		System.out.println(me2.equals(me2));
		System.out.println(me2.equals(me1));
		
		
	}
}