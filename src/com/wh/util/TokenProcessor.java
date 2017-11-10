package com.wh.util;

import com.wh.result.LoginUser;
import net.sf.json.JSONObject;

public class TokenProcessor {

	/**
	 * 生成token (该token是可变的，因为加入了时间戳)
	 * @param seed
	 * @param user
	 * @return
	 */
	public static  String generateToken (String seed,LoginUser user){
		JSONObject json = new JSONObject();
		json.put("seed", seed);
		json.put("phone", user.getPhone());
		json.put("timestamp", System.currentTimeMillis());
		return Md5Utils.getMd5(json.toString());
	}
	
}
