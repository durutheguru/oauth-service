package com.julianduru.oauthservice.module.user.component;

import com.julianduru.data.messaging.dto.UserDataUpdate;
import com.julianduru.oauthservice.AuthServerConstants;
import com.julianduru.oauthservice.dto.UserDataDto;
import com.julianduru.oauthservice.entity.UserData;
import com.julianduru.oauthservice.exception.UnprocessableInputException;
import com.julianduru.oauthservice.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * created by julian on 05/06/2022
 */
@Component
@RequiredArgsConstructor
public class UserMutator {


    private final UserReader userReader;


    private final PasswordEncoder passwordEncoder;


    private final UserDataRepository userDataRepository;


    public UserData saveUser(UserDataDto userDataDto) throws UnprocessableInputException {
        var data = verifyUserDataInput(userDataDto.data()).encodePassword(passwordEncoder);
        return userDataRepository.save(data);
    }


    public UserData updateUser(UserDataUpdate userDataUpdate) {
        var username = userDataUpdate.getUsername();
        var user = userReader.fetchUser(username);

        user.setFirstName(userDataUpdate.getFirstName());
        user.setLastName(userDataUpdate.getLastName());
        user.setEmail(userDataUpdate.getEmail());

        var additionalInfo = user.getAdditionalInfo();
        if (additionalInfo == null) {
            additionalInfo = new HashMap<>();
        }

        if (StringUtils.hasText(userDataUpdate.getProfilePhotoRef())) {
            additionalInfo.put(
                AuthServerConstants.UserDataUpdateAdditionalInfoKey.PROFILE_PHOTO,
                userDataUpdate.getProfilePhotoRef()
            );
        }
        user.setAdditionalInfo(additionalInfo);

        return userDataRepository.save(user);
    }


    private UserData verifyUserDataInput(UserData data) {
        if (userDataRepository.existsByUsername(data.getUsername())) {
            throw new UnprocessableInputException(
                String.format("Username %s already exists", data.getUsername())
            );
        }

        if (userDataRepository.existsByEmail(data.getEmail())) {
            throw new UnprocessableInputException(
                String.format("Email %s already exists", data.getEmail())
            );
        }

        return data;
    }


}

