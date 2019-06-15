package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Ligacoes {
    private Scanner scanner;
    private String numero;
    private String dataInicio;
    private String dataFim;
    private ArrayList<Ligacoes> ligacoesArrayList;

    public Ligacoes(String numero, String dataInicio, String dataFim) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Ligacoes() {
        this.ligacoesArrayList = new ArrayList<>();
        ligacoesArrayList = readFile(ligacoesArrayList);
    }

    public ArrayList<Ligacoes> readFile(ArrayList<Ligacoes> ligacoes) {

        if (isFile()) {
            scanner = openFileToRead(scanner);
        }
        addTxtToLigacoesArrayList(ligacoes, scanner);

        close(scanner);
        return ligacoes;
    }

    @Override
    public String toString() {
        return "Número: " + getNumero() + "\n" +
                "Hora da inicio da chamada: " + getDataInicio() + "\n" +
                "Hora de termino da chamada: " + getDataFim() + "\n" +
                "Minutos falados:" + getMinuteFromDateDiference() + "\n" +
                "Crédito Pre-Pago " + getValorPrePago() + "\n" +
                "Valor para Pós-Pago: " + getValorPosPago();
    }

    private String getValorPosPago() {
        if (getMinuteFromDateDiference() <= 0) {
            return 1 + " real";
        } else return getMinuteFromDateDiference() * 1.27f + " reais";
    }

    private long getMinuteFromDateDiference() {
        Data data = new Data();
        Date dateInicio = data.stringToDate(getDataInicio());
        Date dateFim = data.stringToDate(getDataFim());
        return (dateFim.getTime() - dateInicio.getTime()) / (60 * 1000) % 60;
    }

    private String getValorPrePago() {
        Random random = new Random();
        double d = random.nextInt(50);
        if (getMinuteFromDateDiference() >= 0) {
            return "inicial: " + d + "    crédito final: " + (d - 1);
        } else return "inicial: " + d + "    crédito final: " + (d - getMinuteFromDateDiference());
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

    private String getNumero() {
        return numero;
    }

    private String getDataInicio() {
        return dataInicio;
    }

    private String getDataFim() {
        return dataFim;
    }

    public ArrayList<Ligacoes> getLigacoesArray() {
        return ligacoesArrayList;
    }
}
