package com.faceye.component.@component.name@.repository.jpa;
/**
 * 模块:@component.name@<br>
 * 实体:@entity.name@<br>
 * ORM业务接口<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.faceye.component.@component.name@.entity.@entity.name@;
import com.faceye.feature.repository.BaseRepository;
/**
 * @entity.name@ 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
public interface @entity.name@Repository extends BaseRepository<@entity.name@,Long> {
	
	
}/**@generate-repository-source@**/
