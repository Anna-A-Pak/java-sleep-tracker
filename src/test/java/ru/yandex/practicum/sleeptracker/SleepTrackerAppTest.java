package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {
    private static SleepSession sleepSession;

    @Test
    public void testSleepSessionsCountShouldBeNil() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        SleepSessionsCount sessionsCount = new SleepSessionsCount();
        SleepAnalysisResult sleepAnalysisResult = sessionsCount.apply(sleepSessions);
        Assertions.assertEquals(0,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepSessionsCountShouldBeOne() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 23, 35),
                LocalDateTime.of(2026, 3, 16, 7, 15),
                SleepQuality.valueOf("GOOD")));
        SleepSessionsCount sessionsCount = new SleepSessionsCount();
        SleepAnalysisResult sleepAnalysisResult = sessionsCount.apply(sleepSessions);
        Assertions.assertEquals(1,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepSessionsMinShouldBeThirty() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 16, 0, 0),
                LocalDateTime.of(2026, 3, 16, 0, 30),
                SleepQuality.valueOf("BAD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 17, 0, 0),
                LocalDateTime.of(2026, 3, 17, 0, 30),
                SleepQuality.valueOf("BAD")));
        SleepSessionsMin sessionsMin = new SleepSessionsMin();
        SleepAnalysisResult sleepAnalysisResult = sessionsMin.apply(sleepSessions);
        Assertions.assertEquals(30,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepSessionsMinShouldBeThirtyOne() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 16, 0, 0),
                LocalDateTime.of(2026, 3, 16, 0, 31),
                SleepQuality.valueOf("BAD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 17, 0, 0),
                LocalDateTime.of(2026, 3, 17, 0, 32),
                SleepQuality.valueOf("BAD")));
        SleepSessionsMin sessionsMin = new SleepSessionsMin();
        SleepAnalysisResult sleepAnalysisResult = sessionsMin.apply(sleepSessions);
        Assertions.assertEquals(31,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepSessionsMaxShouldBeThirtyOne() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 16, 0, 0),
                LocalDateTime.of(2026, 3, 16, 0, 31),
                SleepQuality.valueOf("BAD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 17, 0, 0),
                LocalDateTime.of(2026, 3, 17, 0, 30),
                SleepQuality.valueOf("BAD")));
        SleepSessionsMax sessionsMax = new SleepSessionsMax();
        SleepAnalysisResult sleepAnalysisResult = sessionsMax.apply(sleepSessions);
        Assertions.assertEquals(31,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepSessionsMaxShouldBeThrow() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        SleepSessionsMax sessionsMax = new SleepSessionsMax();
        try {
            sessionsMax.apply(sleepSessions);
        } catch (RuntimeException e) {
            Assertions.assertEquals("Список сессий сна пустой!", e.getMessage());
        }
    }

    @Test
    public void testSleepSessionsMiddleShouldBeFourHundredEighty() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 23, 0),
                LocalDateTime.of(2026, 3, 16, 7, 0),
                SleepQuality.valueOf("GOOD")));
        SleepSessionsMiddle sessionsMiddle = new SleepSessionsMiddle();
        SleepAnalysisResult sleepAnalysisResult = sessionsMiddle.apply(sleepSessions);
        Assertions.assertEquals(480,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepSessionsMiddleShouldBeThirty() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 16, 0, 0),
                LocalDateTime.of(2026, 3, 16, 0, 30),
                SleepQuality.valueOf("BAD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 17, 0, 0),
                LocalDateTime.of(2026, 3, 17, 0, 30),
                SleepQuality.valueOf("BAD")));
        SleepSessionsMiddle sessionsMiddle = new SleepSessionsMiddle();
        SleepAnalysisResult sleepAnalysisResult = sessionsMiddle.apply(sleepSessions);
        Assertions.assertEquals(30,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepSessionsBadShouldBeNil() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 23, 35),
                LocalDateTime.of(2026, 3, 16, 7, 15),
                SleepQuality.valueOf("GOOD")));
        SleepSessionsBad sessionsBad = new SleepSessionsBad();
        SleepAnalysisResult sleepAnalysisResult = sessionsBad.apply(sleepSessions);
        Assertions.assertEquals(0,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepSessionsBadShouldBeOne() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 23, 35),
                LocalDateTime.of(2026, 3, 16, 7, 15),
                SleepQuality.valueOf("GOOD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 17, 0, 0),
                LocalDateTime.of(2026, 3, 17, 0, 30),
                SleepQuality.valueOf("BAD")));
        SleepSessionsBad sessionsBad = new SleepSessionsBad();
        SleepAnalysisResult sleepAnalysisResult = sessionsBad.apply(sleepSessions);
        Assertions.assertEquals(1,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepLessNightsShouldBeNil() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 0, 0),
                LocalDateTime.of(2026, 3, 15, 6, 0),
                SleepQuality.valueOf("GOOD")));
        SleepLessNights sleepLessNights = new SleepLessNights();
        SleepAnalysisResult sleepAnalysisResult = sleepLessNights.apply(sleepSessions);
        Assertions.assertEquals(0,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepLessNightsShouldBeOne() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 6, 0),
                LocalDateTime.of(2026, 3, 15, 7, 0),
                SleepQuality.valueOf("GOOD")));
        SleepLessNights sleepLessNights = new SleepLessNights();
        SleepAnalysisResult sleepAnalysisResult = sleepLessNights.apply(sleepSessions);
        Assertions.assertEquals(1,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepLessNightsShouldBeThree() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 6, 0),
                LocalDateTime.of(2026, 3, 15, 7, 0),
                SleepQuality.valueOf("GOOD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 17, 5, 59),
                LocalDateTime.of(2026, 3, 17, 7, 0),
                SleepQuality.valueOf("GOOD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 18, 23, 0),
                LocalDateTime.of(2026, 3, 19, 0, 0),
                SleepQuality.valueOf("GOOD")));
        SleepLessNights sleepLessNights = new SleepLessNights();
        SleepAnalysisResult sleepAnalysisResult = sleepLessNights.apply(sleepSessions);
        Assertions.assertEquals(3,sleepAnalysisResult.getValue());
    }

    @Test
    public void testSleepChronotypeShouldBeOwl() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 17, 23, 1),
                LocalDateTime.of(2026, 3, 18, 23, 59),
                SleepQuality.valueOf("GOOD")));
        SleepChronotype sleepChronotype = new SleepChronotype();
        SleepAnalysisResult sleepAnalysisResult = sleepChronotype.apply(sleepSessions);
        Assertions.assertTrue(sleepAnalysisResult.getDescription().contains("OWL"));
    }

    @Test
    public void testSleepChronotypeShouldBeDove() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 23, 1),
                LocalDateTime.of(2026, 3, 16, 9, 1),
                SleepQuality.valueOf("GOOD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 16, 21, 59),
                LocalDateTime.of(2026, 3, 17, 6, 59),
                SleepQuality.valueOf("GOOD")));
        SleepChronotype sleepChronotype = new SleepChronotype();
        SleepAnalysisResult sleepAnalysisResult = sleepChronotype.apply(sleepSessions);
        Assertions.assertTrue(sleepAnalysisResult.getDescription().contains("DOVE"));
    }

    @Test
    public void testSleepChronotypeShouldBeLark() {
        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 15, 21, 59),
                LocalDateTime.of(2026, 3, 16, 6, 59),
                SleepQuality.valueOf("GOOD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 16, 21, 59),
                LocalDateTime.of(2026, 3, 17, 6, 59),
                SleepQuality.valueOf("GOOD")));
        sleepSessions.add(new SleepSession(LocalDateTime.of(2026, 3, 17, 23, 1),
                LocalDateTime.of(2026, 3, 18, 23, 59),
                SleepQuality.valueOf("GOOD")));
        SleepChronotype sleepChronotype = new SleepChronotype();
        SleepAnalysisResult sleepAnalysisResult = sleepChronotype.apply(sleepSessions);
        Assertions.assertTrue(sleepAnalysisResult.getDescription().contains("LARK"));
    }
}