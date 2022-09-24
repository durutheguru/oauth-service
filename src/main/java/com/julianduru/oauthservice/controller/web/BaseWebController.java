package com.julianduru.oauthservice.controller.web;

import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * created by julian on 24/09/2022
 */
@Slf4j
public abstract class BaseWebController extends Controller {


    @Value("classpath:ui_build_paths.json")
    private Resource uiPathsResource;


    private JSONObject pathJSON;


    @PostConstruct
    public void init() {
        try {
            pathJSON = new JSONObject(IOUtils.toString(uiPathsResource.getInputStream()));
        }
        catch (IOException e) {
            log.error("Error while parsing UI Resource Paths JSON", e);
        }
    }


    @Override
    public List<String> cssFiles() {
        var list = new ArrayList<>(
            pathJSON.getJSONArray("cssVendorList")
            .toList()
            .stream()
            .map(Object::toString).toList()
        );

        list.addAll(
            pathJSON.getJSONArray("themeCSSFiles")
                .toList()
                .stream()
                .map(Object::toString).toList()
        );

        return list;
    }


    @Override
    public List<String> compiledCssFiles() {
        return List.of("/compiled/compiled.css");
    }


    @Override
    public List<String> jsFiles() {
        var list = new ArrayList<>(
            pathJSON.getJSONArray("jsVendorList")
            .toList()
            .stream()
            .map(Object::toString).toList()
        );

        list.addAll(
            pathJSON.getJSONArray("themeJSFiles")
                .toList()
                .stream()
                .map(Object::toString).toList()
        );

        return list;
    }


    @Override
    public List<String> compiledJsFiles() {
        return List.of("/compiled/compiled.js");
    }


}
