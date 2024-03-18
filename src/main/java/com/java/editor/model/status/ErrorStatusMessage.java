package com.java.editor.model.status;

public class ErrorStatusMessage implements StatusMessage {
    private final String messageFormat = "Error - %s";
    private final String fullMessage;

    public ErrorStatusMessage(String message) {
        fullMessage = messageFormat.formatted(message);
    }

    @Override
    public String getMessage() {
        return fullMessage;
    }
}
