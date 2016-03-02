package ua.alekseytsev.LibraryApp.web.util;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provides the password hashing
 */
public final class PasswordHash {
    public static final String ALGORITHM_MD5 = "MD5";
    public static final String ALGORITHM_SHA_512 = "SHA-512";
    private static final Logger LOG = LogManager.getLogger(PasswordHash.class);
    private static final String ENCODING = "UTF-8";

    public static String hash(String input) {
        LOG.debug("Start hashing password");
        StringBuilder sb = new StringBuilder();
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(ALGORITHM_SHA_512);
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage());

        }
        try {
            digest.update(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage());
        }
        for (byte digit : digest.digest()) {
            if ((digit & 0xFF) > 0x0F) {
                sb.append(Integer.toHexString(digit & 0xFF).toUpperCase());
            } else {
                sb.append(0).append(Integer.toHexString(digit & 0xFF).toUpperCase());
            }
        }
        LOG.debug("Finish hashing password");
        return sb.toString();
    }
}