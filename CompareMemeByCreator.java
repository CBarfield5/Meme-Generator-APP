// @author Christopher Barfield

import java.util.ArrayList;
import java.util.Comparator;

public class CompareMemeByCreator implements Comparator<Meme>{

	@Override
	public int compare(Meme o1, Meme o2) {
		//Sort by creator
		
		int creator = o1.getCreator().compareTo(o2.getCreator());
		if (creator!=0)
			return creator;
		
		//Sort by rating
		
		int rating = (int) (o2.calculateOverallRating() - o1.calculateOverallRating());
		if (rating != 0)
			return rating;
		
		//Sort by caption
		
		int caption = o1.getCaption().compareTo(o2.getCaption());
		if (caption != 0)
			return caption;
		
		//Sort by background image 
		
		int back = o1.getBackgroundImage().compareTo(o2.getBackgroundImage());
		if (back != 0)	
			return back;
		
		int shared = 0;
		//Sort by shared Memes first
		if (o1.getShared() == true && o2.getShared() == true) {
			shared = 0; 
		}
		if (o1.getShared() == false && o2.getShared() == false) {
			shared = 0; 
		}
		if (o1.getShared() == true && o2.getShared() == false) {
			shared = -1;	
		}
		if (o1.getShared() == false && o2.getShared() == true) {
			shared = 1;
		}
		return shared;
	}
}
