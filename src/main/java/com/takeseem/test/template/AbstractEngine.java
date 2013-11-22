/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.test.template;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public abstract class AbstractEngine {
	final Logger logger = LoggerFactory.getLogger(getClass());
	/** 共享模型忽略模型初始化时间对性能测试的影响 */
	final Map<String, Object> model;
	/** 1MB的缓冲防止数组copy对性能影响 */
	final ByteArrayOutputStream out = new ByteArrayOutputStream(1024 * 1024);
	final BufferedWriter writer;
	
	public AbstractEngine(Map<String, Object> model) {
		this.model = model;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"), 8192);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
	
	protected void reset() {
		out.reset();
	}
	public String getOutText() {
		try {
			return out.toString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	public abstract String getKey();
	public abstract void procTable(boolean info) throws Exception;
}
