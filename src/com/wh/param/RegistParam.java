package com.wh.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * 
 * @author maming
 * @date 2017/11/9
 */
public class RegistParam  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	@NotNull(message="不能为空")
	@Pattern(regexp = "^1[34578]\\d{9}$", message = "格式不正确")
	private String phone;
	
	@NotNull(message="不能为空")
	@Pattern(regexp = "^\\d{4}$", message = "格式不正确")
	private String verifyCode;
	
	@NotNull(message="不能为空")
	private String pwd;
	
	@NotNull(message="不能为空")
	private Integer isExist;
	
	private Double amount;
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getIsExist() {
		return isExist;
	}

	public void setIsExist(Integer isExist) {
		this.isExist = isExist;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
