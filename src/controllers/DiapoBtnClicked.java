package src.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import src.models.Model;
import src.views.View;

import java.util.Timer;
import java.util.TimerTask;

public class DiapoBtnClicked implements EventHandler<ActionEvent> {
    private final Model model;
    private final View view;
    public Timer timer;

    public DiapoBtnClicked(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        switch (((Button) actionEvent.getSource()).getText()) {
            case "First":
                model.setActualImage(model.animalImages.get(0));
                view.updateImage();
                break;
            case "Prev.":
                prev();
                break;
            case "Play":
                playLoop();
                break;
            case "Pause":
                stopLoop();
                break;
            case "Next":
                next();
                break;
            case "Last":
                model.setActualImage(model.animalImages.get(model.animalImages.size() - 1));
                view.updateImage();
                break;
            default:
                break;
        }
    }

    private void next() {
        model.setActualImage(model.animalImages.get((model.getActualImage().id + 1) % model.animalImages.size()));
        view.updateImage();
    }

    private void prev() {
        model.setActualImage(model.animalImages.get(Math.floorMod(model.getActualImage().id - 1, model.animalImages.size())));
        view.updateImage();
    }

    private void playLoop() {
        view.playDiapo.setText("Pause");
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                next();
            }
        }, 3000, 3000);
    }

    public void stopLoop() {
        timer.cancel();
        timer.purge();
        view.playDiapo.setText("Play");
    }
}
