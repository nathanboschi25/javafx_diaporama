package src.views;

import javafx.scene.image.Image;
import src.Diapo;


public class AnimalImage {
    String animalName;
    public Image animalImage;
    public static int ImageIdCounter;
    public int id;

    public AnimalImage(String path, String fileName) {
        this.animalImage = new Image(path);
        System.out.println(animalImage.getUrl());
        fileName = fileName.split("\\.")[0];
        this.animalName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
        id = ImageIdCounter;
        ImageIdCounter++;
    }

    public String toString() {
        return this.animalName;
    }
}
