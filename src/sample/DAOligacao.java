package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DAOligacao {
    private Scanner scanner;

    public DAOligacao() {
    }

    public ArrayList<Ligacoes> readFileLigacao(ArrayList<Ligacoes> ligacoes) {
        if (isFile()) {
            scanner = openFileToRead(scanner);
        }
        addTxtToLigacoesArrayList(ligacoes, scanner);
        close(scanner);
        return ligacoes;
    }

    private void addTxtToLigacoesArrayList(ArrayList<Ligacoes> ligacoes, Scanner scanner) {
        String linha;
        String[] stringVet;
        while (scanner.hasNext()) {
            linha = scanner.nextLine();
            stringVet = linha.split(";");
            Ligacoes ligacoes1 = new Ligacoes(stringVet[0], stringVet[1], stringVet[2]);
            ligacoes.add(ligacoes1);
        }
    }

    private void close(Scanner scanner) {
        try {
            scanner.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isFile() {
        try {
            new Scanner(new File("ligacoes.txt"));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Scanner openFileToRead(Scanner scanner) {
        try {
            scanner = new Scanner(new File("ligacoes.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scanner;
    }
}
