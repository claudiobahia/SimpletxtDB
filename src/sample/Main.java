package sample;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DAOcliente daoCliente = new DAOcliente();
        DAOligacao daoLigacao = new DAOligacao();
        Swing swing = new Swing();

        ArrayList<Ligacoes> ligacoesArrayList = new ArrayList<>();
        ArrayList<Cliente> clienteArrayList = new ArrayList<>();

        ligacoesArrayList = daoLigacao.readFileLigacao(ligacoesArrayList);
        clienteArrayList = daoCliente.readFileCliente(clienteArrayList);

        boolean sair = false;
        do {
            clearSwing(swing.getAllJTextFieldsVector());
            int choosedOption = showOptionMenu(swing.getMainMenu());
            switch (choosedOption) {
                case 0://Include user info
                    includeMethod(swing, clienteArrayList);
                    break;
                case 1://Change user info
                    changeMethod(swing, clienteArrayList);
                    break;
                case 2://Exclude user info
                    excludeMethod(clienteArrayList);
                    break;
                case 3://Report info from user
                    reportMethod(swing, clienteArrayList, ligacoesArrayList);
                    break;
                case 4://Make a call
                case -1://Exit X
                    sair = true;
            }
        } while (!sair);
        daoCliente.writeTxtCliente(clienteArrayList);
    }

    private static Cliente returnEqualCliente(ArrayList<Cliente> clienteArrayList, String numberToCheck) {
        Cliente user = null;
        boolean found = returnEqualClienteBoolean(clienteArrayList, numberToCheck);
        if (found) {
            for (Cliente cliente : clienteArrayList) {
                if (cliente.getCellphoneNumber().equals(numberToCheck)) {
                    user = cliente;
                }
            }
        }
        return user;
    }

    private static boolean returnEqualClienteBoolean(ArrayList<Cliente> clienteArrayList, String numberToCheck) {
        for (Cliente cliente : clienteArrayList) {
            if (cliente.getCellphoneNumber().equals(numberToCheck)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validaTamNumero(String numero) {
        return numero.length() == 8;
    }

    private static void includeMethod(Swing swing, ArrayList<Cliente> clienteArrayList) {
        int planType;
        showInputMenu(swing.getIncludeObjNameNumber());
        String numero = swing.getNumberTxt().getText();
        boolean achouCliente = returnEqualClienteBoolean(clienteArrayList, numero);
        if (!validaTamNumero(numero)) {
            showMessage("Número telefonico inválido.\n" + "Entre apenas com 8 números sem simbolos.");
            return;
        }
        if (achouCliente) {
            showMessage("Número telefonico já existente!");
            return;
        }
        if (!tryParse(numero)) {
            showMessage("Número inválido.");
            return;
        }
        planType = showOptionMenu(swing.getPlanTypeButtons());
        if (isPrePago(planType)) {//prepago
            showInputMenu(swing.getCreditObj());
            String credito = swing.getCreditMinuteTxt().getText();
            if (!tryParse(credito)) {
                showMessage("Créditos inválidos.");
                return;
            }
            if (!isCreditoValido(credito)) {
                showMessage("Créditos negativos, inválido.");
                return;
            }
        } else {
            swing.setCreditMinuteTxt("0");
        }
        addToClienteArray(clienteArrayList, swing.getNumberTxt().getText(), swing.getNameTxt().getText(), Integer.parseInt(swing.getCreditMinuteTxt().getText()), planType);
    }

    private static boolean isPrePago(int planType) {
        return planType == 0;
    }

    private static void changeMethod(Swing swing, ArrayList<Cliente> clienteArrayList) {
        String allUserString = listAllUsers(clienteArrayList);
        if (allUserString.equals(isListAllUserEmpty())) {
            showMessage("Não há clientes cadastrados.");

        }
        String numberToCheck = showSimpleInput(allUserString +
                "\n\nEntre com o número que deseja alterar os dados.\nSomente a numeração");
        if (numberToCheck == null) {
            return;
        }
        Cliente cliente = returnEqualCliente(clienteArrayList, numberToCheck);
        if (!returnEqualClienteBoolean(clienteArrayList, numberToCheck)) {
            showMessage("Não há cliente cadastrado com esse número.");
            return;
        }
        cliente.setUserName(showSimpleInput("Entre com o novo nome do cliente:"));
        int novoPlano = showOptionMenu(swing.getPlanTypeButtons());
        cliente.setPlanType(novoPlano);
        String credito;
        if (isPrePago(novoPlano)) {//prepago
            credito = showSimpleInput("Usando apenas números e positivos,\nEntre com o novo crédito: ");
            if (!isCreditoValido(credito)) {
                showMessage("Créditos inválidos");
                return;
            }
            cliente.setCreditQuantityOrSeconds(Integer.parseInt(credito));
        } else {
            credito = showSimpleInput("Usando apenas números e positivos,\nEntre com o novo crédito: ");
            if (!isCreditoValido(credito)) {
                showMessage("Créditos inválidos");
                return;
            }
            cliente.setCreditQuantityOrSeconds(Integer.parseInt(credito));
        }
    }

    private static void excludeMethod(ArrayList<Cliente> clienteArrayList) {
        String allUserString = listAllUsers(clienteArrayList);
        if (allUserString.equals(isListAllUserEmpty())) {
            showMessage("Não há clientes cadastrados.");
            return;
        }
        String numberToCheck = showSimpleInput(allUserString +
                "\n\nEntre com o número que deseja excluir os dados.\nSomente a numeração");
        if (numberToCheck == null) {
            return;
        }
        if (!returnEqualClienteBoolean(clienteArrayList, numberToCheck)) {
            showMessage("Não há cliente cadastrado com esse número.");
            return;
        }
        Cliente cliente = returnEqualCliente(clienteArrayList, numberToCheck);
        clienteArrayList.remove(cliente);
    }

    private static void reportMethod(Swing swing, ArrayList<Cliente> clienteArrayList, ArrayList<Ligacoes> ligacoesArrayList) {
        boolean sair = false;
        do {
            int choosedOption = showOptionMenu(swing.getReportMenu());
            switch (choosedOption) {
                case 0: //List users
                    showMessage(listAllUsers(clienteArrayList));
                    break;
                case 1: // List null or negative credits
                    listNegativeNullCredits(clienteArrayList);
                    break;
                case 2: // Biggest credit user
                    listMostCredit(clienteArrayList);
                    break;
                case 3: // Generate billets
                    generateBills(clienteArrayList, ligacoesArrayList);
                    break;
                case 4:
                    sair = true;
            }
        } while (!sair);
    }

    private static void generateBills(ArrayList<Cliente> clienteArrayList, ArrayList<Ligacoes> ligacoesArrayList) {

        StringBuilder s = new StringBuilder();
        for (Cliente cliente : clienteArrayList) {
            for (Ligacoes ligacoes : ligacoesArrayList) {
                if (ligacoes.getNumero().equals(cliente.getCellphoneNumber())) {
                    s.append("Nome: ").append(cliente.getUserName()).append("\n").append(ligacoes.toString());
                    if (isPrePago(cliente.getPlanType())) {
                        s.append("Plano Pré Pago\n").append(ligacoes.getValorPrePago(cliente.getCreditQuantityOrSeconds() + ""));
                        cliente.setCreditQuantityOrSeconds(cliente.getCreditQuantityOrSeconds() - Integer.parseInt(Long.toString(ligacoes.getMinuteFromDateDiference())));
                    } else {
                        s.append("Plano Pós Pago\n").append(ligacoes.getValorPosPago(cliente.getCreditQuantityOrSeconds() + ""));
                        cliente.setCreditQuantityOrSeconds(cliente.getCreditQuantityOrSeconds() + Integer.parseInt(Long.toString(ligacoes.getMinuteFromDateDiference())));
                    }
                    s.append("\n--------------------------------------------------------------------\n");
                }
            }
        }
        showMessage(s.toString());
    }

    private static void listMostCredit(ArrayList<Cliente> clienteArrayList) {
        String string = "Não há cliente pré pago cadastrado.\n";
        int lastCredito = 0;
        for (Cliente cliente : clienteArrayList) {
            int newCredito = cliente.getCreditQuantityOrSeconds();
            if (isPrePago(cliente.getPlanType())
                    && newCredito >= lastCredito) {
                string = "Cliente com maior crédito\n\n" + cliente.toString();
                lastCredito = newCredito;
            }
        }
        if (lastCredito > 0) {
            showMessage(string);
        } else showMessage("Não há cliente com crédito positivo.");
    }

    private static void listNegativeNullCredits(ArrayList<Cliente> clienteArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        String msg = "Clientes sem créditos ou negativado.\n\n";
        stringBuilder.append(msg);
        for (Cliente cliente : clienteArrayList) {
            if (isPrePago(cliente.getPlanType())
                    && cliente.getCreditQuantityOrSeconds() <= 0)
                stringBuilder.append(cliente.toString()).append("\n-----------------------------------------------\n");
        }
        if (!stringBuilder.toString().equals(msg)) {
            showMessage(stringBuilder.toString());
        } else showMessage("Não há clientes sem créditos ou negativado.");
    }

    private static String isListAllUserEmpty() {
        return "NÃO HÁ CLIENTES CADASTRADOS.";
    }

    private static String listAllUsers(ArrayList<Cliente> clienteArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Cliente cliente : clienteArrayList) {
            stringBuilder.append(cliente.toString()).append("\n-----------------------------------------------\n");
        }
        if (!stringBuilder.toString().equals("")) {
            return (stringBuilder.toString());
        } else return isListAllUserEmpty();
    }

    private static void addToClienteArray(ArrayList<Cliente> clienteArrayList, String numero, String nome, int creditoOUminuto, int plano) {
        clienteArrayList.add(new Cliente(numero, nome, creditoOUminuto, plano));
    }

    private static void clearSwing(JTextField[] field) {
        for (int i = 0; i < field.length; i++) {
            field[1].setText("");
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

    private static boolean isCreditoValido(String credito) {
        return Integer.parseInt(credito) >= 0;
    }

    private static void showMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    private static String showSimpleInput(String s) {
        return JOptionPane.showInputDialog(s);
    }

    private static void showInputMenu(Object[] objects) {
        JOptionPane.showConfirmDialog(null, objects, "Preencha os campos", JOptionPane.DEFAULT_OPTION);
    }

    private static int showOptionMenu(Object[] objects) {
        return JOptionPane.showOptionDialog(null, "Escolha uma opção: ", "Opções",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, objects, null);
    }
}
