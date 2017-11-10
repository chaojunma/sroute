package com.wh.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wh.cache.Memory;
import com.wh.mapper.UserMapper;
import com.wh.model.UserInfo;
import com.wh.param.LoginParam;
import com.wh.param.RegistParam;
import com.wh.result.RegistResult;
import com.wh.result.LoginUser;
import com.wh.service.UserService;
import com.wh.util.Md5Utils;
import com.wh.util.Result;
import com.wh.util.ResultCode;


/**
 * 
 * @author maming
 *@date 2017/11/9
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private Memory memory;

	@Override
	public Result login(LoginParam param) {
		Result result = null;
		LoginUser userInfo = null;
		if (param.getType() == 1) { // 密码登录
			if (param.getPwd() == null) {
				result = new Result(ResultCode.PARAMETER_ERROR);
				return result;
			}
			String pwd = param.getPwd();
			param.setPwd(Md5Utils.getMd5(pwd));
			userInfo = userMapper.loginByPwd(param);
		} else { // 验证码登录
			userInfo = userMapper.loginByVerifyCode(param);
		}
		if(userInfo == null) { //用户名或密码错误
			result = new Result(ResultCode.USERNAME_ERROR);
			return result;
		}
		
		memory.saveLoginUser(userInfo); //把用户信息保存到缓存
		result = new Result(ResultCode.SUCCESS);
		result.setData(userInfo);
		return result;
	}
	
	

	@Override
	public Result regit(RegistParam param) {
		Result result = null;
		RegistResult data = new RegistResult();
		if(param.getIsExist() == 1) { //存在现金红包
			Double amount = param.getAmount();
			if(amount == null || !String.valueOf(amount).matches("^[-|+]?\\d+(.\\d{1})?$")) {
				result =  new Result(ResultCode.PARAMETER_ERROR);
				return result;
			}
		} 
		
		UserInfo user = userMapper.getUser(param.getPhone());
		if(user !=null) {//验证用户是否已存在
			result =  new Result(ResultCode.USER_EXIST);
			return result;
		}
		
		param.setPwd(Md5Utils.getMd5(param.getPwd()));
		userMapper.insertUser(param);
		BeanUtils.copyProperties(param, data);
		result = new Result(ResultCode.SUCCESS);
		result.setData(data);
		return result;
	}

}
