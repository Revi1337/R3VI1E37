package com.example.privmall.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class DefenseFileUploadUtils {

    private static final byte[] PNG_HEADER = {(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'};

    public static String cleanFileName(String fileName) {
        return StringUtils.trimAllWhitespace(
                StringUtils.cleanPath(Objects.requireNonNull(fileName))
        );
    }

    public static boolean hasContainMaliciousCharacter(String fileName) {
        return fileName.contains("..") || fileName.contains("\\") || fileName.contains("/");
    }

    public static boolean hasMultipleExtensions(String fileName) {
        int count = 0;
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = fileName.indexOf(".", lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex++;
            }
        }
        return count >= 2;
    }

    public static boolean matchesFileExtensions(String fileName, String expectedExtension) {
        return expectedExtension.equals(StringUtils.getFilenameExtension(fileName));
    }

    public static boolean checkPngFileWithMagicBytes(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();

        byte[] fileHeader = new byte[PNG_HEADER.length];
        int bytesRead = inputStream.read(fileHeader, 0, PNG_HEADER.length);

        boolean isPng = false;
        if (bytesRead == PNG_HEADER.length) {
            isPng = isHeaderMatch(fileHeader);
        }

        return isPng;
    }

    private static boolean isHeaderMatch(byte[] fileHeader) {
        for (int i = 0; i < DefenseFileUploadUtils.PNG_HEADER.length; i++) {
            if (fileHeader[i] != DefenseFileUploadUtils.PNG_HEADER[i]) {
                return false;
            }
        }
        return true;
    }

}
//    public static boolean isMagicByteValid(byte[] fileBytes, int extensionIndex) {
//        byte[] magicBytes = MAGIC_BYTES[extensionIndex];
//        byte[] fileStartBytes = Arrays.copyOfRange(fileBytes, 0, magicBytes.length);
//        return Arrays.equals(fileStartBytes, magicBytes);
//    }

//    public static String retrieveContentType() {
//        return Files.probeContentType("asd");
//    }

//        String fileName = originalFileName.substring(originalFileName.lastIndexOf("\\") + 1);
//        Path path = Paths.get(UPLOAD_DIR + "\\" + fileName);
//        boolean image = Files.probeContentType(path).startsWith("image");
