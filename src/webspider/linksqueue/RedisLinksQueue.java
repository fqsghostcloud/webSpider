package webspider.linksqueue;

public class RedisLinksQueue {
	private Queue unvisitedUrl;
	private RedisSet visitedUrl;
	
	public RedisLinksQueue() {
		unvisitedUrl = new Queue();
		visitedUrl = new RedisSet();
	}
	
	public boolean UnvisitedUrlIsEmpty() {
		return unvisitedUrl.isQueueEmpty();
	}
	
	public void addUnvisitedUrl(String url) {
		if(url != null && !url.trim().equals("") 
				&& !unvisitedUrl.contains(url) && !visitedUrl.isContains(url)) {
			unvisitedUrl.enQueue(url);
			
		}
	}
	
	public String getUnvisitedUrl() {
		return unvisitedUrl.deQueue();
	}
	
	public void addVisitedUrl(String url) {
		visitedUrl.addVisitedUrl(url);
	}
	
	public long getNumberOfVisitedUrl() {
		return visitedUrl.VisitedUrlCounts();
	}
	
	

}
