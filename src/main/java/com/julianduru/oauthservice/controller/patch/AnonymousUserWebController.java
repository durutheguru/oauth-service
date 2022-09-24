package com.julianduru.oauthservice.controller.patch;

import java.util.List;

/**
 * created by julian on 24/09/2022
 */
public abstract class AnonymousUserWebController extends BaseWebController {


    @Override
    public List<String> cssFiles() {
        return List.of();
    }


    @Override
    public List<String> compiledCssFile() {
        return List.of("compiled.css");
    }


    @Override
    public List<String> jsFiles() {
        return List.of();
    }


    @Override
    public List<String> compiledJsFile() {
        return List.of("compiled.js");
    }


}
