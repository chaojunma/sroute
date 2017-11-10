package com.wh.service;

import com.wh.param.LoginParam;
import com.wh.param.RegistParam;
import com.wh.util.Result;

/**
 * 
 * @author maming
 * @date 2017/11/9
 */
public interface UserService {

	/**
	 * 用户登录
	 * @param param
	 * @return
	 */
	public Result login(LoginParam param); 
	
	/**
	 * 用户注册
	 * @param param
	 * @return
	 */
	public Result regit(RegistParam param);
}
