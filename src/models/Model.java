package src.models;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import src.Diapo;
import src.views.AnimalImage;

import java.io.File;
import java.util.ArrayList;


public class Model {
    public ArrayList<AnimalImage> animalImages;
    private AnimalImage actualImage;
    private String imagePath;

    public Model() {
        this.animalImages = new ArrayList<>();
        imagePath = Diapo.PROJECT_PATH + "\\views\\animalImages\\";
        getAllImages();
        actualImage = animalImages.get(0);
    }

    private void getAllImages() {
        System.out.println("Recuperation des images dans " + imagePath);
        File folder = new File(imagePath);
        for (File fileEntry : folder.listFiles()) {
            animalImages.add(new AnimalImage(imagePath + fileEntry.getName(), fileEntry.getName()));
        }
    }

    public AnimalImage getActualImage() {
        return this.actualImage;
    }

    public void setActualImage(AnimalImage actual) {
        actualImage = actual;
    }

    public void setPathAndUpdateImages(File file) {
        if (file != null) {
            this.imagePath = file.getAbsolutePath() + '\\';
            animalImages = new ArrayList<>();
            AnimalImage.ImageIdCounter = 0;
            getAllImages();
        }
    }
}
