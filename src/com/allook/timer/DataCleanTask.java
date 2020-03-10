package com.allook.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.allook.utils.DateUtil;
import com.allook.utils.JdbcUtil;

/**
 * 
 * <p>文件名称: DataCleanTask.java</p>
 * <p>文件描述: 清理过期数据</p>
 * <p>版权所有: 版权所有(C)2011-2018</p>
 * <p>公   司: 山东广电新媒体中心技术部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2018年6月1日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  zhengxi
 */
public class DataCleanTask {

	private static Logger log = Logger.getLogger(DataCleanTask.class);
	
	public static void dataCleanHandleProcess(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date_now = sdf.format(new Date());
		log.info(date_now + "的过期数据清理任务开始.....");
		String date_last = DateUtil.getSpecifiedMonthBefore(date_now);
		String time_last = date_last + " 00:00:00";
		String news_sql = "delete from t_hotspot_news_clue where create_time < '" + time_last + "'";
		String words_sql = "delete from t_hot_search where create_time < '" + time_last + "'";
		String comment_sql = "delete from t_local_opinion where create_time < '" + time_last + "'";
		// 新建数据库连接对象
		JdbcUtil jdbcUtil = new JdbcUtil();
		try {
			jdbcUtil.init(0);
			try {
				jdbcUtil.insert_update(news_sql);
				log.info(date_last + "之前的资讯数据已删除");
			} catch (Exception e) {
				log.error("删除过期资讯数据错误", e);
			}
			try {
				jdbcUtil.insert_update(words_sql);
				log.info(date_last + "之前的热词数据已删除");
			} catch (Exception e) {
				log.error("删除过期热词数据错误", e);
			}
			try {
				jdbcUtil.insert_update(comment_sql);
				log.info(date_last + "之前的评论数据已删除");
			} catch (Exception e) {
				log.error("删除过期评论数据错误", e);
			}
		} catch (Exception e) {
			log.error("数据库链接初始化错误", e);
		} finally {
			if(jdbcUtil != null){
				jdbcUtil.closeConn();
			}
		}
		log.info(date_now + "的过期数据清理任务执行完毕.....");
	}
}
