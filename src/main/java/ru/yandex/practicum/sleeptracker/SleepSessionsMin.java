package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsMin implements Function<List<SleepSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        long result = sleepSessions.stream()
                .mapToLong(SleepSession::getDurationToMinutes)
                .min()
                .orElse(0);
        return new SleepAnalysisResult((int) result, "Минимальная продолжительность сессии (в минутах): ");
    }
}