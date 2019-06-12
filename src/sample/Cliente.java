package sample;

public class Cliente {
    private String cellphoneNumber;
    private String userName;
    private int planType;//0 prepago
    private int creditQuantityOrSeconds;//prepago

    public Cliente(String cellphoneNumber, String userName, int creditQuantityOrSeconds, int planType) {
        this.cellphoneNumber = cellphoneNumber;
        this.userName = userName;
        this.creditQuantityOrSeconds = creditQuantityOrSeconds;
        this.planType = planType;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCreditQuantityOrSeconds() {
        return creditQuantityOrSeconds;
    }

    public void setCreditQuantityOrSeconds(int creditQuantity) {
        this.creditQuantityOrSeconds = creditQuantity;
    }

    public int getPlanType() {
        return planType;
    }

    public void setPlanType(int planType) {
        this.planType = planType;
    }
}