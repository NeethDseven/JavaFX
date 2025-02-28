import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class exo3 extends Application {
    private int counter = 0;

    @Override
    public void start(Stage primaryStage) {
        // Créer le label avec la valeur initiale
        Label numberLabel = new Label("0");
        numberLabel.setStyle("-fx-font-size: 24px;");

        // Créer les boutons + et -
        Button plusButton = new Button("+");
        plusButton.setOnAction(event -> {
            counter++;
            numberLabel.setText(String.valueOf(counter));
        });

        Button minusButton = new Button("-");
        minusButton.setOnAction(event -> {
            counter--;
            numberLabel.setText(String.valueOf(counter));
        });

        // Créer une disposition horizontale pour les boutons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(minusButton, plusButton);

        // Créer une disposition verticale pour tous les éléments
        VBox root = new VBox(20);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(numberLabel, buttonBox);

        // Configurer la scène
        Scene scene = new Scene(root, 250, 150);

        // Configurer la fenêtre principale
        primaryStage.setTitle("Exercice 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}