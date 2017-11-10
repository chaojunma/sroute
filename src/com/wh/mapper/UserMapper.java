package com.wh.mapper;

import com.wh.model.UserInfo;
import com.wh.param.LoginParam;
import com.wh.param.RegistParam;
import com.wh.result.LoginUser;


/**
 * 
 * @author maming
 *@date 2017/11/9
 */
public interface UserMapper {

	//通过密码登录
	public LoginUser loginByPwd(LoginParam param);
	
	//通过验证码登录
	public LoginUser loginByVerifyCode(LoginParam param);
	
	//用户注册
	public void insertUser(RegistParam param);
	
	//通过手机号获取用户信息
	public UserInfo getUser(String phone);
}
