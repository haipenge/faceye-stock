package com.faceye.component.stock.repository.mongo;

import com.faceye.component.stock.entity.ReportCategory;
import com.faceye.component.stock.repository.mongo.gen.ReportCategoryGenRepository;
/**
 * ReportCategory 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ReportCategoryRepository extends ReportCategoryGenRepository{
	
	public ReportCategory getReportCategoryByCode(String code);
	
	
}/**@generate-repository-source@**/
