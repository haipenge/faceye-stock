package com.faceye.component.stock.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faceye.feature.service.Reporter;
import com.faceye.feature.service.impl.PrintReporter;
import com.faceye.feature.util.FileUtil;
import com.faceye.feature.util.http.Http;

/**
 * 股票数据抓取
 * 
 * @author haipeng
 * 
 */
public class StockFetcher {

	public String URL_PATTERN = "http://vip.stock.finance.sina.com.cn/q/go.php/vIR_RatingNewest/index.phtml?num=60&p=31";
	private String fileName = "E:/Work/FeatureWorkSpace/faceye/faceye-blog/un-store-stock-url.txt";
	// 行情：参数为股票编码
	public String URL_HANGQING = "http://hq.sinajs.cn/list=";
	private Logger logger = LoggerFactory.getLogger(getClass());

	Reporter report = new PrintReporter();
	/**
	 * var hq_str_sh601006="大秦铁路, 27.55, 27.25, 26.91, 27.55, 26.20, 26.91,
	 * 26.92, 22114263, 589824680, 4695, 26.91, 57590, 26.90, 14700, 26.89,
	 * 14300, 26.88, 15100, 26.87, 3100, 26.92, 8900, 26.93, 14230, 26.94,
	 * 25150, 26.95, 15220, 26.96, 2008-01-11, 15:05:32";
	 * 这个字符串由许多数据拼接在一起，不同含义的数据用逗号隔开了，按照程序员的思路，顺序号从0开始。 0：”大秦铁路”，股票名字；
	 * 1：”27.55″，今日开盘价； 
	 * 2：”27.25″，昨日收盘价； 
	 * 3：”26.91″，当前价格； 
	 * 4：”27.55″，今日最高价；
	 * 5：”26.20″，今日最低价； 
	 * 6：”26.91″，竞买价，即“买一”报价； 
	 * 7：”26.92″，竞卖价，即“卖一”报价；
	 * 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
	 * 9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
	 * 10：”4695″，“买一”申请4695股，即47手； 
	 * 11：”26.91″，“买一”报价； 
	 * 12：”57590″，“买二”
	 * 13：”26.90″，“买二” 
	 * 14：”14700″，“买三” 
	 * 15：”26.89″，“买三” 
	 * 16：”14300″，“买四”
	 * 17：”26.88″，“买四” 
	 * 18：”15100″，“买五” 
	 * 19：”26.87″，“买五”
	 * 20：”3100″，“卖一”申报3100股，即31手； 
	 * 21：”26.92″，“卖一”报价 
	 * (22, 23), (24, 25),
	 * (26,27), (28, 29)分别为“卖二”至“卖四的情况” 
	 * 30：”2008-01-11″，日期； 
	 * 31：”15:05:32″，时间
	 */
	private String url = "";

	public String getContent(String url) throws Exception {
		String content = "";
		this.url = url;
		content = Http.getInstance().get(url, "GB2312");
		// this.distill(content);
		return content;
	}

	public List<Map<String, String>> distillStockNameAndCode(String content) throws Exception {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		List<Map<String, String>> codeAndNames = new ArrayList();
		String regexp = "<span id=\"name_([0-9a-z]{8})\">([a-z0-9A-Z\\*\u4E00-\u9FFF]+)</span>";
		regexp = "<a target=\"_blank\" href=\"http://quote.eastmoney.com/([0-9a-z]{8}).html\">([\\(\\)a-z0-9A-Z\\*\u4E00-\u9FFF]+)</a>";
		Pattern pattern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String code = matcher.group(1);
			String name = matcher.group(2);
			Map map = new HashMap();
			map.put(code, name);
			codeAndNames.add(map);
		}
		Reporter reporter = new PrintReporter();
		reporter.reporter(codeAndNames);
		return codeAndNames;
	}

	public List<Map<String, String>> distillStockNameAndCodeFromQQ(String content) throws Exception {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		List<String> codes = this.distil(content, "<a href=[^>]*>([0-9]{6})</a>");
		List<String> names = this.distil(content, "<span id=\"name_[0-9a-z]{8}\">([a-z0-9A-Z\\*\u4E00-\u9FFF]+)</span>");
		if ((codes.size() != names.size()) || (codes.isEmpty() || names.isEmpty())) {
			FileUtil.write(fileName, url);
			return res;
		}
		for (int i = 0; i < codes.size(); i++) {
			Map<String, String> item = new HashMap<String, String>();
			item.put(codes.get(i), names.get(i));
			res.add(item);
		}
		Reporter reporter = new PrintReporter();
		reporter.reporter(res);
		return res;
	}

	private List<String> distil(String content, String regexp) throws Exception {
		List<String> res = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		String newContent = content;
		Matcher matcher = pattern.matcher(newContent);
		while (matcher.find()) {
			String code = matcher.group(1);
			int end = matcher.end();
			newContent = newContent.substring(end);
			matcher = pattern.matcher(newContent);
			res.add(code);
		}
		return res;
	}

	/**
	 * 根据股票编码，取得新浪财经股票数据
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	// public String fetchSinaStockData(String code) throws Exception {
	// String data = "";
	// String url = "http://hq.sinajs.cn/list=" + code;
	// data = rdc.get(url, "GBK", null, null);
	// return data;
	// }

	/**
	 * 解析Yahoo的股票数据
	 * @todo
	 * @param content
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年2月10日
	 */
	private List<Map<String, String>> parseSinaData(String content) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		String regexp = "";
		// regexp="<tr\\s+?.*?>\\s+?<td><div.*?>\\s+?<a[\\s]target=\'_blank\'[\\s]href=\'.*?\'>[\\s+?](.+?)</a>\\s+?</div></td>\\s+?<td><div\\s+?.*?>(.+?)</div></td>\\s+?<td><div\\s+?.*?>(.+?)</div></td>\\s+?<td><div\\s+?.*?>(.+?)</div></td>\\s+?<td\\s+?.*?><div\\s+?.*?>(.+?)</div></td>\\s+?<td\\s+?.*?><div\\s+?.*?>(.+?)</div></td>\\s+?<td\\s+?.*?><div\\s+?.*?>(.+?)</div></td>\\s+?</tr>";
		// <tr\s+?.*?>\s+?<td><div.*?>\s+?<a[\s]target=\'_blank\'[\s]href=\'.*?\'>[\s+?](.+?)</a>\s+?</div></td>\s+?<td><div\s+?.*?>(.+?)</div></td>\s+?<td><div\s+?.*?>(.+?)</div></td>\s+?<td><div\s+?.*?>(.+?)</div></td>\s+?<td\s+?.*?><div\s+?.*?>(.+?)</div></td>\s+?<td\s+?.*?><div\s+?.*?>(.+?)</div></td>\s+?<td\s+?.*?><div\s+?.*?>(.+?)</div></td>\s+?</tr>
		regexp = "<tr\\s+?.*?>\\s+?<td><div.*?>\\s+?<a[\\s]target=\'_blank\'[\\s]href=\'.*?\'>[\\s+?](.+?)<\\/a>\\s+?<\\/div><\\/td>\\s+?<td><div\\s+?.*?>(.+?)<\\/div><\\/td>\\s+?<td><div\\s+?.*?>(.+?)<\\/div><\\/td>\\s+?<td><div\\s+?.*?>(.+?)<\\/div><\\/td>\\s+?<td\\s+?.*?><div\\s+?.*?>(.+?)<\\/div><\\/td>\\s+?<td\\s+?.*?><div\\s+?.*?>(.+?)<\\/div><\\/td>\\s+?<td\\s+?.*?><div\\s+?.*?>(.+?)<\\/div><\\/td>\\s+?<\\/tr>";
		if (StringUtils.isNotEmpty(content)) {
			Pattern pattern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(content);
			while (matcher.find()) {
				Map<String, String> map = new HashMap<String, String>();
				String date = matcher.group(1);
				String open = matcher.group(2);
				String high = matcher.group(3);
				String close = matcher.group(4);
				String low = matcher.group(5);
				String volume = matcher.group(6);
				String money = matcher.group(7);
				// 日期
				map.put("date", StringUtils.trim(date));
				// 开盘价
				map.put("open", StringUtils.trim(open));
				// 最高价
				map.put("high", StringUtils.trim(high));
				// 最低价
				map.put("low", StringUtils.trim(low));
				// 收盘价
				map.put("close", StringUtils.trim(close));
				// 成交股票数（股)
				map.put("volume", StringUtils.trim(volume));
				// 成交金额(元)
				map.put("money", StringUtils.trim(money));
				logger.debug(">>Date:" + map.get("date") + ",open:" + open + ",high:" + high + ",low:" + low + ",close:" + close
						+ ",volume:" + volume + ",money:" + money);
				data.add(map);
			}
		}
		return data;
	}

	/**
	 * 
	 * @todo
	 * @param stockCode
	 * @param year 年，
	 * @param period 季度[1,2,3,4]
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月28日
	 */
	public List<Map<String, String>> getStockDataList(String stockCode, String year, String jidu) {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		String url = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/" + stockCode + ".phtml?year=" + year
				+ "&jidu=" + jidu;
		String content = Http.getInstance().get(url, "gb2312");
		try {
			Thread.sleep(1500L);
		} catch (InterruptedException e) {
			logger.error(">>FaceYe throws Exception: --->",e);
		}
		res = this.parseSinaData(content);
		return res;
	}

	public List<Map<String, String>> getStockDataList(String stockCode, String year, String[] jidus) {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		if (null != jidus) {
			for (String jidu : jidus) {
				res.addAll(this.getStockDataList(stockCode, year, jidu));
			}
		}
		return res;
	}

	public List<Map<String, String>> getStockDailyData(String stockCode, String startDate, String endDate) {
		List<Map<String,String>> res=new ArrayList<Map<String,String>>();
		res=this.getStockDataList(stockCode, "2016", new String[]{"1","2","3","4"});
		if(res==null){
			res=new ArrayList<Map<String,String>>();
		}
		res.addAll(this.getStockDataList(stockCode, "2015", "1"));
		return res;
	}

	/**
	 * 从新浪获取实时数据
	 * @todo
	 * @param code
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月23日
	 *##################################################################################
	 * var hq_str_sh601006="大秦铁路, 27.55, 27.25, 26.91, 27.55, 26.20, 26.91,
	 * 26.92, 22114263, 589824680, 4695, 26.91, 57590, 26.90, 14700, 26.89,
	 * 14300, 26.88, 15100, 26.87, 3100, 26.92, 8900, 26.93, 14230, 26.94,
	 * 25150, 26.95, 15220, 26.96, 2008-01-11, 15:05:32";
	 * 这个字符串由许多数据拼接在一起，不同含义的数据用逗号隔开了，按照程序员的思路，顺序号从0开始。 
	 * 0：”大秦铁路”，股票名字；
	 * 1：”27.55″，今日开盘价； 
	 * 2：”27.25″，昨日收盘价； 
	 * 3：”26.91″，当前价格； 
	 * 4：”27.55″，今日最高价；
	 * 5：”26.20″，今日最低价； 
	 * 6：”26.91″，竞买价，即“买一”报价； 
	 * 7：”26.92″，竞卖价，即“卖一”报价；
	 * 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
	 * 9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
	 * 10：”4695″，“买一”申请4695股，即47手； 
	 * 11：”26.91″，“买一”报价； 
	 * 12：”57590″，“买二”
	 * 13：”26.90″，“买二” 
	 * 14：”14700″，“买三” 
	 * 15：”26.89″，“买三” 
	 * 16：”14300″，“买四”
	 * 17：”26.88″，“买四” 
	 * 18：”15100″，“买五” 
	 * 19：”26.87″，“买五”
	 * 20：”3100″，“卖一”申报3100股，即31手； 
	 * 21：”26.92″，“卖一”报价 
	 * (22, 23), (24, 25),
	 * (26,27), (28, 29)分别为“卖二”至“卖四的情况” 
	 * 30：”2008-01-11″，日期； 
	 * 31：”15:05:32″，时间
	 */
	public Map getLiveDataFromSina(String code) {
		Map res = new HashMap();
		String url = "http://hq.sinajs.cn/list=" + code;
		String content = Http.getInstance().get(url, "GB2312");
		if (StringUtils.isNotEmpty(content)) {
			String[] splitContent = content.split("=");
			if (splitContent != null && splitContent.length == 2) {
				String data = splitContent[1];
				data = data.replaceAll("\"", "");
				String[] splitData = data.split(",");
				res.put("name", splitData[0]);
				res.put("open", splitData[1]);
				res.put("close", splitData[3]);
				res.put("high", splitData[4]);
				res.put("low", splitData[5]);
				res.put("volume", splitData[8]);
				res.put("money", splitData[9]);
				res.put("date", splitData[30]);
				res.put("time", splitData[31]);
				res.put("yesterdayPrice", splitData[2]);
			} else {
				logger.debug(">>FaceYe 数据可能存在异常，股票编码:" + code + "数据为:" + content);
			}
		}
		return res;
	}

	public static void main(String args[]) throws Exception {
		StockFetcher fetcher = new StockFetcher();
		// System.out.println(fetcher.fetchSinaStockData("sh603766"));

		List data = fetcher.getStockDailyData("sz500002", "", "");
		Reporter report = new PrintReporter();
		report.reporter(data);

		// String content = fetcher
		// .getContent("http://vip.stock.finance.sina.com.cn/q/go.php/vIR_RatingNewest/index.phtml?num=60&p=2");
		// List<Map<String, String>> codesAndName = fetcher.distillStockNameAndCode(content);
		// System.out.println(content);
	}
}
