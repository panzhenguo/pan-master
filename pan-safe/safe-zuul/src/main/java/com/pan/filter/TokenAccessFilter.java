package com.pan.filter;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.pan.filter.conf.FilterIgnoresConf;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenAccessFilter extends ZuulFilter {
	
	@Autowired
	private FilterIgnoresConf filterIgnores;

	/**
	 * 过滤器的逻辑
	 */
	@Override
	public Object run() {
		
		try {

			 InetAddress addr = InetAddress.getLocalHost();  
	         String ip=addr.getHostAddress().toString(); //获取本机ip  
	         String hostName=addr.getHostName().toString(); //获取本机计算机名称  
	         System.out.println(ip);
	         System.out.println(hostName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("send {} request to{}", request.getMethod(),
				request.getRequestURL().toString());
		//改地址是否跳过
		boolean isIgnoresFlag = isIgnoresFlag(request);
		//验证token 合法性
		boolean tokenIsTtue = checkToke(request.getParameter("token"));
		
		if(isIgnoresFlag) {
			
		}else {
			if (!tokenIsTtue||isIgnoresFlag) {
				try {
					log.error("token is error ");
					ctx.setSendZuulResponse(false);
					ctx.setResponseStatusCode(401);
					ctx.getResponse().getWriter().write("token is empty");
				} catch (Exception e) {
				}
				return null;
			}
		}
		log.info("token is  ok    " +filterIgnores.getIgnores());
		return null;
	}
	
	/**
	 * 验证token
	 * @Title: checkToke  
	 * @Description: 
	 * @CreateTime 2018年8月3日下午3:35:10
	 * @Author ttsf-pzg
	 * @return
	 */
	private boolean checkToke(Object token) {
		return true;
	}

	/**
	 * 判断本次请求 是否是忽略请求
	 * @Title: isIgnoresFlag  
	 * @Description: 
	 * @CreateTime 2018年8月2日下午3:02:02
	 * @Author ttsf-pzg
	 * @param request
	 * @return
	 */
	private boolean isIgnoresFlag(HttpServletRequest request) {
		// 获取忽略地址
    	String[] ignoreArray =  filterIgnores.getIgnores().split(",");
		if(ignoreArray!=null) {
			 for(int i=0;i<ignoreArray.length;i++){
				if(request.getRequestURL().toString().contains(ignoreArray[i])){
					log.info("这是一个不需要验证的请求!!!");
	        		return  true;
	            }
			 }
		}
		return false;
	}


	@Override
	public String filterType() {
		return "pre";
	}
	@Override
	public int filterOrder() {
		return 0;
	}
	@Override
	public boolean shouldFilter() {
		return true;
	}
}
