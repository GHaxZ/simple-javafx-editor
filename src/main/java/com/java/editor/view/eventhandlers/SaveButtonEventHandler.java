package com.java.editor.view.eventhandlers;

import com.java.editor.controller.LoadFileController;
import com.java.editor.controller.SaveFileController;
import com.java.editor.model.Document;
import com.java.editor.model.DocumentSaver;
import com.java.editor.model.enums.DocumentType;
import com.java.editor.model.status.StatusMessage;
import com.java.editor.view.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class SaveButtonEventHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        SaveFileController saveFileController = new SaveFileController();

        MainApplication view = MainApplication.getInstance();

        Document document = view.getDocument();
        DocumentType documentType = view.getDocumentType();
        File savePath = view.showSaveDialogue(
                List.of(
                        new FileChooser.ExtensionFilter(documentType.getFileDescription(),
                                "*" + documentType.getFileExtension())
                )
        );

        if(savePath == null) {
            return;
        }

        StatusMessage result = saveFileController.saveFile(document, documentType, savePath);

        view.updateStatus(result);
    }
}
