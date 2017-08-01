package webspider.downloadpage;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class DownloadPage {

	private String currentUrl;
	private String htmlEntity;
	private int statusCode;
	private RequestConfig requestConfigTimeout;
	private String userAgent;
	
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public DownloadPage(String currentUrl, String userAgent, RequestConfig requestConfigTimeout) {
		this.currentUrl = currentUrl;
		this.userAgent = userAgent;
		this.requestConfigTimeout = requestConfigTimeout;
	}

	public DownloadPage() {

	}
	
	//configure user-Agent
	
	// download HTML page by GET method
	public String DownloadByGetMethod() {

		HttpGet request = new HttpGet(currentUrl);
		request.addHeader("User-Agent", userAgent);
		request.setConfig(requestConfigTimeout);

		try {
			CloseableHttpResponse response = httpClient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();

			System.out.println("\n---------------------------------------------------------------------");
			System.out.println("**Download Page: " + currentUrl);
			System.out.println("**response code is: " + statusCode);

			if (entity != null && statusCode == HttpStatus.SC_OK) {
				
				htmlEntity = EntityUtils.toString(entity, "utf-8");
				return htmlEntity;
				
			} 

		} catch (SocketTimeoutException e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("URL:"+this.currentUrl+"-- SocketTimeout");
		} catch (ConnectTimeoutException e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("URL:"+this.currentUrl+"-- ConnectTimeout" );
		} catch (HttpHostConnectException e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("URL:"+this.currentUrl+"-- HttpHostConnectException" );
		}
		catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("\n---------------Download Page Fail, The ContentString Is Null-------------\n");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			request.releaseConnection();
		}
		
		return null;

	}

}
