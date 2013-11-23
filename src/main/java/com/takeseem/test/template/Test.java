/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.test.template;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static Logger logger = LoggerFactory.getLogger(Test.class);
	private static Map<String, Object> model = new HashMap<>();
	private static List<AbstractEngine> engines = new ArrayList<>();
	/** 累加测试结果Map&lt;测试项目, Map&lt;引擎, [size, 耗时]&gt;&gt; */
	private static Map<String, Map<String, long[]>> all = new TreeMap<>();
	
	private static long[] getData(String engineKey, String testName) {
		Map<String, long[]> tmp = all.get(testName);
		if (tmp == null) all.put(testName, tmp = new TreeMap<>());
		
		long[] ret = tmp.get(engineKey);
		if (ret == null) tmp.put(engineKey, ret = new long[2]);
		return ret;
	}
	
	
	private static void exec() throws Exception {
		int max = 10000;
		for (AbstractEngine engine : engines) {
			long det, start = System.currentTimeMillis();
			for (int i = 0; i < max; i++) {
				engine.procTable(false);
			}
			det = System.currentTimeMillis() - start;
			long[] testData = getData(engine.getKey(), "table");
			testData[0] += max;
			testData[1] += det;
			logger.info("{} TABLE size: {}\t{}s\t1000size: {}ms",
					engine.getKey(), max, det/1000f, det*1000f/max);
			
		}
	}
	
	private static void pause(long timeout) throws InterruptedException {
		final Object lock = new Object();
		synchronized (lock) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.in.read(new byte[1024]);
					} catch (IOException e) {
					}
					synchronized (lock) {
						lock.notifyAll();
					}
				}
			});
			thread.setDaemon(true);
			thread.start();
			lock.wait(timeout);
		}
	}
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
		engines.add(new VelocityEngine(model));
		for (AbstractEngine engine : engines) {
			engine.procTable(true);
		}
		logger.info("系统预热结束");
		System.out.println("回车后开始测试：");
		pause(1000 * 5);
		
		for (int i = 0; i < 10; i++) {
			exec();
		}
		
		System.out.println("最终测试结果：");
		for (Entry<String, Map<String, long[]>> entry : all.entrySet()) {
			for (Entry<String, long[]> entry2 : entry.getValue().entrySet()) {
				long[] data = entry2.getValue();
				System.out.println(entry.getKey() + " " + entry2.getKey() + "\t"
						+ data[0]/10000f + "万\t" + data[1]/1000f + "s\t" + data[1]*10f/data[0] + "s/万");
			}
		}
		
		System.out.println();
		System.out.println("回车退出");
		pause(1000 * 5);
	}
}
