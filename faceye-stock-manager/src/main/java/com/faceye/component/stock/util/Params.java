package com.faceye.component.stock.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参数构造器
 * @author haipenge
 *
 */
public class Params {
	private Logger logger = LoggerFactory.getLogger(Params.class);
	private static Map MAP = null;
	private static Params PARAMS = null;

	public Params() {
		if (MAP == null) {
			MAP = new HashMap();
		}
	}

	public  Params add(Object key, Object value) {
		if (PARAMS == null) {
			PARAMS = new Params();
		}
		if (!isContains(key) && value != null) {
			MAP.put(key, value);
		}
		return PARAMS;
	}

	private  boolean isContains(Object key) {
		return MAP.containsKey(key);
	}

	public  Map map() {
		return MAP;
	}

}
