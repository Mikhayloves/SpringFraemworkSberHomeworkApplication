package com.example.SpringFraemworkSberHomework.util;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class TxtListPathParser implements ListPathParser{
    @Override
    @SneakyThrows
    public List<String> parse(String path) {
        return Files.readAllLines(Paths.get(path));
    }
}
