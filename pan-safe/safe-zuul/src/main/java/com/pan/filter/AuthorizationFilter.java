package com.pan.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthorizationFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.info("进入鉴权中心...........");
		return null;
	}

	@Override
	public String filterType() {
		//pre、route、post、error四
		return "route";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
