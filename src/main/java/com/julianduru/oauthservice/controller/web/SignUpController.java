package com.julianduru.oauthservice.controller.web;

import com.julianduru.oauthservice.AuthServerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * created by julian on 24/09/2022
 */
@Controller
public class SignUpController extends AnonymousUserWebController {


    @Value("${code.config.app-mode:development}")
    private String mode;


    @RequestMapping("/signup")
    public String signup(Model model, HttpServletRequest request) {
        var clientId = request.getParameter("client_id");

        model.addAttribute("client_id", clientId);
        model.addAttribute(
            "cssFiles",
            AuthServerConstants.AppModes.BUILD.equalsIgnoreCase(mode) ? compiledCssFiles() : cssFiles()
        );
        model.addAttribute(
            "jsFiles",
            AuthServerConstants.AppModes.BUILD.equalsIgnoreCase(mode) ? compiledJsFiles() : jsFiles()
        );

        return "signup";
    }


}