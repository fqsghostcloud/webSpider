package webspider;

import java.io.IOException;
import java.util.Map;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class DownloadPage {

	public String currentUrl;
	public int statusCode;
	private String htmlString;
	private RequestConfig requestConfig;
	private Map<String, String> headers;
	private HttpEntity entity;
	private CloseableHttpResponse response;
	private CloseableHttpClient httpClient;


	public DownloadPage(String currentUrl, Map<String,String> headers, RequestConfig requestConfig){
		this.currentUrl = currentUrl;
		this.headers = headers;
		this.requestConfig = requestConfig;
		httpClient = HttpClients.createDefault();
	}


	
	// download HTML page by GET method
	public String DownloadByGetMethod() {

		HttpGet request = new HttpGet(currentUrl);
		request.setConfig(requestConfig);

		//设置http header
		if(headers!=null && headers.size()>0){
			for(String key: headers.keySet()){
				request.addHeader(key, headers.get(key));
			}
		}

		try {
			response = httpClient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			entity = response.getEntity();

			System.out.println("\n---------------------------------------------------------------------");
			System.out.println("**Download Page: " + currentUrl);
			System.out.println("**response code is: " + statusCode);

			if (entity != null && statusCode == HttpStatus.SC_OK) {
				htmlString = EntityUtils.toString(entity, "utf-8");
				return htmlString;
			}
		}catch (Exception e) {
			System.out.println("Download Error: " + e);
		}
		finally {
			request.releaseConnection();
			if(response != null){
				//主动关闭连接
				try{
					EntityUtils.consume(entity);
				}catch (IOException e){
					System.out.println("Entity Consume Error:" + e);
				}
			}
		}
		return null;
	}


}
