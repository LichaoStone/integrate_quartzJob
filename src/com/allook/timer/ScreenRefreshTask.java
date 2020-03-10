package com.allook.timer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;

import com.allook.utils.HttpClientServer;
import com.allook.utils.MD5;
import com.allook.utils.ReadProperties;

/**
 * 
 * <p>文件名称: ScreenRefreshTask.java</p>
 * <p>文件描述: 通知接口刷新大屏数据的定时任务</p>
 * <p>版权所有: 版权所有(C)2011-2018</p>
 * <p>公   司: 山东广电新媒体中心技术部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2018年6月6日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  zhengxi
 */
public class ScreenRefreshTask {

	private static Logger log = Logger.getLogger(ScreenRefreshTask.class);
	
	public static void refreshHandleProcess(String modelType){
		Map<String, String> tmpMap = new HashMap<>();
//		tmpMap.put("modelType", modelType);
		String time = Long.toString(System.currentTimeMillis());
//		tmpMap.put("_timestamp", time);
		String jiami = "qingkintegratemodelType"+modelType+"_timestamp"+time+"qingkintegrate";
		String md5 = MD5.getMD5String(jiami);
//		tmpMap.put("_sign", md5);
		String url = ReadProperties.getProperties("refreshUrl")+"?modelType="+modelType+"&_timestamp="+time+"&_sign="+md5;
		try {
			Map<String, String> result = HttpClientServer.httpPost(url, tmpMap);
			log.info(result.get("result"));
			log.info("大屏数据["+modelType+"]刷新成功");
		} catch (HttpException e) {
			log.error("大屏数据["+modelType+"]刷新失败", e);
		} catch (IOException e) {
			log.error("大屏数据["+modelType+"]刷新失败", e);
		}
	}
}
