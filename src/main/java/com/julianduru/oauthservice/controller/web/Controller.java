package com.julianduru.oauthservice.controller.web;

import com.julianduru.oauthservice.AuthServerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

import java.util.List;

/**
 * created by julian on 24/09/2022
 */
public abstract class Controller {


    @Value("${code.config.app-mode:development}")
    private String mode;


    abstract List<String> cssFiles();


    abstract List<String> compiledCssFiles();


    abstract List<String> jsFiles();


    abstract List<String> compiledJsFiles();


    protected void augmentModelWithAssets(Model model) {
        model.addAttribute(
            "cssFiles",
            AuthServerConstants.AppModes.BUILD.equalsIgnoreCase(mode) ? compiledCssFiles() : cssFiles()
        );
        model.addAttribute(
            "jsFiles",
            AuthServerConstants.AppModes.BUILD.equalsIgnoreCase(mode) ? compiledJsFiles() : jsFiles()
        );
    }


}
