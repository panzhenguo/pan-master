package com.pan.filter;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenAccessFilter extends ZuulFilter {
	
//	@Autowired
//	private FilterIgnoresConf filterIgnores;

	/**
	 * 过滤器的逻辑
	 */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("send {} request to{}", request.getMethod(),
				request.getRequestURL().toString());
		// 判断 该链接的状态
		boolean isIgnoresFlag = isIgnoresFlag(request);
		Object accessToken = request.getParameter("token");
		if (accessToken == null&&!isIgnoresFlag) {
			try {
				//验证token
				if(checkToke()) {
					log.error("token is error ");
					ctx.setSendZuulResponse(false);
					ctx.setResponseStatusCode(401);
					ctx.getResponse().getWriter().write("token is empty");
				}
			} catch (Exception e) {
			}
			return null;
		}
		log.info("token is  ok");
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
	private boolean checkToke() {
		return false;
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
		// 获取忽略地址filterIgnores.getIgnores().split(",");
//    	String[] ignoreArray =   null;
//		if(ignoreArray!=null) {
//			 for(int i=0;i<ignoreArray.length;i++){
//				if(request.getRequestURL().toString().contains(ignoreArray[i])){
//	        		return  true;
//	            }
//			 }
//		}
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
