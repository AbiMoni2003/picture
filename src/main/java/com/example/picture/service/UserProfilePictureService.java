package com.example.picture.service;

import com.example.picture.dto.UserProfilePictureResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfilePictureService {
    
    ResponseEntity<String> uploadProfilePicture(String userId, MultipartFile file);
    
    ResponseEntity<byte[]> getProfilePicture(String userId);
}
