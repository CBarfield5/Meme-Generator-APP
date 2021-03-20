//Christopher Barfield 
//cdb8da
//CS2110 - Homework 4
//3-20-2021

import java.util.ArrayList;
public class Feed {
	private ArrayList<Meme> memes;

	public Feed() {
		this.memes= new ArrayList<>();
	}
	
	public Meme getNewMeme(User u) {
		for(Meme memes : this.memes) {
			if(!(u.getMemesViewed().contains(memes))) {
				if(!(u.getMemesCreated().contains(memes))) {
					return memes;
				}
			}
		}
		return null;
	}
	
	public void setMemes(ArrayList<Meme> memes) {
		this.memes = memes;
	}

	public ArrayList<Meme> getMemes() {
		return memes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feed other = (Feed) obj;
		if (memes == null) {
			if (other.memes != null)
				return false;
		} else if (!memes.equals(other.memes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String returnString = "";
		for(Meme memes : this.memes) {
			returnString += memes;
		}
		return returnString;
	}
}