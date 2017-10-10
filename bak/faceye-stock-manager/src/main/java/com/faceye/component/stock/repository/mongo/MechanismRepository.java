package com.faceye.component.stock.repository.mongo;

import com.faceye.component.stock.entity.Mechanism;
import com.faceye.component.stock.repository.mongo.gen.MechanismGenRepository;
/**
 * Mechanism 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface MechanismRepository extends MechanismGenRepository{
	
	public Mechanism getMechanismByName(String name);
}/**@generate-repository-source@**/
