package com.example.picture.controller;

import com.example.picture.dto.UserProfilePictureResponseDto;
import com.example.picture.service.UserProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("profile-picture")
public class UserProfilePictureController {

    @Autowired
    private UserProfilePictureService service;

    private final UserProfilePictureService userProfilePictureService;
    public UserProfilePictureController(UserProfilePictureService userProfilePictureService) {
        this.userProfilePictureService = userProfilePictureService;
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("userId") String userId, @RequestParam("file") MultipartFile file) {
        return userProfilePictureService.uploadProfilePicture(userId, file);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable String userId) {
        return userProfilePictureService.getProfilePicture(userId);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }
}
