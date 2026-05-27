package com.mizore.test;


import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

/**
 * 图片转换工具
 *
 * @author MizoreBlue
 */
public class ImageUtils {

    public static String toBase64(String imagePath) throws Exception {
        File file = new File(imagePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
