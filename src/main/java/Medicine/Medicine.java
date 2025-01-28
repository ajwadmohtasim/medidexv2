package Medicine;

public class Medicine extends Generic {
    private String medicineName;
    private String medicineType;
    private String companyName;
    private String packSize;

    public Medicine(String medicineName, String medicineType, String indicationType, String genericName,
                    String dose, String sideEffects, String precautions, String modeOfAction, String companyName, String packSize) {
        super(indicationType, genericName, dose, sideEffects, precautions, modeOfAction);
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.companyName = companyName;
        this.packSize = packSize;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPackSize() {
        return packSize;
    }
}
