package sample;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

public class DAO {
    private Formatter formatter;

    public DAO() {
        try {
            this.formatter = new Formatter(new File("dao.txt"));
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void writeTxt(ArrayList<Cliente> clientes){

    }



    /*
            ArrayList<Integer> papito = new ArrayList();
        papito.add(1);
        papito.add(2);
        papito.add(33);
        Formatter formatter = null;

        try {
            formatter = new Formatter(new File("dao.txt"));
            for (int o : papito) {

                formatter.format(o + "\n");
            }
            } catch(IOException e){
                System.out.println(e);
            }

        formatter.close();
     */

}
