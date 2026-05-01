package ru.yandex.practicum.sleeptracker;

import java.util.function.Function;
import java.util.List;

public class SleepSessionsCount implements Function<List<SleepSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return new SleepAnalysisResult(sleepSessions.size(), "Сессий сна всего: ");
    }

}
