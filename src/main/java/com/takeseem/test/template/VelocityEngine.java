/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.test.template;

import java.io.IOException;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.takeseem.test.template.util.UtilIO;

public class VelocityEngine extends AbstractEngine {
	static {
		Velocity.setProperty("input.encoding", "UTF-8");
		Velocity.setProperty("output.encoding", "UTF-8");
		Velocity.setProperty("file.resource.loader.cache", "true");
		try {
			Velocity.setProperty("file.resource.loader.path", UtilIO.getResource("vm").getPath());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		Velocity.init();
	}
	
	
	public VelocityEngine(Map<String, Object> model) throws Exception {
		super(model);
	}
	
	@Override
	public String getKey() {
		return "VM";
	}

	@Override
	public void procTable(boolean info) throws Exception {
		reset();
		//context重新构造，否则内存泄漏
		Velocity.getTemplate("table.html").merge(new VelocityContext(model), writer);
		writer.flush();
		if (info) logger.info("{} TABLE:\n{}\n", getKey(), getOutText());
	}
}
