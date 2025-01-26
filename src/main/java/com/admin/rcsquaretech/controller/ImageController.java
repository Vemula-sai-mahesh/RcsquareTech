package com.admin.rcsquaretech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImageController {

    // private static final String IMAGE_DIR = "C:/Users/gurup/Cstpl Projects/Rc_Square_Tech_Final_Project/Rc_Square_Tech_Backend/rcsquaretech/images/";
//    private static final String IMAGE_DIR = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	 private static final String IMAGE_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "main" + File.separator + "resources" + File.separator + "static" +
            File.separator + "images" + File.separator;

    @PostMapping("/uploadImg")
    public ResponseEntity<Map<String, String>> uploadImages(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        Map<String, String> responseMap = new HashMap<>();

        for (MultipartFile file : files) {
            // Ensure the upload directory exists
            File directory = new File(IMAGE_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Extract original file name and extension
            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null) {
                responseMap.put("unknown", "Invalid file name");
                continue;
            }
            String fileExtension = "";
            int dotIndex = originalFileName.lastIndexOf('.');
            if (dotIndex != -1) {
                fileExtension = originalFileName.substring(dotIndex); // Includes the dot (e.g., ".jpg")
            }
            String baseName = originalFileName.substring(0, dotIndex != -1 ? dotIndex : originalFileName.length());

            // Append timestamp to the file name
            String timestamp = String.valueOf(System.currentTimeMillis());
            String newFileName = baseName + "_" + timestamp + fileExtension;

            // Save file locally
            File destinationFile = new File(IMAGE_DIR + newFileName);
            try {
                file.transferTo(destinationFile);

                // Build the dynamic URL
                String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                        + "/images/" + newFileName;

                // Add to response map
                responseMap.put(newFileName, fileUrl);
            } catch (IOException e) {
                // In case of error, log and skip to the next file
                responseMap.put(newFileName, "Error occurred while saving the file.");
            }
        }

        return ResponseEntity.ok(responseMap);
    }
}

