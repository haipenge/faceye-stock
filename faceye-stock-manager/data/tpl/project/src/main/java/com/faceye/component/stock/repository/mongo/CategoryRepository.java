package com.faceye.component.stock.repository.mongo;

import com.faceye.component.stock.entity.Category;
import com.faceye.component.stock.repository.mongo.gen.CategoryGenRepository;
/**
 * Category 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface CategoryRepository extends CategoryGenRepository{
	
	public Category getCategoryByName(String name);
}/**@generate-repository-source@**/
