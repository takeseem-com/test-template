/**
 * Copyright (c) 2013-2033 by takeseem.com
 * All rights reserved.
 * @author <a href="mailto:takeseem@gmail.com">takeseem.com</a>
 */
package com.takeseem.test.template.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 */
public class UtilIO {
	
	public static String getResourceAsText(String name) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream(8192);
		try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(name)) {
			byte[] buf = new byte[8192];
			for (int len; (len = in.read(buf)) != -1;) {
				bout.write(buf, 0, len);
			}
		}
		return bout.toString("UTF-8");
	}
}
