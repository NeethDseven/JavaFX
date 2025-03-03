import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;

public class exo10 {
    // Home tab components
    @FXML private Button refreshButton;

    // Profile tab components
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextArea aboutField;
    @FXML private Button saveProfileButton;

    // Settings tab components
    @FXML private CheckBox darkModeCheck;
    @FXML private Slider fontSizeSlider;
    @FXML private CheckBox emailNotifCheck;
    @FXML private CheckBox pushNotifCheck;
    @FXML private RadioButton frenchRadio;
    @FXML private RadioButton englishRadio;
    @FXML private RadioButton spanishRadio;
    @FXML private ToggleGroup languageGroup;
    @FXML private Button resetButton;
    @FXML private Button applyButton;

    @FXML
    public void initialize() {
        // Initialize with default values or load saved preferences
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        showInfo("Actualisation", "Page d'accueil actualisée");
    }

    @FXML
    private void handleSaveProfile(ActionEvent event) {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
            showError("Erreur de saisie", "Le nom et l'email sont requis.");
            return;
        }
        showInfo("Profil enregistré", "Vos informations personnelles ont été enregistrées avec succès.");
    }

    @FXML
    private void handleReset(ActionEvent event) {
        darkModeCheck.setSelected(false);
        fontSizeSlider.setValue(12);
        emailNotifCheck.setSelected(true);
        pushNotifCheck.setSelected(true);
        frenchRadio.setSelected(true);
        showInfo("Réinitialisation", "Paramètres réinitialisés aux valeurs par défaut.");
    }

    @FXML
    private void handleApply(ActionEvent event) {
        String language = frenchRadio.isSelected() ? "Français" :
                         (englishRadio.isSelected() ? "English" : "Español");

        showInfo("Paramètres appliqués",
                "Mode sombre: " + (darkModeCheck.isSelected() ? "Activé" : "Désactivé") + "\n" +
                "Taille de police: " + (int)fontSizeSlider.getValue() + "\n" +
                "Langue: " + language);
    }

    private void showInfo(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}