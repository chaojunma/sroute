package com.wh.util;

import java.util.Map;

public class BeanUtils {

	/**
	 * map转换成bean对象
	 * 
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		org.apache.commons.beanutils.BeanUtils.populate(obj, map);

		return obj;
	}

	/**
	 * bean转换成map对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null)
			return null;

		return new org.apache.commons.beanutils.BeanMap(obj);
	}
}
