package com.allook.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * http请求发送工具类
 * 
 * @author tangming
 * @date 2017-6-21
 */
public class HttpClientServer {

	/**
	 * 发送post请求
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 */
	public static Map<String, String> httpPost(String url, Map<String, String> params) throws HttpException,
			IOException {
		PostMethod post = null;
		Map<String, String> resultMap = new HashMap<>();
		HttpClient httpClient = new HttpClient();
		try {
			
			post = new PostMethod(url);
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			post.setRequestHeader("content-type", "application/x-www-form-urlencoded");
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
			NameValuePair[] data = new NameValuePair[params.size()];
			int index = 0;
			for (Map.Entry<String, String> entry : params.entrySet()) {
				data[index] = new NameValuePair(entry.getKey(), entry.getValue());
				index++;
			}
			post.setRequestBody(data);
			int stat = httpClient.executeMethod(post);
			resultMap.put("status", String.valueOf(stat));
			resultMap.put("result", post.getResponseBodyAsString());
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
			if(httpClient != null){
				httpClient.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
		return resultMap;
	}
	
	/**
	 * 下载图片文件
	 * @param urlString
	 * @param filename
	 * @param savePath
	 * @throws Exception
	 */
	public static void download(String urlString, String fileName, String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + fileName);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}
}
