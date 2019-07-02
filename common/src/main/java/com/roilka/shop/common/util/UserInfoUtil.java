
package com.roilka.shop.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author sunyi1
 * @date 2018/12/24 15:04
 */
@Slf4j
public class UserInfoUtil {


    /**
     * 返回截取之后的字符串
     *
     * @param s 源字符串
     * @param subLength 需要截取的长度（中文算2个字符）
     * @return
     */
    public static String getSubString(String s, int subLength) {
        if (s==null ||StringUtils.isEmpty(s)) {
            return s;
        }
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
            if (len > subLength) {
                return new String(Arrays.copyOf(c, i));
            }
        }
        return s;
    }

    /**
     * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
     *
     * @param c
     * @return
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return (c / k) == 0 ? true : false;
    }
}
