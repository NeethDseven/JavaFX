import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class exo4 extends Application {
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        // Create the buttons
        Button redButton = new Button("Rouge");
        Button greenButton = new Button("Vert");
        Button blueButton = new Button("Bleu");

        // Set up event handlers
        redButton.setOnAction(event -> scene.getRoot().setStyle("-fx-background-color: red;"));
        greenButton.setOnAction(event -> scene.getRoot().setStyle("-fx-background-color: green;"));
        blueButton.setOnAction(event -> scene.getRoot().setStyle("-fx-background-color: blue;"));

        // Create layout and add buttons
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(redButton, greenButton, blueButton);

        // Create the scene
        scene = new Scene(root, 300, 200);

        // Set up the stage
        primaryStage.setTitle("Exercice 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}