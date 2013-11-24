/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.test.template;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.takeseem.test.template.util.UtilIO;

import jetbrick.template.JetConfig;
import jetbrick.template.JetEngine;

public class JetbrickEngine extends AbstractEngine {
	private JetEngine engine;
	
	public JetbrickEngine(Map<String, Object> model) throws IOException {
		super(model);
		Properties props = new Properties();
		props.setProperty(JetConfig.TEMPLATE_PATH, UtilIO.getResource("jt").getPath());
		props.setProperty(JetConfig.COMPILE_DEBUG, "false");
		engine = JetEngine.create(props);
	}

	@Override
	public String getKey() {
		return "JT";
	}

	@Override
	public void procTable(boolean info) throws Exception {
		reset();
		engine.getTemplate("table.html").render(model, writer);
		writer.flush();
		if (info) logger.info("{} TABLE:\n{}\n", getKey(), getOutText());
	}

}
