package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.Stock;

/**
 * 数据爬取服务
 * 
 * @author haipenge
 *
 */
public interface CrawlFinancialDataService {
	/**
	 * 数据爬取服务
	 * 
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月21日 下午3:11:51
	 */
	public void crawl();

	/**
	 * 爬取一只股票的年、季报数据
	 * 
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年12月21日 下午5:33:24
	 */
	public void crawlStock(Stock stock);
	
	/**
	 * 爬取股票财报数据
	 * @param stock
	 * @param isCrawled
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月4日 上午9:03:51
	 */
	public void crawlStock(Stock stock,boolean isCrawled); 
	
	
	/**
	 * 爬取对企业的估值
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年7月16日 下午6:45:08
	 */
	public void crawlForecast(Stock stock);
}
