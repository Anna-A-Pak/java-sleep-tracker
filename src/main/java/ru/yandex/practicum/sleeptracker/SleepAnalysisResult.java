package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    private int value;
    private String description;

    public SleepAnalysisResult(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SleepAnalysisResult{" +
                "value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
