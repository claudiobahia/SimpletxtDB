package sample;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class DAO {
    private Formatter formatter;

    public DAO() {
    }

    public void writeTxt(ArrayList<Cliente> clientes) {
        try {
            formatter = new Formatter(new File("dao.txt"));
            for (Cliente cliente : clientes) {
                formatter.format(cliente.getCellphoneNumber()+";"+ cliente.getUserName()+";"+
                        cliente.getCreditQuantityOrSeconds() +";"+ cliente.getPlanType()+"\n");
            }
            formatter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO WRITE CRIAR FILE");
        }
    }

    public ArrayList readFile(ArrayList<Cliente> clientes) {
        String linha;
        Object[] stringVet;
        Scanner scanner = null;
        try {
            try {
                scanner = new Scanner(new File("dao.txt"));
            } catch (Exception e) {
                System.out.println("Criou arquivo de texto");
                new Formatter(new File("dao.txt"));
                scanner = new Scanner(new File("dao.txt"));
            }
            try {
                while (scanner.hasNext()) {
                    linha = scanner.nextLine();
                    stringVet = linha.split(";");
                    Cliente cliente = new Cliente(stringVet[0].toString(), stringVet[1].toString(),
                            Integer.parseInt(stringVet[2].toString()), Integer.parseInt(stringVet[3].toString()));
                    clientes.add(cliente);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro le arquivo" + e);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro le arquivo new file");
        }
        scanner.close();
        return clientes;
    }

}
