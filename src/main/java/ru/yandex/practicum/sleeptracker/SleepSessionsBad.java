package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsBad implements Function<List<SleepSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        List<SleepSession> sleepSessionsBad = sleepSessions.stream()
                .filter(sleepSession -> sleepSession.getSleepQuality() == SleepQuality.BAD)
                .toList();
        int result = sleepSessionsBad.size();
        return new SleepAnalysisResult(result, "Количество сессий с плохим качеством сна: ");
    }
}
