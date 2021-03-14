//Christopher Barfield 
//cdb8da
//CS2110 - Homework 3
//3-13-2021

public class Rating {
	private User user;
	private int score;

	public Rating() {
		this.user = null;
		this.score = 0;
	}
	
	public Rating(User user, int score) {
		this.user = user;
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	
	public boolean setScore(int score) {
		if (score == 1 || score == 0 || score == -1) {
			this.score = score;
			return true;
			}
		else {
			return false;
		}
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		if (score == -1) {
			return "Rating was a downvote";
		}
		else if (score == 0) {
			return "Rating was a pass";
		}
		else {
			return "Rating was an upvote";
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
		Rating other = (Rating) obj;
		if (score != other.score)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	public static void main(String[] args) {
		// Constructors
		Rating rat1 = new Rating(null, 1);
		Rating rat2 = new Rating(null, 0);	
		rat1.score = 1;
		rat2.score = -1; 
		// toString methods
		System.out.println(rat1.toString());
		System.out.println(rat2.toString());
		// setScore methods
		System.out.println(rat1.setScore(1));
		System.out.println(rat2.setScore(0));
		// equals methods
		System.out.println(rat1.equals(rat2));
		System.out.println(rat2.equals(rat1));
		System.out.println(rat2.equals(rat2));
	}
}