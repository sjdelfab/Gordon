package org.sjd.gordon.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class SHA_256_Util {
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	md.update(password.getBytes("UTF-8"));
    	byte[] digest = md.digest();
    	return Base64.encode(digest);
    }

}

