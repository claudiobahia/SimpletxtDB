package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Ligacoes {
    private Formatter formatter;
    private Scanner scanner;
    private String nome;
    private String numero;
    private String dataInicio;
    private String dataFim;

    //todo ajeitar metodos etc

    public Ligacoes(String nome, String numero, String dataInicio,String dataFim) {
        this.nome = nome;
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Ligacoes() {
    }

    public void writeTxt(ArrayList<Ligacoes> ligacoes) {
        formatter = createFileToWrite(formatter);
        for (Ligacoes ligacoes1 : ligacoes) {
            formatter.format("%s;%s;%s;%s\n", ligacoes1.getNome(),ligacoes1.getNumero(),ligacoes1.getDataInicio(),ligacoes1.getDataFim());
        }
        close(formatter);
    }

    public ArrayList<Ligacoes> readFile(ArrayList<Ligacoes> ligacoes) {

        if (isFile()) {
            scanner = openFileToRead(scanner);
        }
        if (scanner != null) {
            addTxtToLigacoesArrayList(ligacoes, scanner);
        } else {
            formatter = createFileToWrite(formatter);
            scanner = openFileToRead(scanner);
            addTxtToLigacoesArrayList(ligacoes, scanner);
        }
        close(scanner);
        return ligacoes;
    }

    private void addTxtToLigacoesArrayList(ArrayList<Ligacoes> ligacoes, Scanner scanner) {
        String linha;
        String[] stringVet;
        while (scanner.hasNext()) {
            linha = scanner.nextLine();
            stringVet = linha.split(";");
            Ligacoes ligacoes1 = new Ligacoes(stringVet[0],stringVet[1],stringVet[2],stringVet[3]);
            ligacoes.add(ligacoes1);
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
            formatter = new Formatter(new File("ligacoes.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    private boolean isFile() {
        try {
            new Scanner(new File("ligacoes.txt"));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    private Scanner openFileToRead(Scanner scanner) {
        if (isFile()) {
            try {
                scanner = new Scanner(new File("ligacoes.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else createFileToWrite(formatter);
        return scanner;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
