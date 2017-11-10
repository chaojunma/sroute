package com.wh.util;

public class Result {

	private Integer code; //状态码
	
	private String message; //描述
	
	private Object data; //返回数据
	
	public Result() {
		code = 200;
		message = "success";
	}
	
	public Result(Integer code,String message) {
		this.code = code;
		this.message = message;
	}
	
	public Result(Integer code,String message,Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public Result(ResultCode resultCode) {
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
