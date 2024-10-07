package com.jeneric.eventappfrontend.service.location.utilities;

import junit.framework.TestCase;

import com.jeneric.eventappfrontend.service.location.encryption.AESUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class LocationParserTest extends TestCase {

    private static final String algorithm = "AES/CBC/PKCS5Padding";

    public void testDecryptionEncryptionFunctionality()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeySpecException, IOException {

        String input = "gcqdsf542";

        SecretKey key = AESUtil.getKeyFromPassword();
        IvParameterSpec iv = AESUtil.getIv();

        System.out.println(Arrays.toString(iv.getIV()));

        String cipherText = AESUtil.encrypt(algorithm, input, key, iv, false);

        System.out.println(cipherText);
        String plainText = AESUtil.decrypt(algorithm, cipherText, key, iv);

        assertEquals(input, plainText);
    }

//    -------------- DO NOT DELETE - REQUIRED FOR CONFIG -----------------------------------
//    public void testTrivialBuildIvParameterSpecFile()
//            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
//            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeySpecException, IOException {
//
//        File file = new File("./src/main/res/iv.jpg");
//
//		byte[] hardcodedIV = { INSERT_BYTES_HERE };
//		IvParameterSpec ivParameterSpec = new IvParameterSpec(hardcodedIV);
//
//		try (FileOutputStream outputStream = new FileOutputStream(file)) {
//			outputStream.write(ivParameterSpec.getIV());
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//
//		byte[] bytes = Files.readAllBytes(file.toPath());
//		System.out.println(Arrays.toString(new IvParameterSpec(bytes).getIV()));
//
//        assertEquals("test","test");
//    }
//    ---------------------------------------------------------------------------------------



}