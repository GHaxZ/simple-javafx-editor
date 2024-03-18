package com.java.editor.model;

import com.java.editor.model.enums.DocumentType;
import com.java.editor.model.status.InfoStatusMessage;
import com.java.editor.model.status.StatusMessage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class DocumentSaver {
    public static StatusMessage saveFile(Document document, DocumentType documentType, File savePath) throws IOException {
        switch (documentType) {
            case TEXT -> writeToText(document, savePath);

            case BINARY -> writeToSerialized(document, savePath);
        }

        return new InfoStatusMessage("Successfully saved \"" + document.filename() + "\".");
    }

    private static void writeToText(Document document, File savePath) throws IOException {
        Files.writeString(savePath.toPath(),document.title() + "\n" + document.content());
    }

    private static void writeToSerialized(Document document, File savePath) throws IOException {
        new ObjectOutputStream(Files.newOutputStream(savePath.toPath())).writeObject(document);
    }
}
