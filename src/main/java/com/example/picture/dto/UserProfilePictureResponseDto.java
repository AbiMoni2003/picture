package com.example.picture.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfilePictureResponseDto {

    private String userId;
    private String fileName;
    private String fileType;
    private LocalDateTime uploadDate;
    private String message;

    public UserProfilePictureResponseDto(String userId, String fileName, String fileType, LocalDateTime uploadDate) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.uploadDate = uploadDate;
    }
    public UserProfilePictureResponseDto(String message){
        this.message=message;
    }
}
