package oncall.domain;

import java.util.Arrays;
import oncall.view.input.error.InputError;
import oncall.view.input.error.InputIllegalArgumentException;

public enum Month {

    JAN("1", 31),
    FEB("2", 28),
    MUR("3", 31),
    APR("4", 30),
    MAY("5", 31),
    JUN("6", 30),
    JULY("7", 31),
    AUG("8", 31),
    SEP("9", 30),
    OCT("10", 31),
    NOV("11", 30),
    DEC("12", 31);

    final String number;
    final int endDate;

    Month(String number, int endDate) {
        this.number = number;
        this.endDate = endDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public String getNumber() {
        return number;
    }

    public static Month getMonthByNumber(String monthNumber) {
        return Arrays.stream(values())
                .filter(month -> month.number.equals(monthNumber))
                .findFirst()
                .orElseThrow(() -> new InputIllegalArgumentException(InputError.NOT_POSSIBLE_INPUT_RANGE));
    }
}
