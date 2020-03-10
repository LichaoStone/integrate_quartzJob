package com.allook.enter;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.allook.timer.DataCleanTask;
import com.allook.timer.ScreenRefreshTask;
import com.allook.utils.SpringContext;

/**
 * 定时任务入口
 * 
 * @author tangming
 * @date 2013-10-31
 */
public class QuartzJobEnter {

	private static Logger log = Logger.getLogger(QuartzJobEnter.class);

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// 启动spring
		ApplicationContext ac = SpringContext.context;

		log.info("定时任务启动成功：" + Calendar.getInstance().getTime());
	}
	
	public void dataCleanTask(){
		DataCleanTask.dataCleanHandleProcess();
	}
	
	/**
	 * 刷新大屏的全网热点模块
	 */
	public void qwrdRefreshTask(){
		ScreenRefreshTask.refreshHandleProcess("qwrd");
	}
	
	/**
	 * 刷新大屏的本地新闻模块
	 */
	public void bdxwRefreshTask(){
		ScreenRefreshTask.refreshHandleProcess("bdxw");
	}
	
	/**
	 * 刷新大屏的网络热搜模块
	 */
	public void wlrsRefreshTask(){
		ScreenRefreshTask.refreshHandleProcess("wlrs");
	}
	
	/**
	 * 刷新大屏的地方舆论模块
	 */
	public void dfylRefreshTask(){
		ScreenRefreshTask.refreshHandleProcess("dfyl");
	}
	
	/**
	 * 刷新大屏的生产力统计模块
	 */
	public void scltjRefreshTask(){
		ScreenRefreshTask.refreshHandleProcess("scltj");
	}
	
	/**
	 * 刷新大屏的影响力统计模块
	 */
	public void yxltjRefreshTask(){
		ScreenRefreshTask.refreshHandleProcess("yxltj");
	}
	
	/**
	 * 刷新大屏的资讯热榜模块
	 */
	public void zxrbRefreshTask(){
		ScreenRefreshTask.refreshHandleProcess("zxrb");
	}
	
	/**
	 * 刷新大屏的视频热榜模块
	 */
	public void sprbRefreshTask(){
		ScreenRefreshTask.refreshHandleProcess("sprb");
	}
}
