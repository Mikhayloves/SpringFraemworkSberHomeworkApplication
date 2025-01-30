package com.example.SpringFraemworkSberHomework.commandLineRunner;

import com.example.SpringFraemworkSberHomework.service.ResourceDownloadService;
import com.example.SpringFraemworkSberHomework.util.ListPathParser;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DownloadCommandLineRunner implements CommandLineRunner {
    private final ResourceDownloadService resourceDownloadService;
    private final ListPathParser listPathParser;
    private final Environment environment;


    @Override
    public void run(String... args) throws Exception {
        resourceDownloadService.downloadResources(listPathParser.parse
                        (environment.getProperty("download.resources")),
                environment.getProperty("download.path"));
    }
}
