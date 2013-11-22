/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.test.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static Logger logger = LoggerFactory.getLogger(Test.class);
	private static Map<String, Object> model = new HashMap<>();
	private static List<AbstractEngine> engines = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		List<Map<String, Object>> rows = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			Map<String, Object> row = new HashMap<>();
			row.put("id", i);
			for (int j = 0; j < 9; j++) {
				row.put("col" +j, "val-" + j);
			}
			rows.add(row);
		}
		model.put("rows", rows);
		
		engines.add(new FreemarkerEngine(model));
		for (AbstractEngine engine : engines) {
			engine.procTable(true);
		}
		logger.info("系统预热结束");
		
		int max = 10000;
		for (AbstractEngine engine : engines) {
			long det, start = System.currentTimeMillis();
			for (int i = 0; i < max; i++) {
				engine.procTable(false);
			}
			det = System.currentTimeMillis() - start;
			logger.info("{} TABLE size: {}, {}s, 1000size: {}ms", engine.getClass().getSimpleName(),
					max, det/1000f, det*1000f/max);
		}
		
	}
}
