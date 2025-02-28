import javafx.collections.FXCollections;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.Label;

        import java.net.URL;
        import java.util.ResourceBundle;

        public class exo5 implements Initializable {
            @FXML
            private ComboBox<String> cityComboBox;

            @FXML
            private Label selectedCityLabel;

            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {
                // Ajouter les villes à la ComboBox
                cityComboBox.setItems(FXCollections.observableArrayList(
                        "Paris", "Lyon", "Marseille", "Toulouse", "Nice", "Bordeaux", "Lille"
                ));

                // Gérer la sélection
                cityComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedCityLabel.setText(newValue);
                    }
                });
            }

        }