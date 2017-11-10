package com.wh.controller;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wh.param.LoginParam;
import com.wh.param.RegistParam;
import com.wh.service.UserService;
import com.wh.util.BindingValidation;
import com.wh.util.ResponseHelper;
import com.wh.util.Result;


/**
 * 
 * @author maming
 * @date 2017/11/7
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	
	/**
	 * 用户登录
	 * @param loginParam
	 * @param bindingResult
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@Valid LoginParam param, BindingResult bindingResult) throws IOException {
		Result result = null;
		// 参数校验
		result = BindingValidation.paramsValidation(bindingResult);
		if (result != null) {
			return ResponseHelper.createResponse(result);
		}
		
		result = userService.login(param);
		return ResponseHelper.createResponse(result);
	}
	
	
	/**
	 * 用户注册
	 * @param loginParam
	 * @param bindingResult
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/regist", method = RequestMethod.POST)
	@ResponseBody
	public String regist(@Valid RegistParam param, BindingResult bindingResult) throws IOException {
		Result result = null;
		// 参数校验
		result = BindingValidation.paramsValidation(bindingResult);
		if (result != null) {
			return ResponseHelper.createResponse(result);
		}
		
		result = userService.regit(param);
		return ResponseHelper.createResponse(result);
	}
	
	
	
}
