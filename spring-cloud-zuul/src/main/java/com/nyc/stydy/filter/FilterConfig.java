package com.nyc.stydy.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @description: 服务过滤 比如，用户在登录前，可以将服务请求过滤到指定的页面去
 * @author nyc
 *
 */
@Component
public class FilterConfig extends ZuulFilter {

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Object accessToken = request.getParameter("token");
		if (accessToken == null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				ctx.getResponse().setHeader("Content-Type", "text/html;charset=UTF-8");
				ctx.getResponse().getWriter().write("登录信息为空！");
			} catch (Exception e) {
			}
			return null;
		}
		return null;
	}
}