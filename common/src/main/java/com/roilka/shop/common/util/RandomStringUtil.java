package com.roilka.shop.common.util;

import java.util.Random;

public class RandomStringUtil {

    private static char[] constant = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * 随机生产字符串 数字加字母.
     * @param length 目标长度
     * @return 生成的字符串
     */
    public static String GenerateRandomString(int length) {
        StringBuilder checkCode = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            checkCode.append(constant[rd.nextInt(constant.length)]);
        }
        return checkCode.toString();
    }
    
    
    public static void main(String[] args) {
        System.out.println( GenerateRandomString(8));
    }
}
