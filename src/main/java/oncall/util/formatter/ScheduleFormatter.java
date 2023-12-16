package oncall.util.formatter;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Month;

public class ScheduleFormatter {

    private static final List<String> WEEK_DAY = List.of("월", "화", "수", "목", "금");
    private List<String> formattedSchedule = new ArrayList<>();

    public List<String> format(Month month, List<String> weeks, List<String> scheduleResult,
                               List<Integer> dayOffWeeks) {
        for (int i = 1; i < month.getEndDate() + 1; i++) {
            String week = weeks.get(i - 1);

            if (dayOffWeeks.contains(i) && WEEK_DAY.contains(week)) {
                week = week + "(휴일)";
            }

            String result = month.getNumber() + "월 " + i + "일 " + week + " " + scheduleResult.get(i - 1);

            formattedSchedule.add(result);
        }

        return formattedSchedule;
    }
}
