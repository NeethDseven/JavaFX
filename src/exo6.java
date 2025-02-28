import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class exo6 implements Initializable {

    @FXML
    private TableView<Person> personTableView;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, Integer> ageColumn;

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the table columns
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        // Add sample data
        personList.add(new Person("Dupont", "Jean", 42));
        personList.add(new Person("Martin", "Sophie", 35));
        personList.add(new Person("Bernard", "Michel", 28));

        // Connect data to the TableView
        personTableView.setItems(personList);
    }

    @FXML
    private void handleAddPerson() {
        // Create dialog for person information
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle("Ajouter une personne");
        dialog.setHeaderText("Saisissez les informations de la personne");

        ButtonType addButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Nom");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Prénom");
        TextField ageField = new TextField();
        ageField.setPromptText("Âge");

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(lastNameField, 1, 0);
        grid.add(new Label("Prénom:"), 0, 1);
        grid.add(firstNameField, 1, 1);
        grid.add(new Label("Âge:"), 0, 2);
        grid.add(ageField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // Process the result
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    int age = Integer.parseInt(ageField.getText());
                    return new Person(lastNameField.getText(), firstNameField.getText(), age);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText("L'âge doit être un nombre entier.");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        Optional<Person> result = dialog.showAndWait();
        result.ifPresent(person -> personList.add(person));
    }

    @FXML
    private void handleDeletePerson() {
        Person selectedPerson = personTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            personList.remove(selectedPerson);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucune sélection");
            alert.setContentText("Veuillez sélectionner une personne à supprimer.");
            alert.showAndWait();
        }
    }
}