package com.example.SpringFraemworkSberHomework.service;

import java.util.List;

public interface ResourceDownloadService {
    void downloadResources(List<String> resources, String path);
}
