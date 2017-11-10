package com.wh.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


/**
 * 
 * @author maming
 *@date 2017/11/9
 */
public class BindingValidation {

	
	
	/**
	 * @explain 参数校验
	 * @param bindingResult
	 * @return
	 */
	public static Result paramsValidation(BindingResult bindingResult){
		Result result = null;
		if(bindingResult.hasErrors()){  
	        ObjectError objectError =  bindingResult.getAllErrors().get(0);
	        FieldError fileError =  (FieldError) objectError;
	        ResultCode resultCode = ResultCode.PARAMETER_ERROR;
			result = new Result(resultCode.getCode(),fileError.getField() +  objectError.getDefaultMessage());
	    }
		return result;
	}
}
