package src.views;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import src.controllers.ControlListChanged;
import src.controllers.DiapoBtnClicked;
import src.controllers.SelectPath;
import src.models.Model;

public class View extends Stage {
    private final Model model;
    public ListView<AnimalImage> listeAnimalsSelector;
    public Button playDiapo;
    Tab liste;
    Tab diaporama;
    private TabPane tabPane;
    private ImageView imageViewListe;
    private ImageView imageViewDiapo;
    private ButtonBar diapoButtons;
    private ProgressBar diapoPosition;
    private DiapoBtnClicked diapoController;
    private BorderPane mainPane;
    private Stage loadingWindow;


    public View(Model model) {
        super();
        this.model = model;
        initWidgets();
        this.setOnCloseRequest(windowEvent -> {
            if (this.playDiapo.getText() == "Pause")
                diapoController.stopLoop();
        });
    }

    private void initWidgets() {

        this.liste = new Tab("Liste");
        imageViewListe = new ImageView();
        listeAnimalsSelector = new ListView<>();

        this.diaporama = new Tab("Diaporama");
        imageViewDiapo = new ImageView();
        diapoButtons = new ButtonBar();

        tabPane = new TabPane(liste, diaporama);

        mainPane = new BorderPane();
        mainPane.setCenter(tabPane);
        mainPane.setTop(initMenuBar());
    }

    public void addWidgetsToScene() {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        initListTab();

        initDiapoTab();

    }

    private MenuBar initMenuBar() {
        Menu file = new Menu("Fichier");

        MenuItem file_source = new MenuItem("Selectioner la source des images");
        file_source.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));

        file_source.setOnAction(new SelectPath(model, this));

        MenuItem file_close = new MenuItem("Fermer");
        file_close.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));

        file_close.setOnAction(actionEvent -> this.close());

        file.getItems().addAll(file_source, new SeparatorMenuItem(), file_close);
        return new MenuBar(file);
    }

    private void initListTab() {
        SplitPane listeSplit = new SplitPane();
        listeSplit.setDividerPosition(0, 0.3);

        listeAnimalsSelector.setItems(FXCollections.observableList(model.animalImages));

        FlowPane listFlowPane = new FlowPane(imageViewListe);
        listFlowPane.setAlignment(Pos.CENTER);

        imageViewListe.setFitHeight(400);
        imageViewListe.setFitWidth(300);
        imageViewListe.setPreserveRatio(true);

        listeSplit.getItems().addAll(listeAnimalsSelector, listFlowPane);
        liste.setContent(listeSplit);
    }

    private void addListeners() {
        listeAnimalsSelector.getSelectionModel().selectedItemProperty().addListener(new ControlListChanged(model, this));
        listeAnimalsSelector.getSelectionModel().select(0);

        diapoController = new DiapoBtnClicked(model, this);
        for (Node btn : diapoButtons.getButtons()) {
            ((Button) btn).setOnAction(diapoController);
        }
    }

    private void initDiapoTab() {
        BorderPane diapoSplit = new BorderPane();

        Button lastImage = new Button("Last");

        Button nextImage = new Button("Next");

        playDiapo = new Button("Play");

        Button prevImage = new Button("Prev.");

        Button firstImage = new Button("First");


        diapoButtons.getButtons().addAll(firstImage, prevImage, playDiapo, nextImage, lastImage);

        diapoPosition = new ProgressBar();
        FlowPane diapoPositionProgress = new FlowPane(diapoPosition);
        diapoPositionProgress.setAlignment(Pos.CENTER);
        diapoPositionProgress.setPadding(new Insets(20));

        imageViewDiapo.setFitHeight(400);
        imageViewDiapo.setFitWidth(400);
        imageViewDiapo.setPreserveRatio(true);
        diapoSplit.setCenter(imageViewDiapo);


        FlowPane diapoBtns = new FlowPane(diapoButtons);
        diapoBtns.setAlignment(Pos.CENTER);
        diapoBtns.setVgap(10);
        diapoBtns.setPadding(new Insets(20));

        diapoSplit.setTop(diapoPositionProgress);
        diapoSplit.setBottom(diapoBtns);

        diaporama.setContent(diapoSplit);
    }

    public void display() {
        addWidgetsToScene();
        addListeners();
        Scene scene = new Scene(mainPane, 500, 600);

        this.setResizable(false);

        this.setScene(scene);
        this.show();
    }


    public void updateImage() {
        try {
            this.imageViewListe.setImage(model.getActualImage().animalImage);
            this.imageViewDiapo.setImage(model.getActualImage().animalImage);

            this.listeAnimalsSelector.getSelectionModel().select(model.getActualImage().id);
            this.diapoPosition.setProgress((double) model.getActualImage().id / (AnimalImage.ImageIdCounter - 1));
        } catch (NullPointerException ignored) {
        }
    }
}

