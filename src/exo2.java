import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class exo2 extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Créer un champ de texte
        TextField textField = new TextField();
        textField.setPromptText("Entrez du texte ici");

        // Créer un label
        Label label = new Label("Le texte apparaîtra ici");

        // Ajouter un écouteur pour mettre à jour le label en temps réel
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(newValue);
        });

        // Créer une mise en page verticale
        VBox root = new VBox(10); // 10 pixels d'espacement
        root.setPadding(new Insets(15));
        root.getChildren().addAll(textField, label);

        // Configurer la scène
        Scene scene = new Scene(root, 300, 150);

        // Configurer la fenêtre principale
        primaryStage.setTitle("Exercice 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}