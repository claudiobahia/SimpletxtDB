package sample;

public class Cliente {
    private String cellphoneNumber;
    private String userName;
    private int planType;// 0 pré-pago colcoar crédito pra usar // 1 pós-pago pacote minutos
    private int creditQuantity;

    public Cliente(String cellphoneNumber, String userName, int planType, int creditQuantity) {
        this.cellphoneNumber = cellphoneNumber;
        this.userName = userName;
        this.planType = planType;
        this.creditQuantity = creditQuantity;
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

    public int getPlanType() {
        return planType;
    }

    public void setPlanType(int planType) {
        this.planType = planType;
    }

    public int getCreditQuantity() {
        return creditQuantity;
    }

    public void setCreditQuantity(int creditQuantity) {
        this.creditQuantity = creditQuantity;
    }
}
