package com.faceye.component.stock.service;

import java.util.List;

import com.faceye.component.stock.entity.StarDataStat;
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
	public List<Long>getStarStockIds();
}/** @generate-service-source@ **/
