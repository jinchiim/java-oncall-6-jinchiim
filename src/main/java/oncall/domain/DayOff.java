package oncall.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public enum DayOff {

    JAN_DAYOFF(Month.JAN, List.of(1)),
    MUR_DAYOFF(Month.MUR, List.of(1)),
    MAY_DAYOFF(Month.MAY, List.of(5)),
    JUN(Month.JUN, List.of(6)),
    AUG(Month.AUG, List.of(3)),
    OCT(Month.OCT, List.of(3, 9)),
    DEC(Month.DEC, List.of(25));

    final Month month;
    final List<Integer> date;

    DayOff(Month month, List<Integer> date) {
        this.month = month;
        this.date = date;
    }

    public static List<Integer> getDayOffByMonth(Month choiceMonth) {
        Optional<DayOff> monthOptional = Arrays.stream(values())
                .filter(month -> month.month.equals(choiceMonth))
                .findFirst();

        return monthOptional.map(month -> month.date)
                .orElse(Collections.emptyList());
    }
}
