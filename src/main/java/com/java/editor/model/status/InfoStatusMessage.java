package com.java.editor.model.status;

public class InfoStatusMessage implements StatusMessage {
    private final String messageFormat = "Info - %s";
    private final String fullMessage;

    public InfoStatusMessage(String message) {
        fullMessage = messageFormat.formatted(message);
    }

    @Override
    public String getMessage() {
        return fullMessage;
    }
}
