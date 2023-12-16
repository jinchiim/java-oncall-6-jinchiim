package oncall.domain.schedular;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private static final List<String> WEEK_DAY = List.of("월", "화", "수", "목", "금");
    private static final List<String> WEEKEND = List.of("토", "일");

    private final List<String> weekDayWorker;
    private final List<String> weekendWorker;

    private int weekDayWorkerIdx = 0;
    private int weekendWorkerIdx = 0;
    private boolean isWeekend;
    private boolean isWeekendChanged = false;
    private boolean isWeekDayChanged = false;
    final List<String> scheduleResult = new ArrayList<>();

    public Scheduler(List<String> weekDayWorker, List<String> weekendWorker) {
        this.weekDayWorker = weekDayWorker;
        this.weekendWorker = weekendWorker;
    }

    public List<String> getSchedule(List<String> weekCalendar, List<Integer> dayOffMonth) {
        String worker = initSchedule(weekCalendar, dayOffMonth);
        scheduleResult.add(worker);

        for (int i = 1; i < weekCalendar.size(); i++) {
            String currentDay = weekCalendar.get(i);
            int currentDate = i + 1;
            if (dayOffMonth.contains(currentDate) || WEEKEND.contains(currentDay)) {
                if (isWeekendChanged) {
                    changeBeforeWorker(weekendWorker);
                    continue;
                }
                handleDayOffOrWeekend();
            }
            if (!dayOffMonth.contains(currentDate) && WEEK_DAY.contains(currentDay)) {
                if (isWeekDayChanged) {
                    changeBeforeWorker(weekDayWorker);
                    continue;
                }
                handleWeekday();
            }
        }

        return scheduleResult;
    }

    private void handleDayOffOrWeekend() {
        if (isWeekend) {
            scheduleResult.add(addWeekendWorker());
        }
        if (!isWeekend) { // 이전에 넣은 값이 휴일이 아닌 경우
            isWeekend = true; // 현재를 휴일로 바꿔줌
            checkNeedChange(weekendWorker.get(weekendWorkerIdx));
        }
    }

    private void handleWeekday() {
        if (!isWeekend) {
            scheduleResult.add(addWeekDayWorker());
        }
        if (isWeekend) {
            isWeekend = false;
            checkNeedChange(weekDayWorker.get(weekDayWorkerIdx));
        }
    }

    private void changeWorker(List<String> workerList) {
        String currentWorker = getLastElement(scheduleResult);
        int currentIndex = workerList.indexOf(currentWorker);

        // 다음 근무자의 인덱스를 찾아서 교체

        int nextIndex = (currentIndex + 1) % workerList.size();
        if (isWeekend) {
            weekendWorkerIdx++;
            isWeekendChanged = true;
        }
        if (!isWeekend) {
            weekDayWorkerIdx++;
            isWeekDayChanged = true;
        }
        scheduleResult.add(workerList.get(nextIndex));
    }

    private void changeBeforeWorker(List<String> workerList) {
        String currentWorker = getLastElement(scheduleResult);
        int currentIndex = workerList.indexOf(currentWorker);

        int beforeIndex = (currentIndex - 1) % workerList.size();

        if (isWeekend) {
            weekendWorkerIdx++;
            isWeekendChanged = false;
        }
        if (!isWeekend) {
            weekDayWorkerIdx++;
            isWeekDayChanged = false;
        }
        scheduleResult.add(workerList.get(beforeIndex));
    }

    private String initSchedule(List<String> weekCalender, List<Integer> dayOffMonth) {
        String firstWeek = weekCalender.get(0);

        if (dayOffMonth.contains(1) || WEEKEND.contains(firstWeek)) {
            isWeekend = true;
            return addWeekendWorker();
        }

        if (!dayOffMonth.contains(1) && WEEK_DAY.contains(firstWeek)) {
            isWeekend = false;
        }
        return addWeekDayWorker();
    }

    private String addWeekDayWorker() {
        String worker = weekDayWorker.get(weekDayWorkerIdx);
        weekDayWorkerIdx++;

        if (weekDayWorkerIdx == weekDayWorker.size()) {
            weekDayWorkerIdx = 0;
        }
        isWeekend = false;
        return worker;
    }

    private String addWeekendWorker() {
        String worker = weekendWorker.get(weekendWorkerIdx);
        weekendWorkerIdx++;

        if (weekendWorkerIdx == weekendWorker.size()) {
            weekendWorkerIdx = 0;
        }
        isWeekend = true;
        return worker;
    }

    private void checkNeedChange(String worker) {
        boolean isDuplicate = checkIsDuplicateSchedule(worker); // 이미 전에 근무했던 직원일 경우

        if (!isDuplicate) { // 전에 근무했던 직원이 아닐 경우
            if (isWeekend) {
                scheduleResult.add(addWeekendWorker());
            }
            if (!isWeekend) {
                scheduleResult.add(addWeekDayWorker());
            }
        }
        if (isDuplicate) { // 전에 근무했던 직원일 경우
            if (isWeekend) {
                changeWorker(weekDayWorker);
            }
            if (!isWeekend) {
                changeWorker(weekendWorker);
            }
        }
    }

    private boolean checkIsDuplicateSchedule(String worker) {
        String lastWorker = getLastElement(scheduleResult);
        if (lastWorker.equals(worker)) {
            return true;
        }
        return false;
    }

    private static <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }
}
