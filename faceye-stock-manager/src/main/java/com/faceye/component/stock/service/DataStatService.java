package com.faceye.component.stock.service;

import java.util.List;
import java.util.Map;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.Stock;
import com.faceye.component.stock.service.wrapper.StatRecord;
import com.faceye.feature.service.BaseService;

/**
 * DataStat 服务接口<br>
 * 对财报数据进行分析
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
public interface DataStatService extends BaseService<DataStat, Long> {


	/**
	 * 数据分析入口
	 * 
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月22日 下午5:26:09
	 */
	public void stat();
	/**
	 * 对一只股票进行分析
	 * @param stock
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月24日 上午9:37:13
	 */
	public void stat(Stock stock);
	
	/**
	 * 取得特定条件下股票分析结果 
	 * @param params
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年3月22日 下午5:53:45
	 */
	public List<StatRecord> getStatResults(Map params);
}/** @generate-service-source@ **/
