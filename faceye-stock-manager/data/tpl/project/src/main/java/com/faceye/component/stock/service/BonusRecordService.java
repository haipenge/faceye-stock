package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.BonusRecord;
import com.faceye.feature.service.BaseService;
/**
 * BonusRecord 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface BonusRecordService extends BaseService<BonusRecord,Long>{

	/**
	 * 是否存在指定日期的分红记录
	 * @param stockId
	 * @param date
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2017年7月13日 下午10:35:50
	 */
	public boolean isExistBonusRecord(Long stockId,String date);
	
	
	public BonusRecord getBonusRecord(Long stockId,String date);
}/**@generate-service-source@**/
