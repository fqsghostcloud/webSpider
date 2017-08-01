package renren66;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import webspider.linksqueue.LinksQueue;

public class ParsePage {
	public static int count = 0;
	private String rootDomain;
	private String currentUrl;
	
	private LinksQueue pageQueue;
	private LinksQueue movieQueue;
	
	private Document pageDoc;
	private Document movieDoc;


	public ParsePage(String rootDomain, LinksQueue movieQueue, String currentUrl, LinksQueue pageQueue) {
		this.rootDomain = rootDomain;
		this.currentUrl = currentUrl;
		this.movieQueue = movieQueue;
		this.pageQueue = pageQueue;
	}

	// Parse HTML page
	public boolean getMoviesUrl(String moviesItemContents) {
		pageDoc = Jsoup.parse(moviesItemContents, rootDomain);
		Elements movieItemTag = pageDoc.getElementsByClass("movie-item");

		int links = movieItemTag.size();

		if (links > 0) {
			System.out.println("\n======================Get Movie Item From: "+ currentUrl);
			System.out.println("the num of movie-item is:" + links);
			System.out.println("----------------------------------------");
			Elements tagA = movieItemTag.select("div.movie-item > a");
			// add movie info page to queue
			for (Element tag : tagA) {
				movieQueue.addUnvisitedUrl(tag.attr("abs:href"));
			}
			return true;
			
		} else {
			System.out.println(
					"\n------------------From " + this.currentUrl + " get <movie-item> tag fail---------------\n");
		}
		return false;

	}

	// get info of movies
	public void getMoviesInfo(String movieInfoContents) {
		int trTagCounts = 2;
		movieDoc = Jsoup.parse(movieInfoContents, rootDomain);

		Element title = movieDoc.select("h1.movie-title").first();
		Elements imgUrl = movieDoc.select("img.img-thumbnail");
		Elements year = movieDoc.select("span.movie-year");
		Element movieInfo = movieDoc.getElementsByTag("tbody").first();
		Elements summary = movieDoc.select("p.summary");
		Elements panUrl = movieDoc.select("a[href*=/pan.baidu.com/]"); // String contains pan.baidu.com
		Elements panKey = movieDoc.select("strong[style=color:red;]");
		ParseHtmlString parseHtmlString = new ParseHtmlString(movieInfo.text());
		
		System.out.println("***********************Counts of Movies is: " + count + "***************\n");
		System.out.println("\n----------------------------Movies Info-----------------------------");
		// movie information
		System.out.println("Title is: " + title.ownText());
		System.out.println("imgUlr is: " + imgUrl.attr("src"));
		System.out.println("Year is: " + year.text());
		System.out.println("Actor is: " + parseHtmlString.getActor());
		System.out.println("Type is: " + parseHtmlString.getType());
		System.out.println("Countr is: " + parseHtmlString.getCountry());
		System.out.println("Language is: " + parseHtmlString.getLanguage());
		System.out.println("ShowTime is: " + parseHtmlString.getShowTime());
		System.out.println("Length is: " + parseHtmlString.getLength());
		System.out.println("OtherName is: " + parseHtmlString.getOtherName());
		System.out.println("Score is: " + parseHtmlString.getScore());
		System.out.println("Imdb is: " + parseHtmlString.getImdb());
		System.out.println("Summary is: " + summary.text());
		System.out.println("BaiDuPan is: " + panUrl.attr("href"));
		System.out.println("Pan Key is: " + panKey.text());

		System.out.println("\n------------------------------------------------------------------\n");

	}

	// Find other classified HTML Url
	public void findHtmlUrls(String Reg) {
		// Elements htmlUrls = document.select("a[href~=/vip_(\\w)+.html]");
		Elements htmlUrls = pageDoc.select(Reg);

		for (Element url : htmlUrls) {
			pageQueue.addUnvisitedUrl(url.attr("abs:href"));
		}

	}

}
