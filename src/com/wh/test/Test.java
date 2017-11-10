package com.wh.test;

import com.wh.cache.Memory;
import com.wh.result.LoginUser;

public class Test {

	
	public static void main(String[] args) {
		Memory memory =new Memory();
		LoginUser loginUser = new LoginUser();
		loginUser.setPhone("13161617105");
		memory.saveLoginUser(loginUser);
	}
}
