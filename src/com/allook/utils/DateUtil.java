package com.allook.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期帮助方法类
 * <p>
 * 文件名称: DateUtil.java
 * </p>
 * <p>
 * 文件描述: 本类描述
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2011-2015
 * </p>
 * <p>
 * 公 司: 山东广电新媒体中心技术部
 * </p>
 * <p>
 * 内容摘要:
 * </p>
 * <p>
 * 其他说明:
 * </p>
 * <p>
 * 完成日期：2012-7-24
 * </p>
 * <p>
 * 修改记录0：无
 * </p>
 * 
 * @version 1.0
 * @author 秦鹏云
 */
public class DateUtil {

	public static String getDate(String currentDate, int amount) throws ParseException {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateformat.parse(currentDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, amount);
		Date now = calendar.getTime();

		return dateformat.format(now);
	}

	public static String getDateWithoutSecond(String currentDate, int amount) throws ParseException {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateformat.parse(currentDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, amount);
		Date now = calendar.getTime();

		return dateformat.format(now);
	}

	public static String getNowDateWithSeparator() {

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");

		return getSpecifiedDayBefore(tempDate.format(new Date()));
	}

	public static String getNowDateMonth() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM");
		return getSpecifiedDayBefore(tempDate.format(new Date()));
	}

	public static String getStartDate(String startDate) {
		return startDate.substring(0, 10) + " 00:00:00";
	}

	public static String getEndDate(String endDate) {
		return endDate.substring(0, 10) + " 23:59:59";
	}

	public static Date getFormDate(String date, String formatStr) {
		if (!"".equals(date)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 得到輸入的日期是所在的年度的第几周
	 * 
	 * @param date
	 *            type:Date 輸入日期
	 * @return 輸入的日期是所在的年度的周別
	 */
	public static String getWeekOfDate(String dt) {
		if (!"".equals(dt)) {
			Date date = getFormDate(dt, "yyyy-MM-dd");
			String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (w < 0)
				w = 0;
			return weekDays[w];
		}
		return null;
	}

	/**
	 * 实现指定日期添加天数
	 * 
	 * @param d
	 *            日期
	 * @param day
	 *            天数
	 * @return
	 * @throws ParseException
	 */
	public static Date addDate(Date d, long day) throws ParseException {

		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);
	}

	/**
	 * 根据指定日期 获得前一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}
	
	/**
	 * 根据指定日期 获得后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay,int days) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + days);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}
	
	/**
	 * 根据指定日期 获得前一个月
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedMonthBefore(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		c.set(Calendar.MONTH, month - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}
}
