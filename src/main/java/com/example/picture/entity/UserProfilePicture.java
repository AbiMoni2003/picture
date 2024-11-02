package com.example.picture.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private LocalDateTime uploadDate;
    @Lob // This annotation is important for large data
    private byte[] data;

    public UserProfilePicture(Long id, String userId, String fileName, String fileType, LocalDateTime uploadDate) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.uploadDate = uploadDate;
    }

    public UserProfilePicture() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
