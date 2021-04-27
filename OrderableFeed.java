// @author Christopher Barfield

import java.util.Collections;

public class OrderableFeed extends Feed {
	
	public void sortByCaption() {
		//Sort by natural ordering
		
		Collections.sort(this.getMemes());
	}
	public void sortByRating() {
		//Sort by rating using CompareMemeByRating
		
		Collections.sort(this.getMemes(), new CompareMemeByRating());
	}
	public void sortByCreator() {
		//Sort by creator using CompareMemeByCreator
		
		Collections.sort(this.getMemes(), new CompareMemeByCreator());
	}
}
