package com.java.editor.model;

public class TextProcessor {
    public static int countWords(String str) {

        if(str == null) {
            return 0;
        }

        if(str.isBlank()) {
            return 0;
        }

        final String[] words = str.split("[^\\p{L}\\p{Nd}']+");
        return words.length;
    }
}
