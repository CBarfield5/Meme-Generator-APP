import java.util.Comparator;

public class CompareMemeByRating implements Comparator<Meme> {

	@Override
	public int compare(Meme o1, Meme o2) {
		//Sort by overall rating
		
		int ratings = (int)(o2.calculateOverallRating() - o1.calculateOverallRating());
		if (ratings != 0)
			return ratings;
		
		//Sort by caption
		
		int caption = o1.getCaption().compareTo(o2.getCaption());
		if (caption != 0) 
			return caption;
		
		//Sort by background image natural ordering
		
		int back = o1.getBackgroundImage().compareTo(o2.getBackgroundImage());
		if (back != 0)
			return back;
		
		//Sort by creator natural ordering
		
		int creator = o1.getCreator().compareTo(o2.getCreator());
		return creator;
	}

}
