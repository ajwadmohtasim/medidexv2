package dex.medidex;

import Medicine.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Loader {




    public static void setupTableColumns(TableColumn<Medicine, String> mediTableName,
                                         TableColumn<Medicine, String> mediTableType) {
        mediTableName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        mediTableType.setCellValueFactory(new PropertyValueFactory<>("medicineType"));
    }






    public static ObservableList<Medicine> loadMedicines(String filename) {

        ObservableList<Medicine> medicineList = FXCollections.observableArrayList();

        File file = new File(filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 10) {
                    medicineList.add(new Medicine(
                            parts[0], //indication name
                            parts[1], //generic name
                            parts[2], //dose
                            parts[3], //side effects
                            parts[4], //precautions
                            parts[5], //
                            parts[6],
                            parts[7],
                            parts[8],
                            parts[9]
                    ));
                } else {
                    System.err.println("Invalid row details : " + line);
                }
            }
            System.out.println("Medicines successfully loaded from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading medicines from CSV file: " + e.getMessage());
        }
        return medicineList;
    }

    public static void populateSort(String filename,
                                    ComboBox<String> sortByGeneric,
                                    ComboBox<String> sortByIndciation,
                                    ComboBox<String> sortBytype) {

        Set<String> genericSet = new HashSet<>();
        Set<String> indicationSet = new HashSet<>();
        Set<String> typeSet = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Skip the header row

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);

                if (parts.length == 10) {

                    genericSet.add(parts[3]);
                    indicationSet.add(parts[2]);
                    typeSet.add(parts[1]);

                }
            }
            sortByGeneric.getItems().clear();
            sortByIndciation.getItems().clear();
            sortBytype.getItems().clear();

            sortByGeneric.getItems().addFirst("None");
            sortByIndciation.getItems().addFirst("None");
            sortBytype.getItems().addFirst("None");

            sortByGeneric.getItems().addAll(genericSet);
            sortByIndciation.getItems().addAll(indicationSet);
            sortBytype.getItems().addAll(typeSet);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ObservableList<Medicine> filterMedicines(ObservableList<Medicine> medicineList,
                                                           String selectedGeneric,
                                                           String selectedIndication,
                                                           String selectedType,
                                                           String keyword) {

        return medicineList.filtered(medicine ->
                (selectedGeneric == null || selectedGeneric.isEmpty() || "None".equals(selectedGeneric) || medicine.getGenericName().equals(selectedGeneric)) &&
                        (selectedIndication == null || selectedIndication.isEmpty() || "None".equals(selectedIndication) || medicine.getIndicationType().equals(selectedIndication)) &&
                        (selectedType == null || selectedType.isEmpty() || "None".equals(selectedType) || medicine.getMedicineType().equals(selectedType)) &&
                        (keyword.isEmpty() || medicine.getMedicineName().toLowerCase().contains(keyword.toLowerCase()))
        );
    }
}
