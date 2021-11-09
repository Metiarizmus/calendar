package helperData;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HelperGetFullDate {
    public Date getFullData(String time, String date) {

        date = date.replaceAll("\\[", "").replaceAll("\\]", "");

        String[] s = date.split("\\,");

        String[] ss = time.split("\\:");

        s[1] = s[1].replaceAll("\"", "");

        int k = 0;
        switch (s[1]) {
            case "январь":
                k = 0;
                break;
            case "февраль":
                k = 1;
                break;
            case "март":
                k = 2;
                break;
            case "апрель":
                k = 3;
                break;
            case "май":
                k = 4;
                break;
            case "июнь":
                k = 5;
                break;
            case "июль":
                k = 6;
                break;
            case "август":
                k = 7;
                break;
            case "сентябрь":
                k = 8;
                break;
            case "октябрь":
                k = 9;
                break;
            case "ноябрь":
                k = 10;
                break;
            case "декабрь":
                k = 11;
                break;
            default:k=-1;
        }

        Calendar calendar1 = new GregorianCalendar();
        calendar1.set(Calendar.YEAR, Integer.parseInt(s[2]));
        calendar1.set(Calendar.MONTH, k);
        calendar1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s[0]));
        calendar1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ss[0]));
        calendar1.set(Calendar.MINUTE, Integer.parseInt(ss[1]));
        calendar1.set(Calendar.SECOND, 0);

        java.util.Date date1 = calendar1.getTime();
        return date1;
    }

    public String[] getNowDateAndPlusMonth() {
        String[] s = new String[2];
        String[] todaydate = String.valueOf(LocalDate.now()).split("-");

        Calendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(todaydate[0]),Integer.parseInt(todaydate[1])-1,Integer.parseInt(todaydate[2]));

        Calendar calendar1 = new GregorianCalendar();
        calendar1.set(Integer.parseInt(todaydate[0]),Integer.parseInt(todaydate[1]),Integer.parseInt(todaydate[2]));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String s1 = dateFormat.format(calendar.getTime());
        String s2 = dateFormat.format(calendar1.getTime());

        s[0] = s1;
        s[1] = s2;

        return s;
    }
}
