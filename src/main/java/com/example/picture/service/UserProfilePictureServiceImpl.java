package com.example.picture.service;

import com.example.picture.dto.UserProfilePictureResponseDto;
import com.example.picture.entity.UserProfilePicture;
import com.example.picture.facade.UserProfilePictureFacade;
import com.example.picture.repo.UserProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@Service
public class UserProfilePictureServiceImpl implements UserProfilePictureService {

    @Autowired
    private UserProfilePictureRepository repository;
    @Autowired
    private UserProfilePictureFacade userProfilePictureFacade;

    @Override
    public ResponseEntity<String> uploadProfilePicture(String userId, MultipartFile file) {
       return userProfilePictureFacade.uploadProfilePicture(userId,file);
        }

    @Override
    public ResponseEntity<byte[]> getProfilePicture(String userId) {
        Optional<UserProfilePicture> profilePicture = repository.findByUserId(userId);

        if (profilePicture.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new UserProfilePictureResponseDto("user ID: " + userId+" not found").getMessage().getBytes());
        }

        UserProfilePicture pic = profilePicture.get();
        byte[] pictureData = pic.getData();
        if (pictureData == null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new UserProfilePictureResponseDto("No image data found for user ID: " + userId).getMessage().getBytes());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(pic.getFileType()));
        headers.setContentLength(pictureData.length);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pictureData);
    }


    private void validateImageFile(MultipartFile file) {
        String fileType = file.getContentType();
        if (fileType == null || (!fileType.equals("image/jpeg") && !fileType.equals("image/png"))) {
            throw new IllegalArgumentException("Invalid file type. Only JPG and PNG are allowed.");
        }
    }
}
