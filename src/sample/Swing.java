package sample;

import javax.swing.*;

public class Swing {
    private JTextField nameTxt = new JTextField();
    private JTextField numberTxt = new JTextField();
    private JTextField creditMinuteTxt = new JTextField();
    private JTextField[] allJTextFieldsVector = {nameTxt, numberTxt, creditMinuteTxt};
    private Object[] planTypeButtons = {"Pré-Pago", "Pós-Pago"};
    private Object[] includeObjNameNumber = {"Entre com o nome do cliente:", nameTxt,
            "Entre com o número telefonico:\nNúmeros apenas.", numberTxt};
    private Object[] creditObj = {"Entre com os créditos:", creditMinuteTxt};
    private Object[] minutesObj = {"Entre com os segundos usados:", creditMinuteTxt};
    private Object[] mainMenu = {"Incluir", "Alterar", "Excluir", "Relatório", "Sair"};

    public Swing() {
    }

    public JTextField getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(JTextField nameTxt) {
        this.nameTxt = nameTxt;
    }

    public JTextField getNumberTxt() {
        return numberTxt;
    }

    public void setNumberTxt(JTextField numberTxt) {
        this.numberTxt = numberTxt;
    }

    public JTextField getCreditMinuteTxt() {
        return creditMinuteTxt;
    }

    public void setCreditMinuteTxt(JTextField creditMinuteTxt) {
        this.creditMinuteTxt = creditMinuteTxt;
    }

    public JTextField[] getAllJTextFieldsVector() {
        return allJTextFieldsVector;
    }

    public void setAllJTextFieldsVector(JTextField[] allJTextFieldsVector) {
        this.allJTextFieldsVector = allJTextFieldsVector;
    }

    public Object[] getPlanTypeButtons() {
        return planTypeButtons;
    }

    public void setPlanTypeButtons(Object[] planTypeButtons) {
        this.planTypeButtons = planTypeButtons;
    }

    public Object[] getIncludeObjNameNumber() {
        return includeObjNameNumber;
    }

    public void setIncludeObjNameNumber(Object[] includeObjNameNumber) {
        this.includeObjNameNumber = includeObjNameNumber;
    }

    public Object[] getCreditObj() {
        return creditObj;
    }

    public void setCreditObj(Object[] creditObj) {
        this.creditObj = creditObj;
    }

    public Object[] getMinutesObj() {
        return minutesObj;
    }

    public void setMinutesObj(Object[] minutesObj) {
        this.minutesObj = minutesObj;
    }

    public Object[] getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(Object[] mainMenu) {
        this.mainMenu = mainMenu;
    }
}
