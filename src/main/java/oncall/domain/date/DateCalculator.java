package oncall.domain.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateCalculator {

    private static final List<String> WEEKS = List.of("일", "월", "화", "수", "목", "금", "토");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("E");

    private int convertToNumber(String weekDay) {
        return WEEKS.indexOf(weekDay) + 1;
    }

    private String convertToString(int weekDayNumber) {
        return WEEKS.get(weekDayNumber - 1);
    }

    public List<String> getDates(String startDay, int endDate) {
        int startDayNumber = convertToNumber(startDay);

        Calendar calendar = Calendar.getInstance();
        int diff = (calendar.get(Calendar.DAY_OF_WEEK) - startDayNumber + 7) % 7;

        calendar.add(Calendar.DAY_OF_YEAR, -diff);
        List<String> repeatPattern = new ArrayList<>();

        for (int i = 0; i < endDate; i++) {
            repeatPattern.add(convertToString(calendar.get(Calendar.DAY_OF_WEEK)));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return repeatPattern;
    }
}
