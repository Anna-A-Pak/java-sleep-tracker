package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsMax implements Function<List<SleepSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        long result = sleepSessions.stream()
                .mapToLong(SleepSession::getDurationToMinutes)
                .max()
                .orElse(0);
        return new SleepAnalysisResult((int)result, "Максимальная продолжительность сессии (в минутах): ");
    }
}
