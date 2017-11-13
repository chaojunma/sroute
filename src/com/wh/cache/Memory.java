package com.wh.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wh.constant.Constants;
import com.wh.result.LoginUser;
import com.wh.util.Md5Utils;
import com.wh.util.ThreadTokenHolder;
import com.wh.util.TokenProcessor;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Component
public class Memory {

	@Autowired
	private Cache ehcache; // 注意这里引入的Cache是net.sf.ehcache.Cache

	public void setValue(String key, String value) {
		ehcache.put(new Element(key, value));
	}

	public Object getValue(String key) {
		Element element = ehcache.get(key);
		return element != null ? element.getValue() : null;
	}
	
	public void setVertifyCode(String key, String value) {
		Element element = ehcache.get(key);
		if (element != null) {
			// 根据key清空之前的验证码信息
			ehcache.remove(key);
			ehcache.remove(element.getValue());
		}
		
		//重新将验证码放入缓存
		ehcache.put(new Element(key, value, false, Constants.VERIFY_CODE_EXPIRE_TIME, Constants.VERIFY_CODE_EXPIRE_TIME));
	}
	
	/**
	 * 保存当前登录用户信息
	 * 
	 * @param loginUser
	 */
	public void saveLoginUser(LoginUser loginUser) {
		// 生成seed和token值
		String seed = Md5Utils.getMd5(loginUser.getPhone());
		String token = TokenProcessor.generateToken(seed, loginUser);
		// 保存token到登录用户中
		loginUser.setToken(token);
		// 清空之前的登录信息
		 clearLoginInfoBySeed(seed);
		// 保存新的token和登录信息
		ehcache.put(new Element(seed, token, false, Constants.TOKEN_EXPIRE_TIME, 0));
		ehcache.put(new Element(token, loginUser, false, Constants.TOKEN_EXPIRE_TIME, 0));
	}

	/**
	 * 获取当前线程中的用户信息
	 * 
	 * @return
	 */
	public LoginUser currentLoginUser() {
		Element element = ehcache.get(ThreadTokenHolder.getToken());
		return element == null ? null : (LoginUser) element.getValue();
	}

	/**
	 * 根据token检查用户是否登录
	 * 
	 * @param token
	 * @return
	 */
	public boolean checkLoginInfo(String token) {
		Element element = ehcache.get(token);
		return element != null && (LoginUser) element.getValue() != null;
	}

	/**
	 * 清空登录信息
	 */
	public void clearLoginInfo() {
		LoginUser loginUser = currentLoginUser();
		if (loginUser != null) {
			// 根据登录的用户名生成seed，然后清除登录信息
			String seed = Md5Utils.getMd5(loginUser.getPhone());
			clearLoginInfoBySeed(seed);
		}
	}

	/**
	 * 根据seed清空登录信息
	 * 
	 * @param seed
	 */
	public void clearLoginInfoBySeed(String seed) {
		// 根据seed找到对应的token
		Element element = ehcache.get(seed);
		if (element != null) {
			// 根据token清空之前的登录信息
			ehcache.remove(seed);
			ehcache.remove(element.getValue());
		}
	}
}
