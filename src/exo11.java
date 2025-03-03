import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class exo11 {
    @FXML private Label scoreLabel;
    @FXML private Label timerLabel;
    @FXML private Button startButton;
    @FXML private Button clickButton;
    @FXML private Pane gamePane;

    private int score = 0;
    private int timeLeft = 30;
    private Random random = new Random();
    private Timeline gameTimeline;
    private Timeline buttonTimeline;

    @FXML
    private void initialize() {
        // Initial setup
        scoreLabel.setText("0");
        timerLabel.setText("30");
        clickButton.setVisible(false);

        // Set initial size for the button to ensure it's properly positioned
        clickButton.setPrefWidth(80);
        clickButton.setPrefHeight(30);
    }

    @FXML
    private void startGame(ActionEvent event) {
        // Reset game state
        score = 0;
        timeLeft = 30;
        scoreLabel.setText("0");
        timerLabel.setText("30");
        startButton.setDisable(true);

        // Show the button and position it randomly
        clickButton.setVisible(true);
        moveButtonRandomly();

        // Start the game timer
        gameTimeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                timeLeft--;
                timerLabel.setText(Integer.toString(timeLeft));

                if (timeLeft <= 0) {
                    endGame();
                }
            })
        );
        gameTimeline.setCycleCount(30);
        gameTimeline.play();

        // Start the button movement timeline with smoother transitions
        buttonTimeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                // Ensure the button is visible before moving it
                clickButton.setVisible(true);
                moveButtonRandomly();
            })
        );
        buttonTimeline.setCycleCount(Timeline.INDEFINITE);
        buttonTimeline.play();
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        score++;
        scoreLabel.setText(Integer.toString(score));
    }

    private void moveButtonRandomly() {
        // Calculate max positions while keeping button within bounds
        double maxX = gamePane.getWidth() - clickButton.getWidth();
        double maxY = gamePane.getHeight() - clickButton.getHeight();

        // Ensure positive values (in case pane size is not yet initialized)
        maxX = maxX > 0 ? maxX : gamePane.getWidth() - 100;
        maxY = maxY > 0 ? maxY : gamePane.getHeight() - 50;

        // Set random position
        double newX = random.nextDouble() * maxX;
        double newY = random.nextDouble() * maxY;

        // Ensure the button remains visible
        clickButton.setVisible(true);
        clickButton.setLayoutX(newX);
        clickButton.setLayoutY(newY);
    }

    private void endGame() {
        // Stop timelines
        if (gameTimeline != null) {
            gameTimeline.stop();
        }
        if (buttonTimeline != null) {
            buttonTimeline.stop();
        }

        // Hide button and enable start button
        clickButton.setVisible(false);
        startButton.setDisable(false);

        // Show final score
        timerLabel.setText("0");
    }
}