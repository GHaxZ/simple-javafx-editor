package com.java.editor.controller;

import com.java.editor.model.Document;
import com.java.editor.model.DocumentLoader;
import com.java.editor.model.enums.DocumentType;
import com.java.editor.model.status.ErrorStatusMessage;
import com.java.editor.model.status.StatusMessage;
import javafx.util.Pair;

import java.io.File;

public class LoadFileController {
    public Pair<Document, StatusMessage> loadFile(DocumentType documentType, File savePath) {
        try {
            return DocumentLoader.loadFile(documentType, savePath);
        } catch (Exception e) {
            return new Pair<>(null, new ErrorStatusMessage("Failed loading document: " + e.getCause()));
        }
    }
}
