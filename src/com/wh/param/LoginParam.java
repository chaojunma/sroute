package com.wh.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message="不能为空")
	@Pattern(regexp = "^1[34578]\\d{9}$", message = "格式不正确")
	private String phone;
	
	private String pwd;
	
	private String verifyCode;
	
	@NotNull(message="不能为空")
	private Integer type; //1密码登录 2验证码登陆

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
