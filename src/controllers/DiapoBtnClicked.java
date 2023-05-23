package src.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
        Button source = (Button) actionEvent.getSource();

        if (source == view.firstImageBtn) {
            model.setActualImage(model.animalImages.get(0));
            view.updateImage();

        } else if (source == view.prevImageBtn) {
            prev();

        } else if (source == view.playDiapo && view.playDiapo.getText() == "Play") {
            playLoop();

        } else if (source == view.playDiapo && view.playDiapo.getText() == "Pause") {
            stopLoop();

        } else if (source == view.nextImageBtn) {
            next();

        } else if (source == view.lastImageBtn) {
            model.setActualImage(model.animalImages.get(model.animalImages.size() - 1));
            view.updateImage();

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
