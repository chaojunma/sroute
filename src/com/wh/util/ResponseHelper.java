package com.wh.util;

import net.sf.json.JSONObject;

public class ResponseHelper {
	
	
	public static String createResponse(Result result) {
		
		if(result.getData() == null){
			result.setData(null);
		}
		JSONObject source = JSONObject.fromObject(result);
		return source.toString();
	}
	
	public static String createResponseNoData(Result result) {
		JSONObject source = JSONObject.fromObject(result);
		source.remove("data");
		return source.toString();
	}

}
