import javafx.animation.KeyFrame;
                    import javafx.animation.Timeline;
                    import javafx.event.ActionEvent;
                    import javafx.fxml.FXML;
                    import javafx.scene.control.Button;
                    import javafx.scene.control.Label;
                    import javafx.scene.control.ProgressBar;
                    import javafx.scene.control.RadioButton;
                    import javafx.util.Duration;

                    public class exo9 {

                        @FXML
                        private ProgressBar progressBar;

                        @FXML
                        private Button loadButton;

                        @FXML
                        private RadioButton manualOption;

                        @FXML
                        private RadioButton timelineOption;

                        @FXML
                        private Label statusLabel;

                        private Timeline timeline;
                        private double progress = 0.0;

                        @FXML
                        private void handleLoad(ActionEvent event) {
                            if (manualOption.isSelected()) {
                                // Manual mode: increase progress by 10% each click
                                progress += 0.1;
                                if (progress > 1.0) {
                                    progress = 0.0;
                                    statusLabel.setText("Chargement terminé! Réinitialisé.");
                                } else {
                                    statusLabel.setText(String.format("Progression: %.0f%%", progress * 100));
                                }
                                progressBar.setProgress(progress);
                            } else {
                                // Timeline mode: simulate a download
                                if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
                                    // If timeline is already running, do nothing
                                    return;
                                }

                                // Reset progress
                                progress = 0.0;
                                progressBar.setProgress(progress);
                                statusLabel.setText("Téléchargement en cours...");

                                // Disable button during simulation
                                loadButton.setDisable(true);

                                // Create and start timeline
                                timeline = new Timeline();
                                timeline.setCycleCount(100);
                                timeline.getKeyFrames().add(
                                    new KeyFrame(Duration.millis(50), e -> {
                                        progress += 0.01;
                                        progressBar.setProgress(progress);

                                        if (progress >= 1.0) {
                                            timeline.stop();
                                            loadButton.setDisable(false);
                                            statusLabel.setText("Téléchargement terminé!");
                                        }
                                    })
                                );
                                timeline.play();
                            }
                        }
                    }