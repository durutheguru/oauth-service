package com.julianduru.oauthservice.controller.web;

import java.util.List;

/**
 * created by julian on 24/09/2022
 */
public abstract class Controller {


    abstract List<String> cssFiles();


    abstract List<String> compiledCssFiles();


    abstract List<String> jsFiles();


    abstract List<String> compiledJsFiles();


}
