package com.example.SpringFraemworkSberHomework.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class MultiThreadingResourceDownloadService implements ResourceDownloadService{
    private final int threadCount;
    private final static int DEFAULT_THREAD_COUNT = 3;



    public MultiThreadingResourceDownloadService(Environment environment) {
        String threadCount = environment.getProperty("download.threads");
        this.threadCount = threadCount == null ? DEFAULT_THREAD_COUNT : Integer.parseInt(threadCount);

    }


    @Override
    public void downloadResources(List<String> resources, String path) {
        ExecutorService executorService = Executors.newFixedThreadPool(this.threadCount);
        for (String resource : resources) {
            executorService.execute(new ResourceDownloadTask(resource, path));
           Thread.currentThread().getName();

        }
        executorService.shutdown();
    }
}
