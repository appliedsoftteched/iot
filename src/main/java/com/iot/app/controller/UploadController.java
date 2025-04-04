package com.iot.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class UploadController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping(value = "/upload", consumes = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<String> uploadImage(@RequestBody byte[] imageBytes) {
        try {
            // Create directory if it doesn't exist
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            // Generate filename with timestamp
            String filename = "image_" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".jpg";
            Path filePath = Paths.get(UPLOAD_DIR, filename);

            Files.write(filePath, imageBytes);
            return ResponseEntity.ok("Image uploaded successfully: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error saving image: " + e.getMessage());
        }
    }
}
