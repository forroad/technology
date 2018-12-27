package com.ycjw.technology.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

    /**
     * 判断多个参数是否为空
     * @param args 参数列表
     * @return 是否有参数为空，
     */
    public static Boolean isEmpty(String...args){
        for (String arg:args
             ) {
            if (StringUtils.isEmpty(arg)){
                return true;
            }
        }
        return false;
    }
}
