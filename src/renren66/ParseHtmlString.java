package renren66;
import java.util.regex.*;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class ParseHtmlString {
	private String htmlString;
	private String actor, type, country, language, showTime, length, otherName, imdb;
	private String score;

	public ParseHtmlString(String htmlString) {
		this.htmlString = htmlString;
	}

	public ParseHtmlString() {

	}

	public String getActor() {
		String regex = "( .+)类型";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		if (matcher.find()) {
			actor = matcher.group(1);
		}

		return actor;
	}

	public String getType() {
		String regex = "(类型)( .+)(制片国家)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		
		if(matcher.find()) {
			type = matcher.group(2);
		}

		return type;

	}

	public String getCountry() {
		String regex = "(制片国家)( .+)(语言)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		
		if(matcher.find()) {
			country = matcher.group(2);
		}

		return country;
	}

	public String getLanguage() {
		String regex = "(语言)( .+)(上映时间)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		
		if(matcher.find()) {
			language = matcher.group(2);
		}

		return language;
	}

	public String getShowTime() {
		String regex = "(上映时间)( .+)(片长)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		
		if(matcher.find()) {
			showTime = matcher.group(2);
		}

		return showTime;
	}

	public String getLength() {
		String regex = "(片长)( .+)(又名)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		
		if(matcher.find()) {
			length = matcher.group(2);
		}
		return length;
	}

	public String getOtherName() {
		String regex = "(又名)( .+)(评分)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		
		if(matcher.find()) {
			otherName = matcher.group(2);
		}

		return otherName;
	}

	public String getImdb() {
		String regex = "(imdb)( .+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		
		if(matcher.find()) {
			imdb = matcher.group(2);
		}

		return imdb;
	}

	public String getScore() {
		String regex = "(评分)( .+)(imdb)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(htmlString);
		
		if(matcher.find()) {
			score = matcher.group(2);
		}

		return score;
	}

}
