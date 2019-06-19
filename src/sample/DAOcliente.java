package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class DAOcliente {
    private Formatter formatter;
    private Scanner scanner;

    public DAOcliente() {
    }

    public ArrayList<Cliente> readFileCliente(ArrayList<Cliente> clientes) {
        if (isFile()) {
            scanner = openFileToRead(scanner);
        }
        if (scanner != null) {
            addTxtToClienteArrayList(clientes, scanner);
        } else {
            formatter = createFileToWrite();
            scanner = openFileToRead(scanner);
            addTxtToClienteArrayList(clientes, scanner);
        }
        close(scanner);
        return clientes;
    }

    private Formatter createFileToWrite() {
        Formatter formatter = null;
        try {
            formatter = new Formatter(new File("dao.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    private Scanner openFileToRead(Scanner scanner) {
        if (isFile()) {
            try {
                scanner = new Scanner(new File("dao.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else createFileToWrite();
        return scanner;
    }

    private boolean isFile() {
        try {
            new Scanner(new File("dao.txt"));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public void writeTxtCliente(ArrayList<Cliente> clientes) {
        formatter = createFileToWrite();
        for (Cliente cliente : clientes) {
            formatter.format("%s;%s;%d;%d\n", cliente.getCellphoneNumber(), cliente.getUserName(), cliente.getCreditQuantityOrSeconds(), cliente.getPlanType());
        }
        close(formatter);
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

}
