import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class exo7 implements Initializable {
    @FXML
    private ListView<Image> imageListView;

    @FXML
    private ImageView displayImageView;

    @FXML
    private Pane dropPane;

    private ObservableList<Image> images = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configure ListView to display thumbnail images
        imageListView.setCellFactory(param -> new ImageListCell());
        imageListView.setItems(images);

        // Handle selection in ListView
        imageListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayImageView.setImage(newValue);
            }
        });

        // Set up drag and drop for the dropPane
        dropPane.setOnDragOver(this::handleDragOver);
        dropPane.setOnDragDropped(this::handleDragDropped);
    }

    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasFiles()) {
            success = true;
            for (File file : db.getFiles()) {
                try {
                    String imageUrl = file.toURI().toURL().toString();
                    Image image = new Image(imageUrl, true);
                    images.add(image);

                    // Select the newly added image
                    imageListView.getSelectionModel().select(image);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

        event.setDropCompleted(success);
        event.consume();
    }

    // Custom cell for displaying thumbnails in the ListView
    private static class ImageListCell extends ListCell<Image> {
        private final ImageView imageView;

        public ImageListCell() {
            imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            imageView.setPreserveRatio(true);
            setGraphic(imageView);
        }

        @Override
        protected void updateItem(Image item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                imageView.setImage(null);
            } else {
                imageView.setImage(item);
            }
        }
    }
}