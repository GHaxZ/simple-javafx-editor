package com.java.editor.view.eventhandlers;

import com.java.editor.model.TextProcessor;
import com.java.editor.model.status.EditingStatusMessage;
import com.java.editor.view.MainApplication;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
        MainApplication view = MainApplication.getInstance();

        view.updateStatus(new EditingStatusMessage(view.getFilename(), TextProcessor.countWords(view.getContent())));
    }
}
