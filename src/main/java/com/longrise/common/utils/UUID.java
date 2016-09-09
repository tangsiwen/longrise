package com.longrise.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class UUID
{
    public static String UUID_32(){
    	return RandomStringUtils.randomAlphanumeric(32);
    }
    public static String UUID_4(){
    	return RandomStringUtils.randomAlphanumeric(4);
    }
    public static String UUID_5(){
    	return RandomStringUtils.randomAlphanumeric(5);
    }
    public static String UUID_16(){
    	return RandomStringUtils.randomAlphanumeric(16);
    }
    public static String UUID_64(){
    	return RandomStringUtils.randomAlphanumeric(64);
    }
}
