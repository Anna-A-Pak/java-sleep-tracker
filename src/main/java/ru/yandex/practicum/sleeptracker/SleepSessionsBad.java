package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsBad implements Function<List<SleepSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        long result = sleepSessions.stream()
                .filter(sleepSession -> sleepSession.getSleepQuality() == SleepQuality.BAD)
                .count();
        return new SleepAnalysisResult((int) result, "Количество сессий с плохим качеством сна: ");
    }
}
