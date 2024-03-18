package com.java.editor.view;

import com.java.editor.model.Document;
import com.java.editor.model.TextProcessor;
import com.java.editor.model.enums.DocumentType;
import com.java.editor.model.status.EditingStatusMessage;
import com.java.editor.view.eventhandlers.KeyEventHandler;
import com.java.editor.view.eventhandlers.LoadButtonEventHandler;
import com.java.editor.view.eventhandlers.SaveButtonEventHandler;
import com.java.editor.model.status.StatusMessage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class MainApplication extends Application {
    private static MainApplication instance;
    private Stage primaryStage;

    // ROW 1
    private final Label filenameLabel = new Label("Filename:");
    private final TextField filenameTextField = new TextField();
    private final RadioButton textRadioButton = new RadioButton("text");
    private final RadioButton binaryRadioButton = new RadioButton("binary");
    private final Button loadButton = new Button("load");
    private final Button saveButton = new Button("save");

    // ROW 2
    private final Label documentTitleLabel = new Label("Title:");
    private final TextField documentTitleTextField = new TextField();
    private final Label documentContentLabel = new Label("Content:");
    private final TextArea documentContentTextArea = new TextArea();

    // ROW 3
    private final Label statusPrefixLabel = new Label("Status:");
    private final Label statusDisplayLabel = new Label("");

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        this.primaryStage = primaryStage;
        updateStatus(new EditingStatusMessage(getFilename(), TextProcessor.countWords(getContent())));

        filenameTextField.setPromptText("Unnamed Document");
        loadButton.setOnAction(new LoadButtonEventHandler());
        saveButton.setOnAction(new SaveButtonEventHandler());

        ToggleGroup radioGroup = new ToggleGroup();
        radioGroup.getToggles().add(textRadioButton);
        radioGroup.getToggles().add(binaryRadioButton);
        radioGroup.selectToggle(textRadioButton);

        HBox row1 = new HBox();
        HBox.setHgrow(filenameTextField, Priority.ALWAYS);
        row1.setSpacing(10);
        row1.setAlignment(Pos.BASELINE_CENTER);
        row1.getChildren().addAll(
                filenameLabel,
                filenameTextField,
                textRadioButton,
                binaryRadioButton,
                loadButton,
                saveButton
        );

        HBox documentTitleText = new HBox();
        documentTitleText.getChildren().addAll(
            documentTitleLabel
        );

        HBox documentTitleInput = new HBox();
        HBox.setHgrow(documentTitleTextField, Priority.ALWAYS);
        documentTitleInput.getChildren().addAll(
                documentTitleTextField
        );

        HBox documentContentText = new HBox();
        documentContentText.getChildren().addAll(
            documentContentLabel
        );

        HBox documentContentInput = new HBox();
        HBox.setHgrow(documentContentTextArea, Priority.ALWAYS);
        documentContentInput.getChildren().addAll(
                documentContentTextArea
        );

        VBox row2 = new VBox();
        VBox.setVgrow(documentContentInput, Priority.ALWAYS);
        row2.setSpacing(5);
        row2.getChildren().addAll(
                documentTitleText,
                documentTitleInput,
                documentContentText,
                documentContentInput
        );

        HBox row3 = new HBox();
        row3.setAlignment(Pos.BASELINE_CENTER);
        row3.setSpacing(1);
        row3.getChildren().addAll(
                statusPrefixLabel,
                statusDisplayLabel
        );

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);

        RowConstraints row1Constraints = new RowConstraints();
        row1Constraints.setVgrow(Priority.NEVER);

        RowConstraints row2Constraints = new RowConstraints();
        row2Constraints.setVgrow(Priority.ALWAYS);

        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(primaryStage.getMaxWidth());
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getRowConstraints().addAll(
                row1Constraints,
                row2Constraints
        );
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);

        gridPane.add(row1, 0, 0);
        gridPane.add(row2, 0, 1);
        gridPane.add(row3, 0, 2);

        Scene mainScene = new Scene(gridPane);
        mainScene.addEventFilter(KeyEvent.KEY_RELEASED, new KeyEventHandler());

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Editor");
        primaryStage.show();
    }

    public File showSaveDialogue(List<FileChooser.ExtensionFilter> filters) {
        FileChooser chooser = new FileChooser();

        chooser.setTitle("Save document");

        chooser.setInitialFileName(getFilename());

        if(filters != null) {
            chooser.getExtensionFilters().addAll(filters);
        }

        return chooser.showSaveDialog(primaryStage);
    }

    public File showOpenDialogue(List<FileChooser.ExtensionFilter> filters) {
        FileChooser chooser = new FileChooser();

        chooser.setTitle("Open document");

        if(filters != null) {
            chooser.getExtensionFilters().addAll(filters);
        }

        return chooser.showOpenDialog(primaryStage);
    }

    public void updateStatus(StatusMessage statusMessage) {
        statusDisplayLabel.setText(statusMessage.getMessage());
    }

    public String getFilename() {
        String filename = filenameTextField.getText();

        return filename.isBlank() ? "Unnamed Document" : filename;
    }

    public String getTitle() {
        return documentTitleTextField.getText();
    }

    public String getContent() {
        return documentContentTextArea.getText();
    }

    public Document getDocument() {
        return new Document(getFilename(), getTitle(), getContent());
    }

    public void loadDocument(Document document) {
        filenameTextField.setText(document.filename());
        documentTitleTextField.setText(document.title());
        documentContentTextArea.setText(document.content());
    }

    public DocumentType getDocumentType() {
        return textRadioButton.isSelected() ? DocumentType.TEXT : DocumentType.BINARY;
    }

    public static MainApplication getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }
}