package webspider.config;

import redis.clients.jedis.*;


public class Config {

	public String rootDomain;
	public String ip;
	public int port;
	public String protocol;
	public boolean redirecAllowed;
	public int socketTimeout;
	public int connectTimeout;
	public String userAgent;
	public static Jedis jedis;

	
	
	public Config() {
		// spider http config
		this.redirecAllowed = true;
		this.socketTimeout = 100000;
		this.connectTimeout = 100000;
		this.userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) "
				+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";	
		//redis config
		/*jedis = new Jedis("localhost");
		System.out.println("****************************************");
		System.out.println("reids connection is: " + jedis.ping() );*/
		
	}
	

}
