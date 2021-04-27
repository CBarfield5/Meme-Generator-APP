// @author Christopher Barfield

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
			return user.getUserName() + " rated as a downvote";
		}
		else if (score == 0) {
			return user.getUserName() + " rated as a pass";
		}
		else {
			return user.getUserName() + " rated as an upvote";
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
}
