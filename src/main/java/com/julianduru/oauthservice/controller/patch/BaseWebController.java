package com.julianduru.oauthservice.controller.patch;

import java.util.List;

/**
 * created by julian on 24/09/2022
 */
public abstract class BaseWebController {


    abstract List<String> cssFiles();


    abstract List<String> compiledCssFile();


    abstract List<String> jsFiles();


    abstract List<String> compiledJsFile();


}
