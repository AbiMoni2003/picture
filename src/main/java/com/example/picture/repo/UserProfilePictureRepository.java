package com.example.picture.repo;

import com.example.picture.entity.UserProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserProfilePictureRepository extends JpaRepository<UserProfilePicture, Long> {
    Optional<UserProfilePicture> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
