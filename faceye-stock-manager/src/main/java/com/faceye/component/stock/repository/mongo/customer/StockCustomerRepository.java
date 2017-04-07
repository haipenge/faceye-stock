package com.faceye.component.stock.repository.mongo.customer;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.faceye.component.stock.entity.Stock;
/**
 * ReportCategory 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface StockCustomerRepository {
	
	/**
	 * 查询股票数据
	 * @param searchParams
	 * @param page
	 * @param size
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月7日 下午5:00:53
	 */
	public Page<Stock> getPage(Map searchParams,int page,int size);
}/**@generate-repository-source@**/
