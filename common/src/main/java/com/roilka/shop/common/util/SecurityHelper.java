
package com.roilka.shop.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Random;

/**
 * technician加密算法（Hash密码）
 *
 * @author taoyuan
 * @date 2019/3/4 15:59
 */
@Slf4j
public class SecurityHelper {

    /**
     * SHA-256加密
     *
     * @param password
     * @return
     */
    public static String hashPassword(String password) {
        try {
            byte[] salt = generateSaltValue(4);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] hash = messageDigest.digest(password.getBytes("UnicodeLittleUnmarked"));
            return binaryToHex(salt) + binaryToHex(hash);
        } catch (Exception e) {
            log.info("加密失败, p={}, e={}", password, e);
            return null;
        }
    }

    /**
     * MD5加密
     *
     * @param password
     * @return
     */
    public static String getMd5(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(password.getBytes("utf-8"));
            return binaryToHex(hash);
        } catch (Exception e) {
            log.info("加密失败, p={}, e={}", password, e);
            return null;
        }
    }

    private static String binaryToHex(byte[] data) {
        if (data == null) {
            return null;
        }
        char[] chArray = new char[data.length * 2];
        for (int index = 0; index < data.length; ++index) {
            byte num = data[index];

            chArray[2 * index] = nibbleToHex((byte) ((num & 0x0FF) >> 4));
            chArray[2 * index + 1] = nibbleToHex((byte) ((num & 0x0FF) & (15 & 0x0FF)));
        }
        return new String(chArray);
    }

    private static char nibbleToHex(byte nibble) {
        if (nibble < (byte) 10) {
            return (char) ((nibble & 0x0FF) + (48 & 0x0FF));
        }
        return (char) ((int) nibble - 10 + 65);
    }

    private static byte[] generateSaltValue(int length) {
        byte[] buffer = new byte[length];
        new Random(System.currentTimeMillis()).nextBytes(buffer);
        return buffer;
    }

    public static String encrypt(String password, String token) {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(token)) {
            return null;
        }

        byte[] bpath = DatatypeConverter.parseBase64Binary(password);
        password = new String(bpath);

        token = token.substring(1, token.length() - 1 - 1);

        char[] a = password.toCharArray();
        char[] b = token.toCharArray();

        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ b[i]);
        }
        return new String(a);
    }
}
