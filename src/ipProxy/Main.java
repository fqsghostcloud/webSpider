package ipProxy;

import ipProxy.sixsixipspider.IpSpider;
import ipProxy.xicidaili.XiCiDaiLiSpiser;
import webspider.iptest.IpTest;

public class Main {
	public static double SumOfIp = 0;
	public static double usebleSumOfIp = 0;
	public static double usebleRate = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		IpTest ipTest = new IpTest("46.101.78.9","8080");


		IpSpider ipSpider = new IpSpider(200 );
		ipSpider.main();
/*		XiCiDaiLiSpiser xiCiDaiLiSpiser = new XiCiDaiLiSpiser("http://www.xicidaili.com/nn");
		xiCiDaiLiSpiser.main();*/
		
		// print result info*/
		IpTest.printresult();
	}


}
