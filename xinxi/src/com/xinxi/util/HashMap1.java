package com.xinxi.util;

import java.util.HashMap;

import net.sf.json.JSONObject;

public class HashMap1 extends HashMap {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		JSONObject obj = JSONObject.fromObject(this);
		return obj.toString();

	}
}
