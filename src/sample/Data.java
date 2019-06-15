package sample;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Data {
    private DateFormat dateFormat;


    public Data() {
        this.dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
    }

    public Date stringToDate(String s) {
        try {
            return getDateFormat().parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private DateFormat getDateFormat() {
        return dateFormat;
    }
}
