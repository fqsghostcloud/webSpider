package webspider.linksqueue;
import java.util.LinkedList;

public class Queue {
	
	public Queue() {
		
	}
	
	private LinkedList<String> Queue = new LinkedList<>();
	
	public void enQueue(String url) {
		Queue.addLast(url);
	}
	
	public String deQueue() {
		return Queue.removeFirst();
	}
	
	public boolean isQueueEmpty() {
		return Queue.isEmpty();
	}
	
	public void clearQueue() {
		Queue.clear();
	}
	
	public boolean contains(String url) {
		return Queue.contains(url);
	}
	


}
