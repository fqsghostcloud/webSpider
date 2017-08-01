package webspider.linksqueue;

import redis.clients.jedis.*;

public class RedisQueue {
	private Jedis jedis;
	private String listName = "unvisitedQueue";
	
	public RedisQueue() {
		jedis = webspider.config.Config.jedis;
	}
	
	public void enQueue(String url ) {
		jedis.rpush(listName, url);
	}
	
	public String deQueue() {
		return jedis.lpop(listName);
	}
	
	public boolean isEmpty() {
		if(jedis.llen(listName) != 0) {
			return false;
		}
		else {
			return true;
		}
	}
	

}
