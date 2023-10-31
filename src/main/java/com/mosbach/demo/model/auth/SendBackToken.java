package com.mosbach.demo.model.auth;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class SendBackToken {

    private String token;
    private long validInSeconds;

    private String userID;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    public SendBackToken(String token, long validInSeconds, String userID) {
        this.token = token;
        this.validInSeconds = validInSeconds;
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getValidInSeconds() {
        return validInSeconds;
    }

    public void setValidInSeconds(long validInSeconds) {
        this.validInSeconds = validInSeconds;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
