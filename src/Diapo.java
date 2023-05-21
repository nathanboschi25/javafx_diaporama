package src;

import javafx.application.Application;
import javafx.stage.Stage;
import src.models.Model;
import src.views.View;

public class Diapo extends Application{

    public static final String PROJECT_PATH = "C:\\Users\\natha\\Documents\\IUT\\SEMESTRE_2\\IHM\\JavaFX_TP\\tp4_diapo\\src";

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        View view = new View(model);
        view.display();
    }
}