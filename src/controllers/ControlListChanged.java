package src.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import src.models.Model;
import src.views.AnimalImage;
import src.views.View;

public class ControlListChanged implements ChangeListener<AnimalImage> {
    private Model model;
    private View view;

    public ControlListChanged(Model model, View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void changed(ObservableValue<? extends AnimalImage> observableValue, AnimalImage old_image, AnimalImage new_image) {
        System.out.println("Image changed from " + old_image + " to " + new_image);
        model.setActualImage(observableValue.getValue());
        view.updateImage();
    }
}
