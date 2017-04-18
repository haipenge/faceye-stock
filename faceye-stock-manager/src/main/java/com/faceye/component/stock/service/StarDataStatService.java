package com.faceye.component.stock.service;

import java.util.List;
import java.util.Map;

import com.faceye.component.stock.entity.StarDataStat;
import com.faceye.component.stock.service.wrapper.WrapStarDataStat;
import com.faceye.feature.service.BaseService;

/**
 * StarDataStat 服务接品<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
public interface StarDataStatService extends BaseService<StarDataStat, Long> {
	/**
	 * 获取最近星标标记股票ID
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月11日 下午6:32:54
	 */
	public List<Long>getStarStockIds(Map params);
	
	/**
	 * 包装星标数据，并加入分析结果
	 * @param params
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月12日 上午8:16:42
	 */
	public WrapStarDataStat wrapStarDataStat(Map params,int page,int size);
	
	
	/**
	 * 清空一只股票的星标分析结果
	 * @param stockId
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年4月18日 上午9:24:31
	 */
	public void removeStockStarStatResults(Long stockId);
}/** @generate-service-source@ **/
