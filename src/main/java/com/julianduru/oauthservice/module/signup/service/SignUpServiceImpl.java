package com.julianduru.oauthservice.module.signup.service;

import com.julianduru.oauthservice.module.client.ClientService;
import com.julianduru.oauthservice.module.resourceserver.ResourceServerService;
import com.julianduru.oauthservice.module.signup.dto.SignUpUserDto;
import com.julianduru.oauthservice.module.user.UserService;
import com.julianduru.oauthservice.util.ClientUtil;
import com.julianduru.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;

/**
 * created by julian on 24/09/2022
 */
@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {


    private final UserService userService;


    private final ClientService clientService;


    private final ResourceServerService resourceServerService;



    @Override
    public String signupUser(SignUpUserDto dto) {
        ValidatorUtil.validate(dto);

        var client = clientService.fetchClient(dto.getClientId());
        ClientUtil.verifyClientCanAccessResource(client, dto.getResourceServerId());

        var resourceServer = resourceServerService.fetchResourceServer(
            dto.getResourceServerId()
        ).orElseThrow(
            () -> new EntityNotFoundException(
                String.format("Resource Server %s not found", dto.getResourceServerId())
            )
        );

        var userData = dto.toUserDataDto();
        userData.setAuthorities(resourceServer.getUserAuthoritiesOnSignUp().stream().toList());
        userService.saveUser(userData);

        return clientService.buildLoginUrl(client);
    }


    @Override
    public String showUserSignUp(Model model, String clientId, String resourceServerId) {
        return showUserSignUp(model, clientId, resourceServerId, null);
    }


    @Override
    public String showUserSignUp(Model model, String clientId, String resourceServerId, String error) {
        var user = new SignUpUserDto();

        user.setClientId(clientId);
        user.setResourceServerId(resourceServerId);
        model.addAttribute("user", user);

        if (StringUtils.hasText(error)) {
            model.addAttribute("error", error);
        }

        return "signup";
    }


}



