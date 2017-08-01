/*
package ipProxy.sixsixipspider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.config.RequestConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ipProxy.Config;
import ipProxy.Main;
import ipProxy.PrintResult;
import webspider.downloadpage.DownloadPage;

public class IpSpider {
	private int ipSum;
	private String Url = "http://www.66ip.cn/nmtq.php?isp=0&anonymoustype=4&start=&ports=&export=&ipaddress=&area=0&proxytype=2&api=66ip&getnum=";

	public IpSpider(int ipSum) {
		this.ipSum = ipSum;
	}

	public void main() {
		Config config = new Config();
		RequestConfig requestConfigTimeout = RequestConfig.custom().setCircularRedirectsAllowed(config.redirecAllowed)
				.setSocketTimeout(config.socketTimeout).setConnectTimeout(config.connectTimeout).build();
		String requestUrl = Url + ipSum;

		DownloadPage downloadPage = new DownloadPage(requestUrl, config.userAgent, requestConfigTimeout);

		String pageString = downloadPage.DownloadByGetMethod();

		if (pageString != null) {
			this.parsePageAndTestIp(pageString, ipSum);
		} else {
			System.out.println("\n***********************Page String from 66ip.com is NULL*****************");
		}
	}

	public void parsePageAndTestIp(String pageString, int ipSum) {
		Document jsoup = Jsoup.parse(pageString);
		Elements ipItem = jsoup.select("body");
		String ipString = ipItem.text();
		String[] ipArray = new String[ipSum];

		// ipString to ipArray
		ipArray = ipString.split(" ", ipSum);

		// get ip
		String regex = "(.{8,15})(:)([0-9]{2,4})";
		Pattern pattern = Pattern.compile(regex);

		for (String s : ipArray) {
			Matcher matcher = pattern.matcher(s);
			if (matcher.find()) {
				String ip = matcher.group(1);
				String port = matcher.group(3);

				// test ip

				IpTest ipTest = new IpTest(ip, port);
				boolean status = ipTest.ipDownloadTest();
				Main.SumOfIp++;

				if (status) {
					PrintResult printResult = new PrintResult(ip, port);
					printResult.printInfo();
				}

			} else {
				System.out.println("**Can not parse Ip: "+ipString);
			}
			matcher = null;
		}

	}

}
*/
