package com.yfm.posetion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5EncodeUtil {

	public static String MD5Encode(byte[] toencode) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(toencode);
            return HexEncode(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
     
    public static String HexEncode(byte[] toencode) {
        StringBuilder sb = new StringBuilder(toencode.length * 2);
        for(byte b: toencode){
            sb.append(Integer.toHexString((b & 0xf0) >>> 4));
            sb.append(Integer.toHexString(b & 0x0f));
        }
        return sb.toString();
    }
    
    public static String Encrypt(String code,String key){
    	String md5code = "";
    	if(code!=null&&!"".equals(code)){
    		StringBuffer sb=new StringBuffer(code);
    		if(key!=null&&key.length()==2){
    			Character m=key.charAt(0);
    			Character v=key.charAt(1);
    			int ct=Integer.parseInt(m.toString());
    			int codelength=sb.length();
    			if(codelength<9){
    				for(int i=0;i<9-codelength;i++){
    					sb.append("*");
    				}
    			}
    			sb.insert(ct, v);
				md5code=MD5Encode(sb.toString().getBytes());
				System.out.println("密匙:"+key+",原字符串:"+code+",拼接后,"+sb.toString()+",Md5后:"+md5code);
    		}
    	}
    	return md5code;
    }
}
