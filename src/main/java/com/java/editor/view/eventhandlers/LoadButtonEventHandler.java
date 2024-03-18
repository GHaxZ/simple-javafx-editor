package com.java.editor.view.eventhandlers;

import com.java.editor.controller.LoadFileController;
import com.java.editor.model.Document;
import com.java.editor.model.enums.DocumentType;
import com.java.editor.model.status.ErrorStatusMessage;
import com.java.editor.model.status.StatusMessage;
import com.java.editor.view.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.File;
import java.util.List;

public class LoadButtonEventHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        LoadFileController loadFileController = new LoadFileController();

        MainApplication view = MainApplication.getInstance();

        DocumentType documentType = view.getDocumentType();
        File savePath = view.showOpenDialogue(
                List.of(
                        new FileChooser.ExtensionFilter(documentType.getFileDescription(),
                                "*" + documentType.getFileExtension())
                )
        );

        if(savePath == null) {
            return;
        }

        Pair<Document, StatusMessage> result = loadFileController.loadFile(documentType, savePath);

        view.updateStatus(result.getValue());

        Document document = result.getKey();

        if (document != null) {
            view.loadDocument(document);
        }
    }
}
