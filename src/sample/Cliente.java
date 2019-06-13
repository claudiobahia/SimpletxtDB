package sample;

public class Cliente {
    private String cellphoneNumber;
    private String userName;
    private int planType;//0 prepago
    private int creditQuantityOrSeconds;


    public Cliente(String cellphoneNumber, String userName, int creditQuantityOrSeconds, int planType) {
        this.cellphoneNumber = cellphoneNumber;
        this.userName = userName;
        this.creditQuantityOrSeconds = creditQuantityOrSeconds;
        this.planType = planType;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
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

    public int getCreditQuantityOrSeconds() {
        return creditQuantityOrSeconds;
    }

    public void setCreditQuantityOrSeconds(int creditQuantityOrSeconds) {
        this.creditQuantityOrSeconds = creditQuantityOrSeconds;
    }

    @Override
    public String toString() {
        String plano = (getPlanType() == 0) ? "Pré-pago" : "Pós-pago";
        String creditMinute = (plano.equals("Pré-pago")) ? "Créditos: " + getCreditQuantityOrSeconds() :
                "Minutos utilizados: " + getCreditQuantityOrSeconds();
        return "Nome: " + getUserName() + "\n" +
                "Número: " + getCellphoneNumber() + "\n" +
                "Plano: " + plano + "\n" + creditMinute;
    }
}