package com.faceye.component.stock.service;

import com.faceye.component.stock.entity.Mechanism;
import com.faceye.feature.service.BaseService;
/**
 * Mechanism 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface MechanismService extends BaseService<Mechanism,Long>{

	public Mechanism getMechanismByName(String name);
}/**@generate-service-source@**/
