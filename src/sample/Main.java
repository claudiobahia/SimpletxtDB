package sample;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DAO dao = new DAO();
        Swing swing = new Swing();
        ArrayList<Cliente> clienteArrayList = new ArrayList<>();
        //noinspection unchecked //THIS STATMENT WARNING WAS PISSING ME OFF LOL NEEDED TO SUPPRESS
        clienteArrayList = dao.readFile(clienteArrayList);

        boolean sair = false;
        do {
            int choosedOption = showOptionMenu(swing.getMainMenu());
            switch (choosedOption) {
                case 0://Include user info
                    includeMethod(swing.getIncludeObjNameNumber(), swing.getNumberTxt(), swing.getNameTxt(),
                            clienteArrayList, swing.getCreditObj(), swing.getMinutesObj(), swing.getPlanTypeButtons(),
                            swing.getAllJTextFieldsVector(), swing.getCreditMinuteTxt());
                    break;
                case 1://Change user info
                    break;
                case 2://Exclude user info
                    break;
                case 3://Report info from user
                    break;
                case 4://Exit
                    dao.writeTxt(clienteArrayList);
                    sair = true;
            }

        } while (!sair);

    }

    private static void includeMethod(Object[] includeObjNameNumber, JTextField numberTxt,
                                      JTextField nameTxt, ArrayList<Cliente> clienteArrayList,
                                      Object[] creditObj, Object[] minutesObj,
                                      Object[] planTypeButtons, JTextField[] allJTextFieldsVector,
                                      JTextField creditMinuteTxt) {
        int planType;
        boolean ok = false;
        do {
            showInputMenu(includeObjNameNumber);
            if (numberTxt.getText().length() == 8 && tryParse(numberTxt.getText())) {
                ok = true;
            } else showMessage("Número telefonico inválido.\n" +
                    "Entre apenas com 8 números sem simbolos.");
        } while (!ok);
        planType = showOptionMenu(planTypeButtons);
        if (planType == 0) {//prepago
            showInputMenu(creditObj);
        } else {
            showInputMenu(minutesObj);
        }
        addToClienteArray(clienteArrayList, numberTxt.getText(), nameTxt.getText(),
                Integer.parseInt(creditMinuteTxt.getText()), planType);
        clearSwing(allJTextFieldsVector);
    }

    private static void addToClienteArray(ArrayList<Cliente> clienteArrayList, String numero,
                                          String nome, int creditoOUminuto, int plano) {
        clienteArrayList.add(new Cliente(numero, nome, creditoOUminuto, plano));
    }

    private static void clearSwing(JTextField[] field) {
        for (int i = 0; i < field.length; i++) {
            field[1].setText(null);
        }
    }

    private static boolean tryParse(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void showMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    private static int showInputMenu(Object[] objects) {
        return JOptionPane.showConfirmDialog(null, objects, "Preencha os campos", JOptionPane.DEFAULT_OPTION);
    }

    private static int showOptionMenu(Object[] objects) {
        return JOptionPane.showOptionDialog(null, "Escolha uma opção", "Opções",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, objects, null);
    }
}
