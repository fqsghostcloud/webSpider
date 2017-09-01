/*
package renren66;

import org.apache.http.client.config.RequestConfig;

import webspider.downloadpage.DownloadPage;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Config config = new Config();
		RequestConfig requestConfigTimeout = RequestConfig.custom().setCircularRedirectsAllowed(config.redirecAllowed)
				.setSocketTimeout(config.socketTimeout).setConnectTimeout(config.connectTimeout).build();
		String pageContent = null;

		String[] mid = new String[] { "93181", "93182", "93183", "93185", "93186" };
		for (int i = 0; i < mid.length; i++) {
			String ss = "http://i.sporttery.cn/api/fb_match_info/get_odds/?f_callback=get_sporttery_odds&mid=" + mid[i]
					+ "&time=" + System.currentTimeMillis();
			
			DownloadPage downloadPage = new DownloadPage(ss, config.headers, requestConfigTimeout);

			pageContent = downloadPage.DownloadByGetMethod();
			System.out.println("******************** Current Num is:" + i + "******************");
			System.out.println(pageContent);
			System.out.println("\n");
		}

		

		

	}

}
*/
