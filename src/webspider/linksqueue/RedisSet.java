package webspider.linksqueue;

import redis.clients.jedis.Jedis;

public class RedisSet {
	private Jedis jedis;
	private String keyName = "VisitedUrlSet";
	
	public RedisSet() {
		jedis = webspider.config.Config.jedis;
	}
	
	public void addVisitedUrl(String url) {
		jedis.sadd(keyName, url);
	}
	
	public boolean isContains(String url) {
		return jedis.sismember(keyName, url);
	}
	
	public long VisitedUrlCounts() {
		return jedis.scard(keyName);
	}
}
