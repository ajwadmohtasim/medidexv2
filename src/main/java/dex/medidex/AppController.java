package dex.medidex;

import Medicine.Medicine;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class AppController {
    @FXML
    private Label companyNameDetails;
    @FXML
    private Label doseDetails;
    @FXML
    private Label genericNameDetails;
    @FXML
    private Label indicationNameDetails;
    @FXML
    private Label mediNameDetails;
    @FXML
    private Label modeOfActionDetails;
    @FXML
    private Label sideEffectDetails;
    @FXML
    private Label typeNameDetails;
    @FXML
    private Label packsizeDetails;
    @FXML
    private Label warningDetails;

    @FXML
    private TableView<Medicine> mediTable;
    @FXML
    private TableColumn<Medicine, String> mediTableName;
    @FXML
    private TableColumn<Medicine, String> mediTableType;

    @FXML
    private ComboBox<String> sortByGenericOption;
    @FXML
    private ComboBox<String> sortByIndicationOption;
    @FXML
    private ComboBox<String> sortByTypeOption;

    @FXML
    private MenuItem menubarFileClose;

    @FXML
    private TextField searchbar;
    private ObservableList<Medicine> medicineList;

    @FXML
    public void initialize() {

        medicineList = Loader.loadMedicines("src/main/java/dex/medidex/medidex.csv");

        Loader.setupTableColumns(mediTableName, mediTableType);

        mediTable.setItems(medicineList);


        Loader.populateSort("src/main/java/dex/medidex/medidex.csv", sortByGenericOption, sortByIndicationOption, sortByTypeOption);



        mediTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateMedicineDetails(newValue);
            }
        });



        sortByGenericOption.setOnAction(event -> updateTable());
        sortByIndicationOption.setOnAction(event -> updateTable());
        sortByTypeOption.setOnAction(event -> updateTable());

        searchbar.textProperty().addListener((observable, oldValue, newValue) -> updateTable());

        menubarFileClose.setOnAction(event -> Platform.exit());

    }
    private void updateTable() {
        String selectedGeneric = sortByGenericOption.getSelectionModel().getSelectedItem();
        String selectedIndication = sortByIndicationOption.getSelectionModel().getSelectedItem();
        String selectedType = sortByTypeOption.getSelectionModel().getSelectedItem();

        String keyword = searchbar.getText();

        ObservableList<Medicine> filteredList = Loader.filterMedicines(medicineList, selectedGeneric, selectedIndication, selectedType, keyword);

        mediTable.setItems(filteredList);
    }

    private void updateMedicineDetails(Medicine selectedMedicine) {
        mediNameDetails.setText(selectedMedicine.getMedicineName());
        genericNameDetails.setText(selectedMedicine.getGenericName());
        indicationNameDetails.setText(selectedMedicine.getIndicationType());
        typeNameDetails.setText(selectedMedicine.getMedicineType());
        doseDetails.setText(selectedMedicine.getDose());
        sideEffectDetails.setText(selectedMedicine.getSideEffects());
        warningDetails.setText(selectedMedicine.getPrecautions());
        modeOfActionDetails.setText(selectedMedicine.getModeOfAction());
        companyNameDetails.setText(selectedMedicine.getCompanyName());
        packsizeDetails.setText(selectedMedicine.getPackSize());
    }
}
