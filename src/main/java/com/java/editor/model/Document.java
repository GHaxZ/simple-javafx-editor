package com.java.editor.model;

import java.io.Serializable;

public record Document(String filename, String title, String content) implements Serializable {

}
