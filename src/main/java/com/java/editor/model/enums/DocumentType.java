package com.java.editor.model.enums;

public enum DocumentType {
    TEXT("Text file", ".txt"),
    BINARY("Data file", ".dat");

    private final String fileDescription;
    private final String fileExtension;

    DocumentType(String fileDescription, String fileExtension) {
        this.fileDescription = fileDescription;
        this.fileExtension = fileExtension;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
