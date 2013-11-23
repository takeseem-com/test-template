/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.test.template;

import java.util.Map;

import com.takeseem.test.template.util.UtilIO;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * 
 */
public class FreemarkerEngine extends AbstractEngine {
	private Configuration cfg = new Configuration();
	
	public FreemarkerEngine(Map<String, Object> model) throws Exception {
		super(model);
		
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		StringTemplateLoader templateLoader = new StringTemplateLoader();
		templateLoader.putTemplate("table.html", UtilIO.getResourceAsText("ftl/table.html"));
		cfg.setTemplateLoader(templateLoader);
	}

	@Override
	public String getKey() {
		return "FM";
	}
	
	@Override
	public void procTable(boolean info) throws Exception {
		reset();
		cfg.getTemplate("table.html").process(model, writer);
		if (info) logger.info("TABLE:\n{}\n", getOutText());
	}

}
