package com.wh.filter;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.wh.cache.Memory;
import com.wh.util.ResponseHelper;
import com.wh.util.Result;
import com.wh.util.ResultCode;
import com.wh.util.ThreadTokenHolder;


/**
 * token拦截器
 * @author maming
 * @date 2017/11/10
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private Memory memory;

	private List<String> allowList = Arrays.asList("/sroute/user/login.do","yyy","zzz"); // 放行的URL列表

	private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 判断请求的URI是否运行放行，如果不允许则校验请求的token信息
		if (!checkAllowAccess(request.getRequestURI())) {
			// 检查请求的token值是否为空
			String token = getTokenFromRequest(request);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			ResultCode resultCode = ResultCode.FORBID_REQUEST_ERROR;
			Result result = new Result(resultCode);
			if (StringUtils.isEmpty(token)) {
				response.getWriter().print(ResponseHelper.createResponseNoData(result));
				response.getWriter().close();
				return false;
			}
			if (!memory.checkLoginInfo(token)) {
				response.getWriter().print(ResponseHelper.createResponseNoData(result));
				response.getWriter().close();
				return false;
			}
			ThreadTokenHolder.setToken(token); // 保存当前token，用于Controller层获取登录用户信息
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * 检查URI是否放行
	 * 
	 * @param URI
	 * @return 返回检查结果
	 */
	private boolean checkAllowAccess(String URI) {
		if (!URI.startsWith("/")) {
			URI = "/" + URI;
		}
		for (String allow : allowList) {
			if (PATH_MATCHER.match(allow, URI)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 从请求信息中获取token值
	 * 
	 * @param request
	 * @return token值
	 */
	private String getTokenFromRequest(HttpServletRequest request) {
		// 默认从header里获取token值
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)) {
			// 从请求信息中获取token值
			token = request.getParameter("token");
		}
		return token;
	}

	public List<String> getAllowList() {
		return allowList;
	}

	public void setAllowList(List<String> allowList) {
		this.allowList = allowList;
	}

}
