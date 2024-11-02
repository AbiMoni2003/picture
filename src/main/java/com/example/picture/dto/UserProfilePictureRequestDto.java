package com.example.picture.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserProfilePictureRequestDto {
    
    private String userId;
    private MultipartFile file;
}
