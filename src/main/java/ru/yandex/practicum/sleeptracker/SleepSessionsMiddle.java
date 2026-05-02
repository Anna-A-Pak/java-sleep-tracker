package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsMiddle implements Function<List<SleepSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        double result = sleepSessions.stream()
                .mapToLong(SleepSession::getDurationToMinutes)
                .average()
                .orElse(0);

        return new SleepAnalysisResult((int) result, "Средняя продолжительность сессии (в минутах): ");
    }
}