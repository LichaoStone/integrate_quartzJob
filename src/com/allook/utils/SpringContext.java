package com.allook.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * Spring配置文件读取类
 * 
 * @author tangming
 * @date 2013-10-31
 */
public class SpringContext {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	/** 构建自身对象 */
	private static SpringContext springContext = null;

	/** spring上下文 */
	public static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring/*.xml");

	private static WebApplicationContext webCtx = null;

	/**
	 * 保证对象唯一性
	 * 
	 * @return
	 */
	public static SpringContext getInstance() {
		if (springContext == null) {
			springContext = new SpringContext();
		}
		return springContext;
	}

	/**
	 * 获取spring内容对象
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		if (webCtx != null)
			return webCtx.getBean(beanName);
		return context.getBean(beanName);
	}
}
