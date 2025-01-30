package com.example.SpringFraemworkSberHomework.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URL;


@RequiredArgsConstructor
public class ResourceDownloadTask implements Runnable {
    private final String resource;
    private final String path;
    private static final int SPEED_CONSTRAINT_KBPS = 500;

    @Override
    @SneakyThrows
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " начал загрузку ресурса: " + resource);
        try (InputStream in = new BufferedInputStream(new URL(resource).openStream());
             OutputStream out = new FileOutputStream(path + resource.substring(resource.lastIndexOf("/")))) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            long startTime = System.nanoTime();

            long totalBytesWritten = 0;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalBytesWritten += bytesRead;

                throttleDownload(totalBytesWritten, startTime);
            }

            long totalDownloadTimeNs = System.nanoTime() - startTime;
            long totalDownloadTimeMs = totalDownloadTimeNs / 1_000_000;

            double averageSpeedKBps = (totalBytesWritten / 1024.0) / (totalDownloadTimeMs / 1000.0);

            System.out.printf("Скачали %s%nПоложили в %s%nСредняя скорость загрузки: %.2f КБ/с%n", resource, path, averageSpeedKBps);
            System.out.println(threadName + " завершил загрузку ресурса: " + resource);
        } catch (IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }


    private void throttleDownload(long totalBytesWritten, long startTime) {
        long elapsedTimeNs = System.nanoTime() - startTime;
        long elapsedSeconds = elapsedTimeNs / 1_000_000_000;

        if (elapsedSeconds * 1024 * SPEED_CONSTRAINT_KBPS < totalBytesWritten) {
            long desiredTimeNs = (totalBytesWritten * 1_000_000_000L) / (SPEED_CONSTRAINT_KBPS * 1024);
            long sleepTime = desiredTimeNs - elapsedTimeNs;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1_000_000, (int) (sleepTime % 1_000_000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}