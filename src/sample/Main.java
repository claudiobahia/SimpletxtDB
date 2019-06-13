package sample;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DAO dao = new DAO();
        Ligacoes ligacoes = new Ligacoes();
        Data data = new Data();
        Swing swing = new Swing();

        ArrayList<Ligacoes> ligacoesArrayList = new ArrayList<>();
        ligacoesArrayList = ligacoes.readFile(ligacoesArrayList);
        ArrayList<Cliente> clienteArrayList = new ArrayList<>();
        clienteArrayList = dao.readFile(clienteArrayList);

        boolean sair = false;
        do {
            clearSwing(swing.getAllJTextFieldsVector());
            int choosedOption = showOptionMenu(swing.getMainMenu());
            dao.writeTxt(clienteArrayList);
            ligacoes.writeTxt(ligacoesArrayList);
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
                    reportMethod(swing, clienteArrayList);
                    break;
                case 4://Make a call
                    makeCall(ligacoesArrayList, clienteArrayList, data);
                    break;
                case 5://Exit button
                case -1://Exit X
                    sair = true;
            }
        } while (!sair);
    }

    private static void makeCall(ArrayList<Ligacoes> ligacoesArrayList, ArrayList<Cliente> clienteArrayList, Data data) {
        String allUserString = listAllUsers(clienteArrayList);
        if (!allUserString.equals(listAllUserEmpty())) {
            String numero = showSimpleInput("Entre com o número do usuário\nque realizará a ligação:\n" + allUserString);
            for (Cliente cliente : clienteArrayList) {
                if (cliente.getCellphoneNumber().equals(numero)) {
                    if (cliente.getPlanType() == 0) {//prepago
                        try {
                            String horaInicio = data.getHour();
                            int minutos = (Integer.parseInt(showSimpleInput("Hora de início: " +
                                    horaInicio + "\nQuantos minutos de ligação?")));
                            data.addMinutes(minutos);
                            String horaFim = data.getNewHour();
                            showMessage("Início: " + horaInicio + "\nFim: " + horaFim);
                            cliente.setCreditQuantityOrSeconds(cliente.getCreditQuantityOrSeconds() - minutos);
                            addToLigacoesArray(ligacoesArrayList, cliente.getUserName(), cliente.getCellphoneNumber(), data.getDate(), data.getDatePlus(minutos));
                        } catch (Exception e) {
                            showMessage("Minutos inválidos");
                        }
                    }
                } else {//pos pago
                    try {
                        String horaInicio = data.getHour();
                        int minutos = (Integer.parseInt(showSimpleInput("Hora de início: " +
                                horaInicio + "\nQuantos minutos de ligação?")));
                        data.addMinutes(minutos);
                        String horaFim = data.getNewHour();
                        showMessage("Início: " + horaInicio + "\nFim: " + horaFim);
                        cliente.setCreditQuantityOrSeconds(cliente.getCreditQuantityOrSeconds() + minutos);
                        addToLigacoesArray(ligacoesArrayList, cliente.getUserName(), cliente.getCellphoneNumber(), data.getDate(), data.getDatePlus(minutos));
                    } catch (Exception e) {
                        showMessage("Minutos inválidos");
                    }
                }
            }
        }else showMessage("Não há clientes cadastrados.");
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

    private static void includeMethod(Swing swing, ArrayList<Cliente> clienteArrayList) {
        int planType;
        boolean ok = false;
        do {
            showInputMenu(swing.getIncludeObjNameNumber());
            boolean achouCliente = returnEqualClienteBoolean(clienteArrayList, swing.getNumberTxt().getText());
            if (swing.getNumberTxt().getText().length() == 8 &&
                    tryParse(swing.getNumberTxt().getText()) && !achouCliente) {
                ok = true;
            } else if (!achouCliente) {
                showMessage("Número telefonico inválido.\n" +
                        "Entre apenas com 8 números sem simbolos.");
            } else showMessage("Cliente já existente.");
        } while (!ok);
        planType = showOptionMenu(swing.getPlanTypeButtons());
        if (planType == 0) {//prepago
            showInputMenu(swing.getCreditObj());
        } else {
            swing.setCreditMinuteTxt("0");
        }
        addToClienteArray(clienteArrayList, swing.getNumberTxt().getText(), swing.getNameTxt().getText(), Integer.parseInt(swing.getCreditMinuteTxt().getText()), planType);
    }

    private static void changeMethod(Swing swing, ArrayList<Cliente> clienteArrayList) {
        boolean achou;
        String allUserString = listAllUsers(clienteArrayList);
        if (!allUserString.equals(listAllUserEmpty())) {
            String numberToCheck = showSimpleInput(allUserString +
                    "\n\nEntre com o número que deseja alterar os dados.\nSomente a numeração");
            Cliente cliente = returnEqualCliente(clienteArrayList, numberToCheck);
            achou = returnEqualClienteBoolean(clienteArrayList, numberToCheck);
            if (achou) {
                cliente.setUserName(showSimpleInput("Entre com o novo nome do cliente:"));
                int novoPlano = showOptionMenu(swing.getPlanTypeButtons());
                cliente.setPlanType(novoPlano);
                if (novoPlano == 0) {//prepago
                    cliente.setCreditQuantityOrSeconds(Integer.parseInt(showSimpleInput("Entre com o novo crédito: ")));
                } else {
                    cliente.setCreditQuantityOrSeconds(Integer.parseInt(showSimpleInput("Entre com os minutos falados: ")));
                }
            } else showMessage("Não há cliente cadastrado com esse número.");
        }else showMessage("Não há clientes cadastrados.");
    }

    private static void excludeMethod(ArrayList<Cliente> clienteArrayList) {
        String allUserString = listAllUsers(clienteArrayList);
        if (!listAllUsers(clienteArrayList).equals(listAllUserEmpty())) {
            String numberToCheck = showSimpleInput(allUserString +
                    "\n\nEntre com o número que deseja excluir os dados.\nSomente a numeração");
            boolean achou = returnEqualClienteBoolean(clienteArrayList, numberToCheck);
            if (achou) {
                Cliente cliente = returnEqualCliente(clienteArrayList, numberToCheck);
                clienteArrayList.remove(cliente);
            } else showMessage("Não há cliente cadastrado com esse número.");
        } else showMessage("Não há clientes cadastrados.");
    }

    private static void reportMethod(Swing swing, ArrayList<Cliente> clienteArrayList) {
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
                generateBills(clienteArrayList);
                break;
        }
    }

    private static void generateBills(ArrayList<Cliente> clienteArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Entre com o número do cliente\nque deseja gerar o boleto\n");
        for (Cliente cliente : clienteArrayList) {
            if (cliente.getPlanType() == 1) {
                stringBuilder.append(cliente.toString());
                stringBuilder.append("\n-------------------------------\n");
            }
        }
        if (!listAllUsers(clienteArrayList).equals(listAllUserEmpty())) {
            String numero = showSimpleInput(stringBuilder.toString());
            for (Cliente cliente : clienteArrayList) {
                if (cliente.getCellphoneNumber().equals(numero)) {
                    int minutos = cliente.getCreditQuantityOrSeconds();
                    float valorBoleto = minutos * 1.57f;
                    showMessage("Valor do boleto = R$" + valorBoleto);
                    break;
                }
            }
        }else showMessage("Não há clientes cadastrados.");
    }

    private static void listMostCredit(ArrayList<Cliente> clienteArrayList) {
        String string = "Não há cliente pré pago cadastrado.\n";
        for (Cliente cliente : clienteArrayList) {
            for (Cliente cliente1 : clienteArrayList) {
                if (cliente.getPlanType() == 0 && cliente1.getPlanType() == 0 &&
                        cliente.getCreditQuantityOrSeconds() >= cliente1.getCreditQuantityOrSeconds()) {
                    string = "Cliente com maior crédito\n\n" + cliente.toString();
                }
            }
        }
        showMessage(string);
    }

    private static void listNegativeNullCredits(ArrayList<Cliente> clienteArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Clientes sem créditos ou negativado.\n\n");
        for (Cliente cliente : clienteArrayList) {
            if (cliente.getPlanType() == 0 && cliente.getCreditQuantityOrSeconds() <= 0)
                stringBuilder.append(cliente.toString()).append("\n-----------------------------------------------\n");
        }
        showMessage(stringBuilder.toString());
    }

    private static String listAllUserEmpty() {
        return "NÃO HÁ CLIENTES CADASTRADOS.";
    }

    private static String listAllUsers(ArrayList<Cliente> clienteArrayList) {
        StringBuilder stringBuilder = null;
        for (Cliente cliente : clienteArrayList) {
            stringBuilder.append(cliente.toString()).append("\n-----------------------------------------------\n");
        }
        if (stringBuilder != null) {
            return (stringBuilder.toString());
        } else return listAllUserEmpty();
    }

    private static void addToClienteArray(ArrayList<Cliente> clienteArrayList, String numero, String nome, int creditoOUminuto, int plano) {
        clienteArrayList.add(new Cliente(numero, nome, creditoOUminuto, plano));
    }

    private static void addToLigacoesArray(ArrayList<Ligacoes> ligacoesArrayList, String nome, String numero, String dataInicio, String dataFim) {
        ligacoesArrayList.add(new Ligacoes(nome, numero, dataInicio, dataFim));
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
