package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class SleepLessNights implements Function<List<SleepSession>, SleepAnalysisResult> {
    public static final LocalTime START_NIGHT = LocalTime.of(0, 0);
    public static final LocalTime END_NIGHT = LocalTime.of(6, 0);
    int result = 0;

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        List<SleepSession> sleepSessionsLessNights = sleepSessions.stream()
                .filter(sleepSession -> sleepSession.getStartSleep().toLocalTime().isBefore(END_NIGHT) ||
                        (sleepSession.getStartSleep().toLocalDate().getDayOfMonth() <
                                sleepSession.getFinishSleep().getDayOfMonth() &&
                        sleepSession.getFinishSleep().toLocalTime().isAfter(START_NIGHT)))
                .toList();

        Period period = Period.between(sleepSessions.getFirst().getStartSleep().toLocalDate(),
                sleepSessions.getLast().getFinishSleep().toLocalDate());

        if (period.getDays() > 0) {
            result = period.getDays() - sleepSessionsLessNights.size();
        } else if (period.getDays() == 0 && sleepSessionsLessNights.isEmpty()) {
            result = 1;
        }

        return new SleepAnalysisResult(result, "Количество бессонных ночей: ");
    }
}