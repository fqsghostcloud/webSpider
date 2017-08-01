package webspider.linksqueue;
import java.util.HashSet;
import java.util.Set;
public class LinksQueue {
	
	private static Set<String> visitedUrl = new HashSet<>();
	private Queue unVisitedUrl = new Queue();
	
	public LinksQueue() {
		
	}
	
	//get unvisited queue
	public Queue getUnvisitedQueue() {
		return unVisitedUrl;
	}
	
	//get unvisited url
	public String getUnvisitedUrl() {
		return unVisitedUrl.deQueue();
	}
	
	//add visited url
	public static void addVisitedUrl(String url) {
		visitedUrl.add(url);
	}
	
	//add unvisited url
	public void addUnvisitedUrl(String url) {
		if(url != null && !url.trim().equals("")
				&& !visitedUrl.contains(url)
				&& !unVisitedUrl.contains(url)) {
			unVisitedUrl.enQueue(url);
		}
	}
	
	//get number of visited url
	public static int getNumberOfVisitedUrl() {
		return visitedUrl.size();
	}
	
	//judge unvisited url is empty
	public boolean unvisitedUrlEmpty() {
		return unVisitedUrl.isQueueEmpty();
	}


}
