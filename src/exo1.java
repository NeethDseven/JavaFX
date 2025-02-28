import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class exo1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Cliquez ici");
        button.setOnAction(event -> System.out.println("Bonjour, JavaFX !"));

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Exercice 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}