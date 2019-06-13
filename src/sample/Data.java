package sample;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data {
    private Date date;
    private DateFormat dateFormat;
    private Calendar calendar;

    public Data() {
        this.date = new Date();
        this.dateFormat = DateFormat.getTimeInstance();
        this.calendar = Calendar.getInstance();
    }

    public String getDate() {
        refresh();
        return date.toString();
    }

    public String getDatePlus(int minutos) {
        refresh();
        addMinutes(minutos);
        date = calendar.getTime();
        return date.toString();
    }

    public String getHour() {
        refresh();
        return dateFormat.format(date);
    }

    public void addMinutes(int minutes) {
        refresh();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
    }

    public String getNewHour() {
        date = calendar.getTime();
        return dateFormat.format(date);
    }

    private void refresh() {
        this.date = new Date();
    }
}
