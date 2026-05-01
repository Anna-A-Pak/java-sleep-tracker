package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsMin implements Function<List<SleepSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        long result = sleepSessions.stream()
                .mapToLong(SleepSession::getDurationToMinutes)
                .min()
                .orElseThrow(() -> new RuntimeException("Список сессий сна пустой!"));
        return new SleepAnalysisResult((int) result, "Минимальная продолжительность сессии (в минутах): ");
    }
}