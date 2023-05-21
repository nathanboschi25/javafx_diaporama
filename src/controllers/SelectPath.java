package src.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import src.models.Model;
import src.views.View;

import java.io.File;

public class SelectPath implements EventHandler<ActionEvent> {
    private final Model model;
    private final View view;

    public SelectPath(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File path = directoryChooser.showDialog(view);

        model.setPathAndUpdateImages(path);

        view.listeAnimalsSelector.setItems(FXCollections.observableList(model.animalImages));

        model.setActualImage(model.animalImages.get(0));
        view.listeAnimalsSelector.getSelectionModel().select(0);
    }
}