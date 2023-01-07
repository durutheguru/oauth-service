package com.julianduru.oauthservice.module.user.controller;

import com.julianduru.oauthservice.BaseControllerTest;
import com.julianduru.oauthservice.data.UserDataProvider;
import com.julianduru.oauthservice.entity.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian on 26/12/2022
 */
public class UserControllerTest extends BaseControllerTest {


    @Autowired
    private UserDataProvider userDataProvider;



    @Test
    @WithMockUser
    public void searchingUsersByQuery() throws Exception {
        var user1 = new UserData();
        var user2 = new UserData();
        var user3 = new UserData();

        user1.setFirstName("JohnnyB");
        user2.setLastName("JohnnyB");
        user3.setUsername("johnnyB");

        userDataProvider.save(user1, user2, user3);
        userDataProvider.save(10);

        mockMvc.perform(
            get(UserController.PATH + "/search")
                .contentType(MediaType.APPLICATION_JSON)
                .param("query", "john")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.content", hasSize(3)));
    }


}

