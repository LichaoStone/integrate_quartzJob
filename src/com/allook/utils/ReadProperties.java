package com.allook.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件信息
 * 
 * @author tangming
 * @date 2013-10-31
 */
public class ReadProperties {

	private static String param;

	static {
		Properties prop = new Properties();
		InputStream in = ReadProperties.class.getClassLoader().getResourceAsStream("project.properties");
		try {
			prop.load(in);
//			param = prop.getProperty("activity_url").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 私有构造方法，不需要创建对象
	 */
	private ReadProperties() {
	}

	public static String getParam() {
		return param;
	}

	public static String getProperties(String pram) {
		Properties prop = new Properties();
		InputStream in = ReadProperties.class.getClassLoader().getResourceAsStream("project.properties");
		try {
			prop.load(in);
			param = prop.getProperty(pram).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return param;
	}
}
