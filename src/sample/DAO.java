package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class DAO {
    private Formatter formatter;
    private Scanner scanner;

    public DAO() {
    }

    public void writeTxt(ArrayList<Cliente> clientes) {
        formatter = createFileToWrite(formatter);
        for (Cliente cliente : clientes) {
            formatter.format("%s;%s;%d;%d\n", cliente.getCellphoneNumber(), cliente.getUserName(), cliente.getCreditQuantityOrSeconds(), cliente.getPlanType());
        }
        close(formatter);
    }

    public ArrayList<Cliente> readFile(ArrayList<Cliente> clientes) {

        if (isFile()) {
            scanner = openFileToRead(scanner);
        }
        if (scanner != null) {
            addTxtToClienteArrayList(clientes, scanner);
        } else {
            formatter = createFileToWrite(formatter);
            scanner = openFileToRead(scanner);
            addTxtToClienteArrayList(clientes, scanner);
        }
        close(scanner);
        return clientes;
    }

    private void addTxtToClienteArrayList(ArrayList<Cliente> clientes, Scanner scanner) {
        String linha;
        Object[] stringVet;
        while (scanner.hasNext()) {
            linha = scanner.nextLine();
            stringVet = linha.split(";");
            Cliente cliente = new Cliente(stringVet[0].toString(), stringVet[1].toString(),
                    Integer.parseInt(stringVet[2].toString()), Integer.parseInt(stringVet[3].toString()));
            clientes.add(cliente);
        }
    }

    private void close(Formatter formatter) {
        try {
            formatter.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void close(Scanner scanner) {
        try {
            scanner.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Formatter createFileToWrite(Formatter formatter) {
        formatter = null;
        try {
            formatter = new Formatter(new File("dao.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    private boolean isFile() {
        try {
            new Scanner(new File("dao.txt"));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    private Scanner openFileToRead(Scanner scanner) {
        if (isFile()) {
            try {
                scanner = new Scanner(new File("dao.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else createFileToWrite(formatter);
        return scanner;
    }
}
