package com.julianduru.oauthservice.module.login.service;

import com.julianduru.oauthservice.module.client.ClientService;
import com.julianduru.oauthservice.util.ClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

/**
 * created by julian on 07/10/2022
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {


    private final ClientService clientService;


    @Override
    public String showUserLogin(Model model, String clientId, String resourceServerId) {
        if (StringUtils.hasText(clientId) && StringUtils.hasText(resourceServerId)) {
            var client = clientService.fetchClient(clientId);
            ClientUtil.verifyClientCanAccessResource(client, resourceServerId);

            return String.format("redirect:%s", clientService.buildLoginUrl(client));
        }
        else {
            return "login";
        }
    }


}
