package com.rohan.airBnb.Service;

import com.rohan.airBnb.Entity.User;
import com.rohan.airBnb.dto.ProfileUpdateRequestDto;
import com.rohan.airBnb.dto.UserResponseDTO;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserResponseDTO getMyProfile();
}
