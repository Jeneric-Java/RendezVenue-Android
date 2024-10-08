package com.jeneric.eventappfrontend.service.location.encryption;


import static android.text.Html.FROM_HTML_MODE_COMPACT;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.util.Log;

import androidx.core.text.HtmlCompat;

import com.jeneric.eventappfrontend.R;
import com.jeneric.eventappfrontend.ui.main.MainActivity;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AESUtil {

    public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv, boolean escape) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {

        if (escape) input = escapeHTML(input);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());


        return Base64.getMimeEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);

        byte[] plainText = null;
        plainText = cipher.doFinal(Base64.getMimeDecoder().decode(cipherText));

        return Html.fromHtml(new String(plainText), Html.FROM_HTML_MODE_LEGACY).toString();
    }

    protected static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    public static SecretKey getKeyFromPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    protected static IvParameterSpec generateDynamicIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static void writeIvToFile() throws IOException {

        File file = new File("./src/main/res/iv.jpg");

        IvParameterSpec ivParameterSpec = generateDynamicIv();

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(ivParameterSpec.getIV());
        }
    }

    public static IvParameterSpec getIv() throws IOException {

        File file = new File("./src/main/res/iv.jpg");
        byte[] bytes = Files.readAllBytes(file.toPath());
        return new IvParameterSpec(bytes);
    }

    private static String escapeHTML(String s) {
        StringBuilder out = new StringBuilder(Math.max(16, s.length()));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 127 || c == '"' || c == '\'' || c == '<' || c == '>' || c == '&') {
                out.append("&#");
                out.append((int) c);
                out.append(';');
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }
}
