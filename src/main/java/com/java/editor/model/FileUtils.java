package com.java.editor.model;

public class FileUtils {
    public static String removeFileExtension(String file) {
        return file.replaceFirst("[.][^.]+$", "");
    }
}
