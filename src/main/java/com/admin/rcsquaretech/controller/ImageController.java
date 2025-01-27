package com.admin.rcsquaretech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Value("${image.dir.path}")
    private String IMAGE_DIR;
    @Value("${rcsquaretech.files.path}")
    private String rcsquaretechFilesPath;
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);


    // private static final String IMAGE_DIR = "C:/Users/gurup/Cstpl Projects/Rc_Square_Tech_Final_Project/Rc_Square_Tech_Backend/rcsquaretech/images/";
//    private static final String IMAGE_DIR = System.getProperty("user.dir") + File.separator + "images" + File.separator;
//	 private static final String IMAGE_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator +
//            "main" + File.separator + "resources" + File.separator + "static" +
//            File.separator + "images" + File.separator;


    @PostMapping("/uploadImg")
    public ResponseEntity<Map<String, String>> uploadImages(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        Map<String, String> responseMap = new HashMap<>();

        for (MultipartFile file : files) {
            // Ensure the upload directory exists
            File directory = new File(IMAGE_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null || originalFileName.isEmpty()) {
                responseMap.put("unknown", "Invalid file name");
                continue;
            }

            // Validate file type
            String contentType = file.getContentType();
            if (!"image/png".equals(contentType) && !"image/jpeg".equals(contentType)) {
                responseMap.put(originalFileName, "Invalid file type. Only PNG and JPEG are supported.");
                continue;
            }

            // Validate file size (limit to 5 MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                responseMap.put(originalFileName, "File size exceeds the maximum allowed size (5 MB).");
                continue;
            }

            try {
                // Create a unique file name with timestamp
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String baseName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
                String timestamp = String.valueOf(System.currentTimeMillis());
                String newFileName = baseName + "_" + timestamp + fileExtension;

                // Save the file
                File destinationFile = new File(IMAGE_DIR + File.separator + newFileName);
                file.transferTo(destinationFile);

                // Generate URL for accessing the uploaded file
//                String fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//                        + "/images/" + newFileName;
                String fileUrl = rcsquaretechFilesPath + newFileName;

                responseMap.put(newFileName, fileUrl);
            } catch (IOException e) {
                logger.error("Error occurred while saving the file: {}", originalFileName, e);
                responseMap.put(originalFileName, "Error occurred while saving the file.");
            }
        }

        return ResponseEntity.ok(responseMap);
    }
}

