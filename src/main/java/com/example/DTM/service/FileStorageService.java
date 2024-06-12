package com.example.DTM.service;

import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public List<String> storeFiles(List<MultipartFile> files) throws IOException {
        List<String> imagePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + File.separator + filename);
            Files.copy(file.getInputStream(), path);
            imagePaths.add(path.toString());
        }
        return imagePaths;
    }
}
