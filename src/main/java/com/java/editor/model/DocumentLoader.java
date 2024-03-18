package com.java.editor.model;

import com.java.editor.model.enums.DocumentType;
import com.java.editor.model.status.InfoStatusMessage;
import com.java.editor.model.status.StatusMessage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;

public class DocumentLoader {
    public static Pair<Document, StatusMessage> loadFile(DocumentType documentType, File savePath) throws IOException, ClassNotFoundException {
        Document document = switch (documentType) {
            case TEXT -> readFromText(savePath);

            case BINARY -> readFromSerialized(savePath);
        };

        return new Pair<>(document, new InfoStatusMessage("Successfully loaded \"" + document.filename() + "\"."));
    }

    private static Document readFromText(File file) throws IOException {
        String filename = FileUtils.removeFileExtension(file.toPath().getFileName().toString());
        String content = Files.readString(file.toPath());
        String title = content.isBlank() ? "" : Files.readAllLines(file.toPath()).get(0);

        return new Document(filename, title, content);
    }

    private static Document readFromSerialized(File file) throws IOException, ClassNotFoundException {
        return (Document) new ObjectInputStream(Files.newInputStream(file.toPath())).readObject();
    }
}
