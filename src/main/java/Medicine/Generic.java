package Medicine;

public class Generic extends Indication {
    private String genericName;
    private String dose;
    private String sideEffects;
    private String precautions;
    private String modeOfAction;

    public Generic(String indicationType, String genericName, String dose, String sideEffects, String precautions, String modeOfAction) {
        super(indicationType);
        this.genericName = genericName;
        this.dose = dose;
        this.sideEffects = sideEffects;
        this.precautions = precautions;
        this.modeOfAction = modeOfAction;
    }

    public String getGenericName() {
        return genericName;
    }

    public String getDose() {
        return dose;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public String getPrecautions() {
        return precautions;
    }

    public String getModeOfAction() {
        return modeOfAction;
    }
}
