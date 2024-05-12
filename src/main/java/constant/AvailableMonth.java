package constant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AvailableMonth {
    public static List<String> month;
    public static List<LocalDate> date;

    static {
        month = new ArrayList<String>();
        date = new ArrayList<LocalDate>();

        LocalDate now = LocalDate.now();
        if (now.getMonthValue() >= 9) {
            for (int i = 9; i <= 12; i ++) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(now.getYear(), i - 1, 1);
                String currentMonthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
                month.add(currentMonthName + "-" + now.getYear());
                date.add(LocalDate.of(now.getYear(), i, 1));
            }
            for (int i = 1; i <= 5; i ++) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(now.getYear() + 1, i - 1, 1);
                String currentMonthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
                month.add(currentMonthName + "-" + (now.getYear() + 1));
                date.add(LocalDate.of((now.getYear() + 1), i, 1));
            }
        } else {
            for (int i = 9; i <= 12; i ++) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(now.getYear() - 1, i - 1, 1);
                String currentMonthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
                month.add(currentMonthName + "-" + (now.getYear() - 1));
                date.add(LocalDate.of((now.getYear() - 1), i, 1));
            }
            for (int i = 1; i <= 5; i ++) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(now.getYear(), i - 1, 1);
                String currentMonthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
                month.add(currentMonthName + "-" + (now.getYear()));
                date.add(LocalDate.of((now.getYear()), i, 1));
            }
        }
    }
}
