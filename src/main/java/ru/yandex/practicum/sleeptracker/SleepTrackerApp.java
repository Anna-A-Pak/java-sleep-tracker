package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class SleepTrackerApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Не указан путь к файлу!");
            return;
        }

        if (args[0].isEmpty() || args[0].isBlank()) {
            System.out.println("Путь к файлу не должен быть пустым!");
            return;
        }

        Path filePath = Paths.get(args[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        List<SleepSession> sleepSessions = new ArrayList<>();
        List<Function<List<SleepSession>, SleepAnalysisResult>> listFunction = new ArrayList<>();
        listFunction.add(new SleepSessionsCount());
        listFunction.add(new SleepSessionsMin());
        listFunction.add(new SleepSessionsMax());
        listFunction.add(new SleepSessionsMiddle());
        listFunction.add(new SleepSessionsBad());
        listFunction.add(new SleepLessNights());
        listFunction.add(new SleepChronotype());

        try (Stream<String> session = Files.lines(filePath, StandardCharsets.UTF_8)) {
            List<SleepSession> sleepSessionsNew = session
                    .filter(line -> !line.isBlank())
                    .map(line -> checkLine(line, formatter))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();

            sleepSessions.addAll(sleepSessionsNew);

        } catch (IOException e) {
            e.getStackTrace();
            System.out.println("Ошибка чтения файла!");
        }

        listFunction.forEach(function -> {
            SleepAnalysisResult sleepAnalysisResult = function.apply(sleepSessions);
            if (sleepAnalysisResult.getValue() != -1) {
                System.out.println(sleepAnalysisResult.getDescription() + sleepAnalysisResult.getValue());
            } else {
                System.out.println(sleepAnalysisResult.getDescription());
            }
        });
    }

    private static Optional<SleepSession> checkLine(String line, DateTimeFormatter formatter) {
        String[] partsLine = line.split(";");
         if (partsLine.length != 3) {
             return Optional.empty();
         }

         try {
             LocalDateTime startSleep = LocalDateTime.parse(partsLine[0], formatter);
             LocalDateTime finishSleep = LocalDateTime.parse(partsLine[1], formatter);
             SleepQuality sleepQuality = SleepQuality.valueOf(partsLine[2]);
             Duration duration = Duration.between(startSleep, finishSleep);
             if (duration.isNegative() || duration.toHours() > 24) {
                 return Optional.empty();
             }
             return Optional.of(new SleepSession(startSleep, finishSleep, sleepQuality));
         } catch (Exception e) {
             return Optional.empty();
         }
    }
}