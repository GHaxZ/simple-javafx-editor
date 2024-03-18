package com.java.editor.model.status;

public class EditingStatusMessage implements StatusMessage {
    private final String messageFormat = "Editing \"%s\" - %d words";
    private final String fullMessage;

    public EditingStatusMessage(String filename, int wordCount) {
        fullMessage = messageFormat.formatted(filename, wordCount);
    }

    @Override
    public String getMessage() {
        return fullMessage;
    }
}
