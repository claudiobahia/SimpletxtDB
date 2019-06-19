package sample;

import java.util.Date;

public class Ligacoes {
    private String numero;
    private String dataInicio;
    private String dataFim;

    public Ligacoes(String numero, String dataInicio, String dataFim) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "Número: " + getNumero() + "\n" +
                "Hora da inicio da chamada: " + getDataInicio() + "\n" +
                "Hora de termino da chamada: " + getDataFim() + "\n" +
                "Minutos falados:" + getMinuteFromDateDiference() + "\n";
    }

    public String getValorPosPago(String minutosInicial) {
        if (getMinuteFromDateDiference() > 0) {
            return "Minutos inicial: " + minutosInicial +
                    "\nMinutos Final: " + (Integer.parseInt(minutosInicial) + Integer.parseInt(Long.toString(getMinuteFromDateDiference()))) +
                    "\nValor do boleto: " + (getMinuteFromDateDiference() * 1.73);
        }else return "Minutos inicial: " + minutosInicial +
                "\nMinutos Final: " + (Integer.parseInt(minutosInicial) + 1) +
                "\nValor do boleto: " + (1);
    }

    public long getMinuteFromDateDiference() {
        Data data = new Data();
        Date dateInicio = data.stringToDate(getDataInicio());
        Date dateFim = data.stringToDate(getDataFim());
        return (dateFim.getTime() - dateInicio.getTime()) / (60 * 1000) % 60;
    }

    public String getValorPrePago(String creditoInicial) {
        if (getMinuteFromDateDiference() > 0) {
            return "Crédito inicial: " + creditoInicial +
                    "\nCrédito Final: " + (Integer.parseInt(creditoInicial) - Integer.parseInt(Long.toString(getMinuteFromDateDiference())));
        }else return "Crédito inicial: " + creditoInicial +
                "\nCrédito Final: " + (Integer.parseInt(creditoInicial) - 1);
    }

    public String getNumero() {
        return numero;
    }

    private String getDataInicio() {
        return dataInicio;
    }

    private String getDataFim() {
        return dataFim;
    }
}
