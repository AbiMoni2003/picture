package com.example.picture.facade;

import com.example.picture.dto.UserProfilePictureResponseDto;
import com.example.picture.entity.UserProfilePicture;
import com.example.picture.repo.UserProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UserProfilePictureFacade {
    @Autowired
    private UserProfilePictureRepository repository;
    private final String uploadDir = "D:/uploads/";
    public ResponseEntity<String> uploadProfilePicture(String userId, MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.ok().body("File is empty");
        }
        Optional<UserProfilePicture> existingPicture = repository.findByUserId(userId);
        if (existingPicture.isPresent()) {
            return ResponseEntity.ok("Profile picture already exists for user ID: " + userId);
        }

        UserProfilePicture userProfilePicture = new UserProfilePicture();
        userProfilePicture.setUserId(userId);
        userProfilePicture.setFileName(file.getOriginalFilename());
        userProfilePicture.setFileType(file.getContentType());
        userProfilePicture.setUploadDate(LocalDateTime.now());

        try {
            userProfilePicture.setData(file.getBytes()); // Convert file to byte array
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.OK).body("Error reading file");
        }

        repository.save(userProfilePicture);

        return ResponseEntity.ok("Profile picture uploaded successfully for user ID: " + userId);
    }
    private void validateImageFile(MultipartFile file) {
        String fileType = file.getContentType();
        if (fileType == null || (!fileType.equals("image/jpeg") && !fileType.equals("image/png"))) {
            throw new IllegalArgumentException("Invalid file type. Only JPG and PNG are allowed.");
        }
    }
}
