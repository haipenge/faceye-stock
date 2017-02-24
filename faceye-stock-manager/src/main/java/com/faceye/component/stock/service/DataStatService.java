package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.DataStat;
import com.faceye.component.stock.entity.Stock;
import com.faceye.feature.service.BaseService;

/**
 * DataStat 服务接品<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
public interface DataStatService extends BaseService<DataStat, Long> {

	/**
	 * 分析股票的总资产回报率，计算公式：2*净利润/(期初总资产+期末总资产)
	 * 
	 * @param stock
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年2月22日 下午3:54:01
	 */
	public void statReturnOnAssets(Stock stock);

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
}/** @generate-service-source@ **/
