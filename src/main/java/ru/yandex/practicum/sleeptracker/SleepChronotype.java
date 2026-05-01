package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class SleepChronotype implements Function<List<SleepSession>, SleepAnalysisResult> {
    public static final LocalTime START_SLEEP_OWL = LocalTime.of(23, 0);
    public static final LocalTime END_SLEEP_OWL = LocalTime.of(9, 0);
    public static final LocalTime START_SLEEP_LARK = LocalTime.of(22, 0);
    public static final LocalTime END_SLEEP_LARK = LocalTime.of(7, 0);
    int owlChronotype = 0;
    int larkChronotype = 0;
    int doveChronotype = 0;
    Chronotype chronotype;
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        sleepSessions
                .forEach(sleepSession -> {
                    if (sleepSession.getStartSleep().toLocalTime().isAfter(START_SLEEP_OWL) &&
                    sleepSession.getFinishSleep().toLocalTime().isAfter(END_SLEEP_OWL)) {
                        owlChronotype++;
                    } else if (sleepSession.getStartSleep().toLocalTime().isBefore(START_SLEEP_LARK) &&
                            sleepSession.getFinishSleep().toLocalTime().isBefore(END_SLEEP_LARK)) {
                        larkChronotype++;
                    } else {
                        doveChronotype++;
                    }
                });
        if (owlChronotype > larkChronotype && owlChronotype > doveChronotype) {
            chronotype = Chronotype.OWL;
        } else if (larkChronotype > owlChronotype && larkChronotype > doveChronotype) {
            chronotype = Chronotype.LARK;
        } else {
            chronotype = Chronotype.DOVE;
        }
        return new SleepAnalysisResult(-1, "Хронотип пользователя: " + chronotype.toString());
    }
}
