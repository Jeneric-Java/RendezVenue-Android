package com.jeneric.eventappfrontend.service.location.utilities;

import android.app.AlertDialog;

import com.jeneric.eventappfrontend.service.location.encryption.AESUtil;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import android.content.DialogInterface;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import ch.hsr.geohash.GeoHash;

public class LocationParser {

    private static final String algorithm = "AES/CBC/PKCS5Padding";

    public static String toGeoHashEnc(double latitude, double longitude, String password, String salt, IvParameterSpec iv) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        String plainText = GeoHash.geoHashStringWithCharacterPrecision(latitude, longitude, 9);

        SecretKey key = AESUtil.getKeyFromPassword(password, salt);

        return AESUtil.encrypt(algorithm, plainText, key, iv, true);
    }



}
