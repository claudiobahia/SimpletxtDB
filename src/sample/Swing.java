package sample;

import javax.swing.*;

public class Swing {
    //Include menu vars
    private JTextField nameTxt;
    private JTextField numberTxt;
    private JTextField creditMinuteTxt;
    private JTextField[] allJTextFieldsVector;
    private Object[] planTypeButtons;
    private Object[] includeObjNameNumber;
    private Object[] creditObj;
    //Main menu var
    private Object[] mainMenu;
    //Report menu var
    private Object[] reportMenu;

    public Swing() {
        this.nameTxt = new JTextField();
        this.numberTxt = new JTextField();
        this.creditMinuteTxt = new JTextField();
        this.allJTextFieldsVector = new JTextField[]{getNameTxt(), getNumberTxt(), getCreditMinuteTxt()};
        this.planTypeButtons = new Object[]{"Pré-Pago", "Pós-Pago"};
        this.includeObjNameNumber = new Object[]{"Entre com o nome do cliente:", getNameTxt(), "Entre com o número telefonico:\nNúmeros apenas.", getNumberTxt()};
        this.creditObj = new Object[]{"Entre com os créditos:", getCreditMinuteTxt()};
        this.mainMenu = new Object[]{"Incluir", "Alterar", "Excluir", "Relatório", "Fazer ligação", "Sair"};
        this.reportMenu = new Object[]{"Listar clientes", "Listar créditos nulos ou negativos", "Cliente com maior crédito", "Gerar boletos para pós pagos.", "Voltar"};
    }

    public JTextField getNameTxt() {
        return nameTxt;
    }

    public JTextField getNumberTxt() {
        return numberTxt;
    }

    public JTextField getCreditMinuteTxt() {
        return creditMinuteTxt;
    }

    public void setCreditMinuteTxt(String creditMinuteTxt) {
        this.creditMinuteTxt.setText(creditMinuteTxt);
    }

    public JTextField[] getAllJTextFieldsVector() {
        return allJTextFieldsVector;
    }

    public Object[] getPlanTypeButtons() {
        return planTypeButtons;
    }

    public Object[] getIncludeObjNameNumber() {
        return includeObjNameNumber;
    }

    public Object[] getCreditObj() {
        return creditObj;
    }

    public Object[] getMainMenu() {
        return mainMenu;
    }

    public Object[] getReportMenu() {
        return reportMenu;
    }
}
