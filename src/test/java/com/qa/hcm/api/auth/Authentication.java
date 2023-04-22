package com.qa.hcm.api.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hcm.api.common.RestClient;
import com.qa.hcm.api.common.TestUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Authentication{
    Properties auth = new TestUtil().init_Properties("Authorization");

    public String getAccessToken(String baseURL, String role) throws IOException {
        Map<String,Object> jsonBody = new HashMap<>();
        switch (role){
            case "INTERNAL":
                jsonBody.put("userName",auth.getProperty("INTERNAL"));
                jsonBody.put("Password",auth.getProperty("COMMON_PASSWORD"));
                break;
        }
        return getBearerToken( jsonBody);
    }

    public String getBearerToken(Map<String, Object> jsonBody) throws JsonProcessingException {
    	String basePathAccessToken = auth.getProperty("ACCESS_TOKEN_PATH");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(jsonBody);
        Response actualResponse = RestClient.doPost("JSON", null, null, basePathAccessToken, true, json);
        assert actualResponse != null;
        JsonPath jsonPathEvaluator = actualResponse.jsonPath();
        return "Bearer "+jsonPathEvaluator.get("access");
    }

    public String getAccessTokenDirectlywithUserNamePassword() throws JsonProcessingException {
        Map<String,Object> jsonBody = new HashMap<>();
        jsonBody.put("email",auth.getProperty("INTERNAL"));
        jsonBody.put("password",auth.getProperty("COMMON_PASSWORD"));
        return getBearerToken( jsonBody);
    }
}
