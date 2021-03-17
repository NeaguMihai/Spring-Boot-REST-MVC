package com.fruitshop.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRESTTestHelper {

    public static String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
