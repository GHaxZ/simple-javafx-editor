package com.java.editor.controller;

import com.java.editor.model.Document;
import com.java.editor.model.DocumentSaver;
import com.java.editor.model.enums.DocumentType;
import com.java.editor.model.status.ErrorStatusMessage;
import com.java.editor.model.status.StatusMessage;

import java.io.File;

public class SaveFileController {
    public StatusMessage saveFile(Document document, DocumentType documentType, File savePath) {
        try {
            return DocumentSaver.saveFile(document, documentType, savePath);
        } catch (Exception e) {
            return new ErrorStatusMessage("Failed saving document: " + e.getCause());
        }
    }
}
