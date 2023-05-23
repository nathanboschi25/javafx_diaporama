package src.views;

import javafx.scene.image.Image;


public class AnimalImage {
    public static int ImageIdCounter;

    public Image actualImage;
    public int id;
    String actualName;

    public AnimalImage(String path, String fileName) {
        this.actualImage = new Image(path);
        System.out.println(actualImage.getUrl());
        fileName = fileName.split("\\.")[0];
        this.actualName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
        id = ImageIdCounter;
        ImageIdCounter++;
    }

    public String toString() {
        return this.actualName;
    }
}
