package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepSession {
    private LocalDateTime startSleep;
    private LocalDateTime finishSleep;
    private SleepQuality sleepQuality;
    private long durationToMinutes;

    public SleepSession(LocalDateTime startSleep, LocalDateTime finishSleep, SleepQuality sleepQuality) {
        this.startSleep = startSleep;
        this.finishSleep = finishSleep;
        this.sleepQuality = sleepQuality;
        this.durationToMinutes = Duration.between(startSleep, finishSleep).toMinutes();
    }

    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public LocalDateTime getFinishSleep() {
        return finishSleep;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public long getDurationToMinutes() {
        return durationToMinutes;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startSleeping=" + startSleep +
                ", finishSleeping=" + finishSleep +
                ", sleepQuality=" + sleepQuality +
                '}';
    }
}