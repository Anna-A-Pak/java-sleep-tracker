package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsMiddle implements Function<List<SleepSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        long result = sleepSessions.stream()
                .mapToLong(SleepSession::getDurationToMinutes)
                .sum();
        try {
            result = result / sleepSessions.size();
        } catch (IllegalAccessError e) {
            throw new RuntimeException("Список сессий сна пустой!");
        }

        return new SleepAnalysisResult((int) result, "Средняя продолжительность сессии (в минутах): ");
    }
}